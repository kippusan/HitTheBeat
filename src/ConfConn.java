
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**
 * Created by danieleinternicola on 21/04/16.
 */

/* This class is the one responsible for DB connection*/
public class ConfConn {
    private static Connection conn;
    private static String url1 = "jdbc:mysql://localhost:3306/World";
    private static String user = "root";
    private static String password = "a1a2a3a4a5aaa";


    public static Connection getConnection() throws SQLException{

        try {
            // try to connect

            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected");

            }

            //throws exception if the connection has problems
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }

        return conn;
    }
}


