package MySQL;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 10. 26..
 */
public class FirstExampleTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://183.111.183.101:3306/captanp696";

    //  Database credentials
    static final String DB_ID = "captanp696";
    static final String DB_PW = "parkli**99";

    @Test
    public void createTableTest() throws Exception{
        FirstExample application = new FirstExample(JDBC_DRIVER, DB_URL, DB_ID, DB_PW);

        String sql = "CREATE TABLE aaa" +
                "(" +
                "id INT NOT NULL ," +
                "age INT NOT NULL ," +
                "first VARCHAR (255) ," +
                "last VARCHAR (255) ," +
                "PRIMARY KEY (id) " +
                ")";

        application.createTable(sql, application.getConnection());
    }

}