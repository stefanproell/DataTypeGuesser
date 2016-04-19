package at.stefanproell.API;

import at.stefanproell.CSV_Tools.CSV_Analyser;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorMain {
    public static void main(String[] args) {
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        api.isDate("16.03.2016");

        CSV_Analyser csv_analyser = new CSV_Analyser("/home/stefan/ownCloud/2016-Datasets/CSV-Datasets/Testdata/datatypes.csv");


    }
}
