package at.stefanproell.SQL_Tools;

import at.stefanproell.DataTypeDetector.ColumnMetadata;
import at.stefanproell.DataTypeDetector.DatatypeStatistics;

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
            System.out.println("Key = " + column.getKey() );

            createMySQLInnoDBTableString+=

        }
        if (createMySQLInnoDBTableString.endsWith(",")) {
            createMySQLInnoDBTableString = createMySQLInnoDBTableString.substring(0, createMySQLInnoDBTableString.length() - 1);
        }


        return createMySQLInnoDBTableString;
    }

    private String strictDataType(ColumnMetadata column){

    }

}
