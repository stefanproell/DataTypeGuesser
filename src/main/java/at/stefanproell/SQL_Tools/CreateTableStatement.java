package at.stefanproell.SQL_Tools;

import at.stefanproell.DataTypeDetector.ColumnMetadata;
import at.stefanproell.DataTypeDetector.DatatypeStatistics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefan on 03.05.16.
 */
public class CreateTableStatement {
    private Map<String,String> dataTypeMappingMap;
    /**
     * Constructor
     */
    public CreateTableStatement(){
        this.dataTypeMappingMap = new HashMap<String, String>();
        this.dataTypeMappingMap.put("Boolean","BOOLEAN");
        this.dataTypeMappingMap.put("Date","DATE");
        this.dataTypeMappingMap.put("Double","DOUBLE");
        this.dataTypeMappingMap.put("Float","FLOAT");
        this.dataTypeMappingMap.put("Integer","INTEGER");
        this.dataTypeMappingMap.put("Long","BIGINT");
        this.dataTypeMappingMap.put("String","VARCHAR");

    };

    /**
     * Create a simple CREATE TABLE statement in the MySQL Dialect
     * @param tableName
     * @param statistics
     * @return
     */
    public String createMySQLInnoDBTable(String tableName, DatatypeStatistics statistics){
        String createMySQLInnoDBTableString = "CREATE TABLE " + tableName + "(";

        // gather the column metadata
        Map<String, ColumnMetadata> map = statistics.getColumnMap();
        // Iterate over the list of column objects
        for (Map.Entry<String, ColumnMetadata> column : map.entrySet()) {
            // column name
            String columnName = column.getKey();
            // datatype
            String dataType = this.strictDataType(column.getValue());
            // map the data type to the SQL dialect
            String mySQLDataType = this.dataTypeMappingMap.get(dataType);

            // append the size to INTEGERs or VARCHAR data types

            if(mySQLDataType.equalsIgnoreCase("INTEGER") ||mySQLDataType.equalsIgnoreCase("BIGINTEGER") || mySQLDataType.equalsIgnoreCase("VARCHAR")  ){
                mySQLDataType+= "("+column.getValue().getRecordLength()+")";
            }

            createMySQLInnoDBTableString+=columnName+ " " + mySQLDataType + ",";

        }

        // Remove the latest comma from the list
        if (createMySQLInnoDBTableString.endsWith(",")) {
            createMySQLInnoDBTableString = createMySQLInnoDBTableString.substring(0, createMySQLInnoDBTableString.length() - 1);
        }

        createMySQLInnoDBTableString+=");";


        return createMySQLInnoDBTableString;
    }

    /**
     * Create a simple CREATE TABLE statement in the MySQL Dialect
     * @param tableName
     * @param statistics
     * @return
     */
    public String createBeautifulMySQLInnoDBTable(String tableName, DatatypeStatistics statistics){
        String createMySQLInnoDBTableString = "CREATE TABLE " + tableName + "(\n";

        // gather the column metadata
        Map<String, ColumnMetadata> map = statistics.getColumnMap();
        // Iterate over the list of column objects
        for (Map.Entry<String, ColumnMetadata> column : map.entrySet()) {
            createMySQLInnoDBTableString+="\t";
            // column name
            String columnName = column.getKey();
            // datatype
            String dataType = this.strictDataType(column.getValue());
            // map the data type to the SQL dialect
            String mySQLDataType = this.dataTypeMappingMap.get(dataType);

            // append the size to INTEGERs or VARCHAR data types

            if(mySQLDataType.equalsIgnoreCase("INTEGER") ||mySQLDataType.equalsIgnoreCase("BIGINTEGER") || mySQLDataType.equalsIgnoreCase("VARCHAR")  ){
                mySQLDataType+= "("+column.getValue().getRecordLength()+")";
            }
            // append complete column definition
            createMySQLInnoDBTableString+=columnName+ " " + mySQLDataType + ",";
            createMySQLInnoDBTableString+="\n";

        }

        // Remove the latest comma from the list
        if (createMySQLInnoDBTableString.endsWith(",")) {
            createMySQLInnoDBTableString = createMySQLInnoDBTableString.substring(0, createMySQLInnoDBTableString.length() - 1);
        }

        // close statement
        createMySQLInnoDBTableString+=");\n";


        return createMySQLInnoDBTableString;
    }

    /**
     *
     * @param column
     * @return
     */
    private String strictDataType(ColumnMetadata column){
        if (this.isStrict(column)){
            HashMap <String, Integer> dataTypes = column.getDataTypes();
            for (Map.Entry<String, Integer> entry : dataTypes.entrySet())
            {
                if(entry.getValue() == column.getRowCount()){
                    return entry.getKey();
                }
            }

        }
        return null;

    }


    /**
     * Return true if all records have the same type
     * @param column
     * @return
     */
    private boolean isStrict(ColumnMetadata column){
        HashMap <String, Integer> dataTypes = column.getDataTypes();
        int maxValueInMap=(Collections.max(dataTypes.values()));


        for (Map.Entry<String, Integer> entry : dataTypes.entrySet())
        {
            if(entry.getValue() == maxValueInMap){
                if(maxValueInMap==column.getRowCount()){
                    return true;
                }
            }
        }

        return false;
    }

    public void printStatement(String input){
        System.out.print(input);

    }

    public String getMySQLColumn(ColumnMetadata column){
        String dataType = this.strictDataType(column);
        // map the data type to the SQL dialect
        String mySQLDataType = this.dataTypeMappingMap.get(dataType);

        // append the size to INTEGERs or VARCHAR data types

        if(mySQLDataType.equalsIgnoreCase("INTEGER") ||mySQLDataType.equalsIgnoreCase("BIGINTEGER") || mySQLDataType.equalsIgnoreCase("VARCHAR")  ){
            mySQLDataType+= "("+column.getRecordLength()+")";
        }
        return mySQLDataType;
    }

}
