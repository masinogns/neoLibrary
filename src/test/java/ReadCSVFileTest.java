import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 10. 31..
 */
public class ReadCSVFileTest {

    static final String DB_URL = "jdbc:mysql://183.111.183.101:3306/captanp696";
    static final String DB_ID = "captanp696";
    static final String DB_PW = "parkli**99";


    private String s = "/Users/masinogns/Atom/neoInformation/";
    private String fileType = ".csv";
    private ReadCSVFile csv;
    private ArrayList<ArrayList<String>> ret;
    private ReadXMLFile xml;
    private MySQL.createMySQL mySQL;

    public ReadCSVFileTest() {
       csv = new ReadCSVFile();
       ret = new ArrayList<ArrayList<String>>();
       xml = new ReadXMLFile();
       mySQL = new MySQL.createMySQL(DB_URL, DB_ID, DB_PW);
    }

    @Test
    public void create() throws Exception {
        String createSQL = "create table food(" +
                "id integer not null auto_increment," +
                "name varchar(100) not null," +
                "phone_number varchar(20)," +
                "address varchar(255) not null," +
                "point float," +
                "photo_url varchar(255)," +
                "PRIMARY KEY (id)" +
//                "UNIQUE KEY (address)" +
                ")";

        String tableName = "food";

        mySQL.dropTable(tableName);
        mySQL.createTable(createSQL);

    }

    @Test
    public void input1() throws Exception {

        String tableName = "food";
        String fileName = "aaa";
        String path = s + fileName + fileType;

        try {
            ret = xml.findBracket(csv.reader(path));
            mySQL.insert(tableName, ret);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}