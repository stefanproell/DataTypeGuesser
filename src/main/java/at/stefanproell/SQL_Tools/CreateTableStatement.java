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

    public String createMySQLInnoDBTable(String tableName, DatatypeStatistics statistics){
        String createMySQLInnoDBTableString = "CREATE TABLE " + tableName + "(";
        Map<String, ColumnMetadata> map = statistics.getColumnMap();

        for (Map.Entry<String, ColumnMetadata> column : map.entrySet()) {
            String columnName = column.getKey();
            String dataType = this.strictDataType(column.getValue());
            String mySQLDataType = this.dataTypeMappingMap.get(dataType);

            if(mySQLDataType.equalsIgnoreCase("INTEGER") ||mySQLDataType.equalsIgnoreCase("BIGINTEGER") || mySQLDataType.equalsIgnoreCase("VARCHAR")  ){
                mySQLDataType+= "("+column.getValue().getRecordLength()+")";
            }



            createMySQLInnoDBTableString+=columnName+ " " + mySQLDataType + ",";

        }
        if (createMySQLInnoDBTableString.endsWith(",")) {
            createMySQLInnoDBTableString = createMySQLInnoDBTableString.substring(0, createMySQLInnoDBTableString.length() - 1);
        }

        createMySQLInnoDBTableString+=");";


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

}
