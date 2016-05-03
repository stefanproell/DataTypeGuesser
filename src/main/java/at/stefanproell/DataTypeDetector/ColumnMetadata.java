package at.stefanproell.DataTypeDetector;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by stefan on 22.03.16.
 */
public class ColumnMetadata {
    private static final Logger logger= Logger.getLogger( ColumnMetadata.class.getName() );
    
    private Map dataTypes = new HashMap<String, Integer>();
    
    
    private int countInteger;
    private int countLong;
    private int countFloat;
    private int countDouble;
    private int countBoolean;
    private int countDate;
    private int countString;
    private int rowCount;
    private int columnClount;
    private int nullCount;
    private String columnName;
    private int recordLength;

    public ColumnMetadata() {
        this.dataTypes.put("Integer",0);
        this.dataTypes.put("Boolean",0);
        this.dataTypes.put("Date",0);
        this.dataTypes.put("Double",0);
        this.dataTypes.put("Float",0);
        this.dataTypes.put("Integer",0);
        this.dataTypes.put("Long",0);
        this.dataTypes.put("String",0);
        
        
        this.countInteger = 0;
        this.countLong = 0;
        this.countFloat = 0;
        this.countDouble = 0;
        this.countBoolean = 0;
        this.countDate = 0;
        this.countString = 0;
        this.rowCount = 0;
        this.columnClount = 0;
        this.nullCount = 0;
        this.recordLength = 0;
    }

    public int getCountInteger() {
        return countInteger;
    }

    public void setCountInteger(int countInteger) {
        this.countInteger = countInteger;
    }

    public int getCountLong() {
        return countLong;
    }

    public void setCountLong(int countLong) {
        this.countLong = countLong;
    }

    public int getCountFloat() {
        return countFloat;
    }

    public void setCountFloat(int countFloat) {
        this.countFloat = countFloat;
    }

    public int getCountDouble() {
        return countDouble;
    }

    public void setCountDouble(int countDouble) {
        this.countDouble = countDouble;
    }

    public int getCountBoolean() {
        return countBoolean;
    }

    public void setCountBoolean(int countBoolean) {
        this.countBoolean = countBoolean;
    }

    public int getCountDate() {
        return countDate;
    }

    public void setCountDate(int countDate) {
        this.countDate = countDate;
    }

    public int getCountString() {
        return countString;
    }

    public void setCountString(int countString) {
        this.countString = countString;
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
        this.countInteger = countInteger+1;
    }


    public void incrementCountLong() {
        this.countLong = countLong+1;
    }


    public void incrementCountFloat() {
        this.countFloat = countFloat+1;
    }

    public void incrementCountDouble() {
        this.countDouble = countDouble+1;
    }

    public void incrementCountBoolean() {
        this.countBoolean = countBoolean+1;
    }


    public void incrementCountDate() {
        this.countDate = countDate+1;
    }


    public void incrementCountString() {
        this.countString = countString+1;
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

    public void printColumnStatistics(){
        System.out.println("Column name: " + this.getColumnName());
        System.out.println("Number of records: " + this.columnClount);
        System.out.println("Max record length:" + this.getRecordLength());
        System.out.println("Boolean: " + this.getCountBoolean());
        System.out.println("Date: " + this.getCountDate());
        System.out.println("Double: " + this.getCountDouble());
        System.out.println("Float: " + this.getCountFloat());
        System.out.println("Integer: " + this.getCountInteger());
        System.out.println("Long " + this.getCountLong());
        System.out.println("String: " + this.getCountString());
        System.out.println("Null: " + this.getNullCount());
    }



}
