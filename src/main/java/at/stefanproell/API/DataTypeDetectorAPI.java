package at.stefanproell.API;

import com.google.common.base.Strings;
import com.google.common.primitives.*;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorAPI {

    public DataTypeDetectorAPI() {
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

        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(inputString);
        Date checkDate = null;
        for(DateGroup group:groups) {
            List dates = group.getDates();
            checkDate = (Date) dates.get(0);


         //   int line = group.getLine();
         //   int column = group.getPosition();
         //   String matchingValue = group.getText();
         //   String syntaxTree = group.getSyntaxTree().toStringTree();
         //   Map parseMap = group.getParseLocations();
         //   boolean isRecurreing = group.isRecurring();
         //   Date recursUntil = group.getRecursUntil();
        }

        if(checkDate!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean isString(String inputString){

        if (isBoolean(inputString) == true || isDate(inputString) == true || isDouble(inputString) == true || isFloat(inputString) == true
                || isInteger(inputString) == true || isLong(inputString) == true ){
            return false;
        }else{
            return true;
        }
    }



}
