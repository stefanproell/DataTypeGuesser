package at.stefanproell.Main;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.CSV_Tools.CSV_Analyser;
import at.stefanproell.CSV_Tools.CSV_Helper;
import at.stefanproell.SQL_Tools.CreateTableStatement;

import java.io.File;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorMain {
    public static void main(String[] args) {
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        CSV_Helper help = new CSV_Helper();

        String path = "/home/stefan/OwnCloud/2016-Datasets/CSV-Datasets/Testdata/datatypes.csv";

        File csvFile = new File(path);
        String tableName = help.prettyName(csvFile.getName());
        CSV_Analyser csv_analyser = new CSV_Analyser(csvFile);
        CreateTableStatement createTable = new CreateTableStatement();
        String create = createTable.createMySQLInnoDBTable(tableName,csv_analyser.getStatistics());
        System.out.println(create);



    }
}
