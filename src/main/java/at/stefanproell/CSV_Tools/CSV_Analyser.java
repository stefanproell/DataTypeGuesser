package at.stefanproell.CSV_Tools;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.DataTypeDetector.CSV_Datatype_Statistics;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by stefan on 22.03.16.
 */
public class CSV_Analyser {
    private static final Logger logger= Logger.getLogger( CSV_Analyser.class.getName() );
    private Map<Integer, Map<String,Object>> csvAsMap;
    private String[] header;
    private CSV_Datatype_Statistics statistics;
    private String csvFilePath;
    private DataTypeDetectorAPI detectorAPI;

    public CSV_Analyser(String csvFilePath) {
        this.detectorAPI = new DataTypeDetectorAPI();
        this.csvFilePath = csvFilePath;
        this.statistics = new CSV_Datatype_Statistics(csvFilePath);
        try {
            this.csvAsMap = this.readCSV(csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.statistics.setHeaders(this.header);
        this.statistics.createCSV_Datatype_Statistics_Column_Objects();
        this.analyse(this.csvAsMap);
        this.statistics.printResults();


    }

    private void analyse(Map<Integer, Map<String,Object>> csvAsMap){

        for (Map.Entry<Integer, Map<String, Object>> csvRowMap : csvAsMap.entrySet())
        {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            int rowNumber = csvRowMap.getKey();
            Map<String, Object> csvRow = csvRowMap.getValue();

            for (Map.Entry<String, Object> column: csvRow.entrySet())
            {
                //logger.info("Column: " + column.getKey() + " Value: " + column.getValue() + " Detected type "+ this.detectorAPI.getDataType(column.getValue().toString()));
                String columnName = column.getKey();
                String dataType = this.detectorAPI.getDataType((String)column.getValue());
                int recordLength = column.getValue().toString().length();
                this.statistics.updateColumnStatistic(columnName,dataType,recordLength);

            }




        }


    }



    private Map<Integer, Map<String,Object>> readCSV(String csvFile) throws Exception {

        ICsvMapReader mapReader = null;
        Map<String, Object> rowAsMap;
        Map<Integer, Map<String,Object>> csvAsMap = new HashMap<Integer, Map<String, Object>>();


        try {
            mapReader = new CsvMapReader(new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);

            // the header columns are used as the keys to the Map
            this.header = mapReader.getHeader(true);
            int amountOfColumns = this.header.length;

            final CellProcessor[] processors = new CellProcessor[amountOfColumns];
            for(int i=0; i< amountOfColumns; i++){
                processors[i]=new Optional();

            }


            while( (rowAsMap = mapReader.read(this.header, processors)) != null ) {

                //System.out.println(String.format("lineNo=%s, rowNo=%s, rowAsMap=%s", mapReader.getLineNumber(),mapReader.getRowNumber(), rowAsMap));
                csvAsMap.put(mapReader.getRowNumber(),rowAsMap);

            }

        }
        finally {
            if( mapReader != null ) {
                mapReader.close();
            }
        }

        return csvAsMap;
    }


}
