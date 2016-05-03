package at.stefanproell.Main;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.CSV_Tools.CSV_Analyser;
import at.stefanproell.SQL_Tools.CreateTableStatement;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorMain {
    public static void main(String[] args) {
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();


        CSV_Analyser csv_analyser = new CSV_Analyser("/home/stefan/OwnCloud/2016-Datasets/CSV-Datasets/Testdata/datatypes.csv");
        CreateTableStatement createTable = new CreateTableStatement();
        createTable.createMySQLInnoDBTable(csv_analyser.getStatistics());


    }
}
