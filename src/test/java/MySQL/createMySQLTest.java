package MySQL;

import org.junit.Test;

import java.util.ArrayList;

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
        String sql = "CREATE TABLE REGISTRATIONss " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        application.createTable(sql);
    }

    @Test
    public void dropTableTest() throws Exception {
        String tableName = "REGISTRATIONss";

        application.dropTable(tableName);
    }

    @Test
    public void insertTableTest() throws Exception {
        ArrayList<ArrayList<String>> Data = null;
        String tableName = "";

        application.insertDataToTable(tableName, Data);
    }
}