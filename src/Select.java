import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by danieleinternicola on 27/04/16.
 */


public class Select {

    private static Connection conn = null;



    public static ResultSet setSel (String target, String table) throws SQLException {

        conn = ConfConn.getConnection();

        ResultSet rs = null;
        try {

            Statement stmt = conn.createStatement();

            /* SELECT column_name,column_name
             FROM table_name;*/

            rs = stmt.executeQuery("SELECT " + target +  " FROM " + table );
            while (rs.next()) {
                String name = rs.getString(target);

                //JOptionPane.showMessageDialog(null, name);

                System.out.println(name);

            }

        } catch (Exception e) {
            throw e;
        }
        conn.close();

        return rs;
    }




}


