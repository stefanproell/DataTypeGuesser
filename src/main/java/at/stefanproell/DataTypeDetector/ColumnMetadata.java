package at.stefanproell.DataTypeDetector;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by stefan on 22.03.16.
 */
public class ColumnMetadata {
    private static final Logger logger= Logger.getLogger( ColumnMetadata.class.getName() );
    
    private HashMap<String, Integer> dataTypes;
    

    private int rowCount;
    private int columnClount;
    private int nullCount;
    private String columnName;
    private int recordLength;

    public ColumnMetadata() {
        dataTypes = new HashMap<String, Integer>();

        this.dataTypes.put("Integer",0);
        this.dataTypes.put("Boolean",0);
        this.dataTypes.put("Date",0);
        this.dataTypes.put("Double",0);
        this.dataTypes.put("Float",0);
        this.dataTypes.put("Integer",0);
        this.dataTypes.put("Long",0);
        this.dataTypes.put("String",0);

    }


    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnClount() {
        return columnClount;
    }

    public void setColumnClount(int columnClount) {
        this.columnClount = columnClount;
    }

    public int getNullCount() {
        return nullCount;
    }

    public void setNullCount(int nullCount) {
        this.nullCount = nullCount;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(int recordLength) {
        this.recordLength = recordLength;
    }

    // ---------

    public void incrementCountInteger() {
        this.incrementDataTypeCounter("Integer");
    }


    public void incrementCountLong() {
        this.incrementDataTypeCounter("Long");
    }


    public void incrementCountFloat() {
        this.incrementDataTypeCounter("Float");
    }

    public void incrementCountDouble() {
        this.incrementDataTypeCounter("Double");
    }

    public void incrementCountBoolean() {this.incrementDataTypeCounter("Boolean");}


    public void incrementCountDate() {
        this.incrementDataTypeCounter("Date");
    }


    public void incrementCountString() {
        this.incrementDataTypeCounter("String");
    }

    public void incrementRowCount() {
        this.rowCount = rowCount+1;
    }


    public void incrementColumnClount() {
        this.columnClount = columnClount+1;
    }


    public void incrementCountNull() {
        this.nullCount = nullCount+1;
    }

    public void updateRecordLength(int length){
        if (length>this.recordLength){
            this.recordLength=length;
            logger.info("Longer record detected");
        }

    }

    /**
     * Prnt an overview
     */
    public void printColumnStatistics(){
        System.out.println("Column name: " + this.getColumnName());
        System.out.println("Number of records: " + this.rowCount);
        System.out.println("Max record length:" + this.getRecordLength());
        System.out.println("Boolean: " + this.dataTypes.get("Boolean"));
        System.out.println("Date: " + this.dataTypes.get("Date"));
        System.out.println("Double: " + this.dataTypes.get("Double"));
        System.out.println("Float: " + this.dataTypes.get("Float"));
        System.out.println("Integer: " + this.dataTypes.get("Integer"));
        System.out.println("Long " + this.dataTypes.get("Long"));
        System.out.println("String: " + this.dataTypes.get("String"));
        System.out.println("Null: " + this.getNullCount());
    }

    private void incrementDataTypeCounter(String dataType){
        this.dataTypes.put(dataType,( Integer)this.dataTypes.get(dataType) + 1);


    }

    public HashMap<String, Integer> getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(HashMap<String,Integer> dataTypes) {
        this.dataTypes = dataTypes;
    }

    /**
     * Returns the data type with the most records
     */
    public String getMostLiklyDataTypeOfColumn() {
        Map.Entry<String, Integer> maxdataTypes = null;

        for (Map.Entry<String, Integer> entry : this.getDataTypes().entrySet()) {
            if (maxdataTypes == null || entry.getValue().compareTo(maxdataTypes.getValue()) > 0) {
                maxdataTypes = entry;
            }
        }
        return maxdataTypes.getKey();
    }
}
