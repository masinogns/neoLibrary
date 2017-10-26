package MySQL;
import java.sql.*;
/**
 * Created by masinogns on 2017. 10. 25..
 */
public class FirstExample {

    private Connection connection;
    private String jdbcDriver, dbUrl, dbId, dbPw;

    public FirstExample(String jdbcDriver, String dbUrl, String dbId, String dbPw) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.dbId = dbId;
        this.dbPw = dbPw;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(dbUrl, dbId, dbPw);

        return connection;
    }

    public void createTable(String sql, Connection connection) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        statement.executeQuery(sql);
        System.out.println("Database created successfully...");
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Statement stmt = null;

        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("DB_ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connection.close();

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
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main



}//end FirstExample