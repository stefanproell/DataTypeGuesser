package at.stefanproell.CSV_Tools;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.DataTypeDetector.DatatypeStatistics;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.*;
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
    public CSV_Analyser(String[] headersArray) {
        this.headersArray = headersArray;
        this.statistics = new DatatypeStatistics();
        this.statistics.initColumnObjects(headersArray);
    }

    public CSV_Analyser() {
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

        return this.csvAsMap;
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
                String columnName = (String) column.getValue();
                String dataType = detectorAPI.getDataType((String) column.getKey());
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
    public DatatypeStatistics analyse(Map<Integer, Map<String, Object>> csvAsMap, String[] headerList) {


        this.statistics.initColumnObjects(headerList);


        DataTypeDetectorAPI detectorAPI = new DataTypeDetectorAPI();

        for (Map.Entry<Integer, Map<String, Object>> csvRowMap : csvAsMap.entrySet()) {

            Map<String, Object> csvRow = csvRowMap.getValue();

            for (Map.Entry<String, Object> column : csvRow.entrySet()) {

                String columnName = column.getKey();
                String dataType = detectorAPI.getDataType((String) column.getValue());
                //logger.info("Column: " + column.getKey() + " Value: " + column.getValue());

                int recordLength = 0;

                if (column.getValue() != null) {
                    recordLength = column.getValue().toString().length();
                }else{
                    recordLength=0;
                }

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

    /**
     * Verify if a column is unique
     *
     * @param columnName
     * @param csvPath
     * @return
     */
    public boolean verifyUniquenessOfColumn(String columnName, String csvPath) {

        ICsvListReader reader = null;

        boolean isUnique = false;
        try {
            reader = new CsvListReader(new FileReader(csvPath), CsvPreference.STANDARD_PREFERENCE);
            String[] headers = reader.getHeader(true); // skip header

            List<String> columnValues = new ArrayList<String>();
            int index = -1;

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(columnName)) {
                    index = i;
                    break;
                }
            }

            List<String> line;
            while ((line = reader.read()) != null) {
                columnValues.add(line.get(index));
            }

            // Store all column values in a set. This removes duplicates.
            // If the set is smaller than the list, then there have been duplicates.
            Set<String> set = new HashSet<String>(columnValues);

            if (set.size() == columnValues.size()) {
                isUnique = true;
            } else {
                isUnique = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUnique;


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
