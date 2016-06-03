package at.stefanproell.API;

import at.stefanproell.CSV_Tools.CSV_Analyser;
import at.stefanproell.DataTypeDetector.DatatypeStatistics;
import com.google.common.primitives.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorAPI {
    private ArrayList<String> listOfDateFormats;

    public DataTypeDetectorAPI() {
        listOfDateFormats = new ArrayList<String>();
        listOfDateFormats.add("yyyy mm dd");
        listOfDateFormats.add("yyyy-mm-dd");
        listOfDateFormats.add("yyyy/mm/dd");
        listOfDateFormats.add("dd/mm/yyyy");
        listOfDateFormats.add("dd mm dd"); // e.g. 17 05 2016
        listOfDateFormats.add("dd/MM/YYYY HH:mm:ss");
        listOfDateFormats.add("MM/dd/yyyy HH:mm:ss"); // e.g. "11/15/2013 08:00:00"



    }


    public boolean isInteger(String inputString){
        Integer intValue = Ints.tryParse(inputString);
        if (intValue==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean isLong(String inputString){
        Long longValue = Longs.tryParse(inputString);
        if (longValue==null){
            return false;
        }else{
            return true;
        }
    }
    public boolean isFloat(String inputString){
        Float floatValue = Floats.tryParse(inputString);
        if (floatValue==null){
            return false;
        }else{
            return true;
        }
    }
    public boolean isDouble(String inputString){
        Double doubleValue = Doubles.tryParse(inputString);
        if (doubleValue==null){
            return false;
        }else{
            return true;
        }

    }

    public boolean isBoolean(String inputString){


        return "true".equalsIgnoreCase(inputString) || "false".equalsIgnoreCase(inputString)
                || "yes".equalsIgnoreCase(inputString) || "no".equalsIgnoreCase(inputString)
                || "on".equalsIgnoreCase(inputString) || "off".equalsIgnoreCase(inputString)
                || "0".equalsIgnoreCase(inputString) || "1".equalsIgnoreCase(inputString) ;


    }


    public boolean isDate(String inputString){
        for(String dateFormat : listOfDateFormats){
            if (this.parseDate(inputString,dateFormat)!=null){
                return true;
            }
        }
        return false;
    }

    public boolean isString(String inputString){

        if (isBoolean(inputString) == true || isDate(inputString) == true || isDouble(inputString) == true || isFloat(inputString) == true
                || isInteger(inputString) == true || isLong(inputString) == true ){
            return false;
        }else{
            return true;
        }
    }


    protected org.joda.time.DateTime parseDate(String inputString, String format) {
        org.joda.time.DateTime date = null;
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
            date = fmt.parseDateTime(inputString);
        } catch (Exception e) {
        }
        return date;
    }

    public String getDataType(String inputString){
        if (inputString == null || inputString.equalsIgnoreCase("")){
            return "Null";
        } else if (this.isDate(inputString)){
            return "Date";
        } else if (this.isInteger(inputString)){
            return "Integer";
        } else if (this.isDouble(inputString)){
            return "Double";
        } else if (this.isBoolean(inputString)){
            return "Boolean";
        } else if (this.isFloat(inputString)){
            return "Float";
        } else if (this.isLong(inputString)){
            return "Long";
        } else if (this.isString(inputString)){
            return "String";
        } else {
            return "UNDEFINED";
        }

    }


}
