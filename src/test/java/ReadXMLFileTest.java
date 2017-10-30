import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 10. 26..
 */
public class ReadXMLFileTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://183.111.183.101:3306/captanp696";

    //  Database credentials
    static final String DB_ID = "captanp696";
    static final String DB_PW = "parkli**99";

    private ArrayList<ArrayList<String>> result;


    @Test
    public void sql() throws Exception {
        MySQL.createMySQL mySQL = new MySQL.createMySQL(DB_URL, DB_ID, DB_PW);

        String createSQL = "create table house(" +
                "id integer not null auto_increment," +
                "name varchar(100) not null," +
                "phone_number varchar(20)," +
                "address varchar(255) not null," +
                "point float," +
                "photo_url varchar(255)," +
                "PRIMARY KEY (id)" +
//                "UNIQUE KEY (address)" +
                ")";

        String tableName = "house";

        mySQL.dropTable(tableName);
        mySQL.createTable(createSQL);

    }

    /**
     * 끝냈다 서귀포시 민박펜션 업소
     *         String filePath = "/Users/masinogns/Atom/neoInformation/서귀포시_민박펜션업소.xml";
     *         "업소명", "연락처", "소재지도로명주소"
     *
     * 끝냈다 서귀포시 휴양펜션 업소
     *         String filePath = "/Users/masinogns/Atom/neoInformation/서귀포시_휴양펜션업등록현황.xml";
     *         "업소명", "연락처", "소재지도로명주소"
     *
     * 끝냈다 제주도 관광숙박업소
     *         String filePath = "/Users/masinogns/Atom/neoInformation/제주도_관광숙박업소현황.xml";
     *         "호텔명", "전화번호", "주소"
     *
     * 하는중 제주시 일반숙박업 현황
     *         String filePath = "/Users/masinogns/Atom/neoInformation/제주시_일반숙박업현황.xml";
     *         "업소명", "소재지전화", "업소소재지_도로명_"
     * @throws Exception
     */

    @Test
    public void input1() throws Exception {
        // 끝냈다 서귀포시 민박펜션 업소    "업소명", "연락처", "소재지도로명주소"
        // 끝냈다 서귀포시 휴양펜션
        String filePath = "/Users/masinogns/Atom/neoInformation/서귀포시_민박펜션업소.xml";
        String rootTagName = "Row";
        ArrayList<String> waanaTagNames = new ArrayList<String>(Arrays.asList(
                "업소명", "연락처", "소재지도로명주소"
        ));

        inputData(filePath, rootTagName, waanaTagNames);
    }

    @Test
    public void input2() throws Exception {
        String filePath = "/Users/masinogns/Atom/neoInformation/서귀포시_휴양펜션업등록현황.xml";
        String rootTagName = "Row";
        ArrayList<String> waanaTagNames = new ArrayList<String>(Arrays.asList(
                "업소명", "연락처", "소재지도로명주소"
        ));

        inputData(filePath, rootTagName, waanaTagNames);
    }

    @Test
    public void input3() throws Exception {
        String filePath = "/Users/masinogns/Atom/neoInformation/제주시_일반숙박업현황.xml";
        String rootTagName = "Row";
        ArrayList<String> waanaTagNames = new ArrayList<String>(Arrays.asList(
                "업소명", "소재지전화", "업소소재지_도로명_"
        ));

        inputData(filePath, rootTagName, waanaTagNames);
    }

    @Test
    public void input4() throws Exception {
        String filePath = "/Users/masinogns/Atom/neoInformation/제주도_관광숙박업소현황.xml";
        String rootTagName = "Row";
        ArrayList<String> waanaTagNames = new ArrayList<String>(Arrays.asList(
                "호텔명", "전화번호", "주소"
        ));

        inputData(filePath, rootTagName, waanaTagNames);
    }

    private void inputData(String filePath, String rootTagName, ArrayList<String> waanaTagNames) throws SQLException {
        ArrayList<ArrayList<String>> result;
        ReadXMLFile application = new ReadXMLFile(filePath, rootTagName, waanaTagNames);
        result = application.getValue();
        result = application.findBracket(result);

        MySQL.createMySQL mySQL = new MySQL.createMySQL(DB_URL, DB_ID, DB_PW);

        String tableName = "house";
        mySQL.insert(tableName, result);
    }
}

//    SELECT address, COUNT( * ) AS n
//        FROM house
//        GROUP BY address
//        HAVING 1 < n;