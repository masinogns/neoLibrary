package MySQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by masinogns on 2017. 10. 26..
 */
public class createMySQL {
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL, DB_ID, DB_PW;
    private Connection conn = null;
    private Statement stmt = null;

    public createMySQL(String DB_URL, String DB_ID, String DB_PW) {
        this.DB_ID = DB_ID;
        this.DB_PW = DB_PW;
        this.DB_URL = DB_URL;
    }

    public void createTable(String sql){
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("선택된 데이터베이스와 연결 중입니다...");
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
            System.out.println("데이터베이스에 성공적으로 연결되었습니다...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName

        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){

            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();

            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end createTable

    public void dropTable(String tableName) {
        String sql = "DROP TABLE ";
        sql += tableName;

        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("선택된 데이터베이스와 연결 중입니다...");
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
            System.out.println("데이터베이스에 성공적으로 연결되었습니다...");

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName

        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){

            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();

            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end dropTable

    public void insertDataToTable(String tableName, ArrayList<ArrayList<String>> data) {


        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Registration " +
                    "VALUES (100, 'Zara', 'Ali', 18)";

            for (ArrayList<String> oneThing : data){
                for (String item : oneThing){
                    System.out.print(item+" ");
                }
                System.out.println();
            }

            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    public void insert(String tableName, ArrayList<ArrayList<String>> data) throws SQLException {
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO "+tableName
                + "(name, phone_number, address) VALUES"
                + "(?,?,?)";

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("선택된 데이터베이스와 연결 중입니다...");
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
            System.out.println("데이터베이스에 성공적으로 연결되었습니다...");

            preparedStatement = conn.prepareStatement(insertTableSQL);

            for (ArrayList<String> oneThing : data){
                String one = null;
                String two = null;
                String three = null;

                one = oneThing.get(0);
                two = oneThing.get(1);
                three = oneThing.get(2);

                preparedStatement.setString(1, one);
                preparedStatement.setString(2, two);
                preparedStatement.setString(3, three);

                // execute insert SQL stetement
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

