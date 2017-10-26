package MySQL;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 10. 26..
 */
public class createMySQLTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://183.111.183.101:3306/captanp696";

    //  Database credentials
    static final String DB_ID = "captanp696";
    static final String DB_PW = "parkli**99";

    createMySQL application;

    public createMySQLTest() {
        application = new createMySQL(DB_URL, DB_ID, DB_PW);
    }

    @Test
    public void createTableTest() throws Exception{
        String sql = "CREATE TABLE aaa " +
                "(id INTEGER not NULL auto_increment, " +
                " firstname VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " secondname varchar(255), " +
                " PRIMARY KEY ( id ))";

        application.createTable(sql);
    }

    @Test
    public void dropTableTest() throws Exception {
        String tableName = "aaa";

        application.dropTable(tableName);
    }

    @Test
    public void insertTableTest() throws Exception {
        ArrayList<ArrayList<String>> Data = new ArrayList<ArrayList<String>>();

        ArrayList<String> items = new ArrayList<String>(Arrays.asList(
                "aaa","aaa","aaa"
        ));
        ArrayList<String> items1 = new ArrayList<String>(Arrays.asList(
                "eee","eee","eee"
        ));
        ArrayList<String> items2 = new ArrayList<String>(Arrays.asList(
                "ccc","ccc","ccc"
        ));

        Data.add(items); Data.add(items1); Data.add(items2);

        String tableName = "aaa";

        // ArrayList<String>의 길이는 3으로 고정되어있어서 에러없이 돌아간다.
        application.insert(tableName, Data);
    }

}