package at.stefanproell.DataTypeDetector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by stefan on 22.03.16.
 */
public class DatatypeStatistics {

    private final Logger logger;
    private Map<String, ColumnMetadata> columnMap;


    public DatatypeStatistics() {
        this.logger = Logger.getLogger(this.getClass().getName());


        this.columnMap = new HashMap<String, ColumnMetadata>();

    }


    /**
     * Iterate over all headers from the CSV file. Each element of the header array denotes the name of a column.
     * For each of the columns, create a new column object to store the statistics.
     */
    public void initColumnObjects(String[] headers) {
        this.columnMap = new HashMap<String, ColumnMetadata>();

        for (String columnName : headers) {
            ColumnMetadata column = new ColumnMetadata();
            column.setColumnName(columnName);
            columnMap.put(columnName, column);
        }

    }

    /**
     * Iterate over all headers from the CSV file. Each element of the header array denotes the name of a column.
     * For each of the columns, create a new column object to store the statistics.
     */
    public void initColumnObjects(List<String> headers) {
        this.columnMap = new HashMap<String, ColumnMetadata>();
        for (String columnName : headers) {
            ColumnMetadata column = new ColumnMetadata();
            column.setColumnName(columnName);
            columnMap.put(columnName, column);
        }

    }



    public Map<String, ColumnMetadata> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, ColumnMetadata> columnMap) {
        this.columnMap = columnMap;
    }

    public void updateColumnStatistic(String columnName, String dataTypeName, int length) {

        logger.info("Aktuelle Werte. Column name " + columnName + " data type: " + dataTypeName + " length: " + length);
        logger.info("Test: ");
        if(this.columnMap==null){
            logger.info("NULL test");
        }
        this.columnMap.get(columnName).updateRecordLength(length);
        this.columnMap.get(columnName).incrementRowCount();

        if (dataTypeName.equalsIgnoreCase("NULL")) {
            this.columnMap.get(columnName).incrementCountNull();
        } else if (dataTypeName.equalsIgnoreCase("Date")) {
            this.columnMap.get(columnName).incrementCountDate();
        } else if (dataTypeName.equalsIgnoreCase("Integer")) {

            this.columnMap.get(columnName).incrementCountInteger();
        } else if (dataTypeName.equalsIgnoreCase("Double")) {
            this.columnMap.get(columnName).incrementCountDouble();
        } else if (dataTypeName.equalsIgnoreCase("Boolean")) {
            this.columnMap.get(columnName).incrementCountBoolean();
        } else if (dataTypeName.equalsIgnoreCase("Float")) {
            this.columnMap.get(columnName).incrementCountFloat();
        } else if (dataTypeName.equalsIgnoreCase("Long")) {
            this.columnMap.get(columnName).incrementCountLong();
        } else if (dataTypeName.equalsIgnoreCase("String")) {
            this.columnMap.get(columnName).incrementCountString();
        }

    }

    public void printResults() {
        for (Map.Entry<String, ColumnMetadata> column : this.columnMap.entrySet()) {
            System.out.println("------------------------------------");
            column.getValue().printColumnStatistics();
        }

    }
}