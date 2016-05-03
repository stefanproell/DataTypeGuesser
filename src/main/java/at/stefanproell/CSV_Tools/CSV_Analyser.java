package at.stefanproell.CSV_Tools;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.DataTypeDetector.DatatypeStatistics;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
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
    private String[] headersArray;
    private DatatypeStatistics statistics;

    private DataTypeDetectorAPI detectorAPI;

    public CSV_Analyser(String csvFilePath) {
        this.detectorAPI = new DataTypeDetectorAPI();
        this.parseCSV(csvFilePath);
        this.statistics = new DatatypeStatistics();
        this.statistics.initColumnObjects(this.headersArray);
        this.analyse(this.csvAsMap);
        this.statistics.printResults();


    }

    /**
     * Constructor
     */
    public CSV_Analyser(){
        this.detectorAPI = new DataTypeDetectorAPI();

    }

    /**
     * Parse a CSV file and store it in a HashMap
     * @param csvFilePath
     */
    public void parseCSV(String csvFilePath) {

        try {
            File csvFile = new File(csvFilePath);
            this.csvAsMap = this.readCSV(csvFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Analyse
     * @param csvAsMap
     */
    private void analyse(Map<Integer, Map<String,Object>> csvAsMap){

        for (Map.Entry<Integer, Map<String, Object>> csvRowMap : csvAsMap.entrySet())
        {

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


    /**
     * Read a CSV file into a HashMap
     * @param csvFile
     * @return
     * @throws Exception
     */
    private Map<Integer, Map<String,Object>> readCSV(File csvFile) throws Exception {

        ICsvMapReader mapReader = null;
        Map<String, Object> rowAsMap;
        Map<Integer, Map<String,Object>> csvAsMap = new HashMap<Integer, Map<String, Object>>();


        try {
            mapReader = new CsvMapReader(new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);

            // the headersArray columns are used as the keys to the Map
            this.headersArray = mapReader.getHeader(true);
            int amountOfColumns = this.headersArray.length;

            final CellProcessor[] processors = new CellProcessor[amountOfColumns];
            for(int i=0; i< amountOfColumns; i++){
                processors[i]=new Optional();

            }


            while( (rowAsMap = mapReader.read(this.headersArray, processors)) != null ) {

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

    public DatatypeStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(DatatypeStatistics statistics) {
        this.statistics = statistics;
    }
}
