import javax.swing.*;
import javax.xml.soap.SAAJResult;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by danieleinternicola on 27/04/16.
 */


public class MaxMin {

    private static Connection conn = null;
    private JTextArea testo;


    public static ResultSet selectMax (String column_name, String table) throws SQLException {

        conn = ConfConn.getConnection();


        ResultSet rs = null;
        try {

            Statement stmt = conn.createStatement();

            /* SELECT MAX(column_name) FROM table_name;*/

                    rs = stmt.executeQuery("SELECT max(" + column_name +  ") AS " + "HiVal" + " FROM " + table );
            int max = 0;
            while (rs.next()) {
                 max = rs.getInt("HiVal");

                System.out.println(max);

            }

        } catch (Exception e) {
            throw e;
        }
        conn.close();


        return rs;
    }


    public static ResultSet selectMin (String column_name, String table) throws SQLException {

        conn = ConfConn.getConnection();

        ResultSet rs = null;
        try {

            Statement stmt = conn.createStatement();

            /* SELECT MAX(column_name) FROM table_name;*/

            rs = stmt.executeQuery("SELECT min(" + column_name +  ") AS " + "LoVal" + " FROM " + table );
            int min = 0;
            while (rs.next()) {
                min  = rs.getInt("LoVal");
                System.out.println(min);

            }

        } catch (Exception e) {
            throw e;
        }
        conn.close();

        return rs;
    }




}


