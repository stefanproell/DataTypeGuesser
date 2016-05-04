package at.stefanproell.CSV_Tools;

/**
 * Created by stefan on 03.05.16.
 */
public class CSV_Helper {

    public CSV_Helper() {
    }

    /**
     * Replace all specialcharaters and numbers with underscores
     * @param input
     * @return
     */
    public String prettyName(String input){
        return input.replaceAll("[^a-zA-Z0-9]", "_");
    }
}
