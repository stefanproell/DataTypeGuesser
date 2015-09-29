package at.stefanproell.Guesser;

import java.util.logging.Logger;

/**
 * DataTypeGuesser
 * Created by stefan
 * {MONTH_NAME_FULL} {YEAR}
 */
public class GuessDataTypes {
    private static final Logger log = Logger.getLogger(GuessDataTypes.class.getName());

    public GuessDataTypes() {
    }

    public void guess(String input){

        if(input==null || input.equals(null)){
            log.info("Input was null");


        }else if(input =="" || input.equals("")){
            log.info("Input was empty");

        } else {
            String pattern= "^[a-zA-Z0-9]*$";
            if(input.matches(pattern)){
                log.info("Input is alphanummeric");
            }

        }

    }
}
