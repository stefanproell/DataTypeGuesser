package at.stefanproell.Main;

import at.stefanproell.API.DataTypeDetectorAPI;
import at.stefanproell.CSV_Tools.CSV_Analyser;
import at.stefanproell.CSV_Tools.CSV_Helper;
import at.stefanproell.SQL_Tools.CreateTableStatement;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by stefan on 21.03.16.
 */
public class DataTypeDetectorMain {
    private static final Logger logger= Logger.getLogger( DataTypeDetectorMain.class.getName() );
    public static void main(String[] args) {
        String path = "/home/stefan/OwnCloud/2016-Datasets/CSV-Datasets/Testdata/datatypes.csv";

        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        CSV_Helper help = new CSV_Helper();


        File csvFile = new File(path);
        String tableName = help.prettyName(csvFile.getName());
        CSV_Analyser csv_analyser = new CSV_Analyser(csvFile);
        CreateTableStatement createTable = new CreateTableStatement();
        String createStatement = createTable.createMySQLInnoDBTable(tableName,csv_analyser.getStatistics());
        logger.info(createStatement);
        String createBeautifulStatement = createTable.createBeautifulMySQLInnoDBTable(tableName,csv_analyser.getStatistics());
        logger.info(createBeautifulStatement);

        createTable.printStatement(createBeautifulStatement);




    }
}
