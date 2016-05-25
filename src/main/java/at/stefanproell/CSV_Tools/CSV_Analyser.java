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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    public CSV_Analyser(File csvFile) {
        ;
        this.parseCSV(csvFile);
        this.statistics = new DatatypeStatistics();
        this.statistics.initColumnObjects(this.headersArray);
        this.analyse(this.csvAsMap);
        // this.statistics.printResults();


    }

    /**
     * Constructor
     */
    public CSV_Analyser(){
        this.statistics = new DatatypeStatistics();



    }

    /**
     * Parse a CSV file and store it in a HashMap
     * @param csvFile
     */
    public Map<Integer, Map<String, Object>> parseCSV(File csvFile) {

        try {

            this.csvAsMap = this.readCSV(csvFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Analyse the CSV map
     * @param csvAsMap
     */
    public DatatypeStatistics analyse(Map<Integer, Map<String, Object>> csvAsMap) {


        DataTypeDetectorAPI detectorAPI = new DataTypeDetectorAPI();

        for (Map.Entry<Integer, Map<String, Object>> csvRowMap : csvAsMap.entrySet())
        {

            Map<String, Object> csvRow = csvRowMap.getValue();

            for (Map.Entry<String, Object> column: csvRow.entrySet())
            {
                //logger.info("Column: " + column.getKey() + " Value: " + column.getValue() + " Detected type "+ this.detectorAPI.getDataType(column.getValue().toString()));
                String columnName = column.getKey();
                String dataType = detectorAPI.getDataType((String) column.getValue());
                int recordLength = column.getValue().toString().length();
                this.statistics.updateColumnStatistic(columnName,dataType,recordLength);

            }

        }
        return this.statistics;


    }

    /**
     * Analyse the CSV map
     * @param csvAsMap
     */
    public DatatypeStatistics analyse(Map<Integer, Map<String, Object>> csvAsMap, List<String> headerList) {


        this.statistics.initColumnObjects(headerList);


        DataTypeDetectorAPI detectorAPI = new DataTypeDetectorAPI();

        for (Map.Entry<Integer, Map<String, Object>> csvRowMap : csvAsMap.entrySet()) {

            Map<String, Object> csvRow = csvRowMap.getValue();

            for (Map.Entry<String, Object> column : csvRow.entrySet()) {
                //logger.info("Column: " + column.getKey() + " Value: " + column.getValue() + " Detected type "+ this.detectorAPI.getDataType(column.getValue().toString()));
                String columnName = column.getKey();
                String dataType = detectorAPI.getDataType((String) column.getValue());
                int recordLength = column.getValue().toString().length();
                this.statistics.updateColumnStatistic(columnName, dataType, recordLength);

            }

        }
        return this.statistics;
    }


    /**
     * Read a CSV file into a HashMap. Uses Super CSV
     *
     * @param csvFile
     * @return
     * @throws Exception
     */
    public Map<Integer, Map<String, Object>> readCSV(File csvFile) throws Exception {

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


    /**
     * Read a CSV file into a HashMap. Utilises provided headers.
     * @param csvFile
     * @return
     * @throws Exception
     */
    public Map<Integer, Map<String, Object>> readCSV(File csvFile, List<String> headersList) throws Exception {

        ICsvMapReader mapReader = null;
        Map<String, Object> rowAsMap;
        Map<Integer, Map<String, Object>> csvAsMap = new HashMap<Integer, Map<String, Object>>();


        try {
            mapReader = new CsvMapReader(new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);

            // the headersArray columns are used as the keys to the Map

            this.headersArray = new String[headersList.size()];
            headersList.toArray(this.headersArray);
            int amountOfColumns = this.headersArray.length;

            final CellProcessor[] processors = new CellProcessor[amountOfColumns];
            for (int i = 0; i < amountOfColumns; i++) {
                processors[i] = new Optional();

            }


            while ((rowAsMap = mapReader.read(this.headersArray, processors)) != null) {

                //System.out.println(String.format("lineNo=%s, rowNo=%s, rowAsMap=%s", mapReader.getLineNumber(),mapReader.getRowNumber(), rowAsMap));
                csvAsMap.put(mapReader.getRowNumber(), rowAsMap);

            }

        } finally {
            if (mapReader != null) {
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

    public static Logger getLogger() {
        return logger;
    }

    public Map<Integer, Map<String, Object>> getCsvAsMap() {
        return csvAsMap;
    }

    public void setCsvAsMap(Map<Integer, Map<String, Object>> csvAsMap) {
        this.csvAsMap = csvAsMap;
    }

    public String[] getHeadersArray() {
        return headersArray;
    }

    public void setHeadersArray(String[] headersArray) {
        this.headersArray = headersArray;
    }

    public void setHeadersArray(List<String> headersList) {
        this.headersArray = new String[headersList.size()];
        this.headersArray = headersList.toArray(this.headersArray);

    }
}
