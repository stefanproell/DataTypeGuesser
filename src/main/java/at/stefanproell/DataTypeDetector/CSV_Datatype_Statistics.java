package at.stefanproell.DataTypeDetector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefan on 22.03.16.
 */
public class CSV_Datatype_Statistics {
    private String filePath;
    private Map<String, CSV_Datatype_Statistics_Column> columnMap;
    private String[] headers;

    public CSV_Datatype_Statistics(String filePath) {
        this.filePath = filePath;
        this.columnMap = new HashMap<String, CSV_Datatype_Statistics_Column>();

    }


    /**
     * Iterate over all headers from the CSV file. Each element of the header array denotes the name of a column. For each of the columns, create a new column object to store the statistics.
     */
    public void createCSV_Datatype_Statistics_Column_Objects() {
        for (String columnName : this.headers) {
            CSV_Datatype_Statistics_Column column = new CSV_Datatype_Statistics_Column();
            column.setColumnName(columnName);
            columnMap.put(columnName, column);
        }

    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public Map<String, CSV_Datatype_Statistics_Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, CSV_Datatype_Statistics_Column> columnMap) {
        this.columnMap = columnMap;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void updateColumnStatistic(String columnName, String dataTypeName, int length) {

        this.columnMap.get(columnName).updateRecordLength(length);

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
        for (Map.Entry<String, CSV_Datatype_Statistics_Column> column : this.columnMap.entrySet()) {
            System.out.println("------------------------------------");
            column.getValue().printColumnStatistics();



        }

    }
}