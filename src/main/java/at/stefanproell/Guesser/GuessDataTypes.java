package at.stefanproell.Guesser;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;

import java.util.logging.Logger;

/**
 * DataTypeGuesser
 * Created by stefan
 * {MONTH_NAME_FULL} {YEAR}
 */
public class GuessDataTypes {
    private static final Logger log = Logger.getLogger(GuessDataTypes.class.getName());
    private GenericValidator validator;


    public GuessDataTypes() {
        validator = new GenericValidator();



    }

    public void guess(String input){

        if(input==null || input.equals(null)){
            log.info("Input was null");


        }else if(input =="" || input.equals("")){
            log.info("Input was empty");

        } else if(validator.isInt(input)){
            log.info("Input was Int");



        }

    }

    private boolean isAlphaNummeric(String input){
        String pattern= "^[a-zA-Z0-9]*$";
        if(input.matches(pattern)){

            log.info("Input is alphanummeric");
            return true;
        }else{
            return false;
        }
    }

    public boolean isInteger(String input){
        Integer parsedInt = null;
        try{
            parsedInt = new Integer(input);
        }catch(NumberFormatException ex){
            log.info("Not an integer");
            return false;
        }

        if(parsedInt!=null){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isDouble(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }



}
