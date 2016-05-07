import java.sql.*;
import java.util.ArrayList;


/**
 * Created by danieleinternicola on 27/04/16.
 */


public class Insert {

    private static Connection conn = null;



    public static boolean insert (String tableName, ArrayList column , ArrayList values) throws SQLException {

        conn = ConfConn.getConnection();

        String columnString = "";

        int countColumn = 1;
        for ( Object c : column) {
            columnString += c;

            if (countColumn != column.size()){
                columnString +=  ",";
            }

            countColumn++;
        }

        System.out.println(columnString);

        String valuesString = "";


        int countValues = 1;
        for ( Object s : values) {
            valuesString += "'" +s + "'";

            if (countValues != values.size()){
                valuesString +=  ",";
            }
            countValues++;
        }

        System.out.println(valuesString);




        boolean rs = false ;
        try {

            Statement stmt = conn.createStatement();

            System.out.print("INSERT INTO " + tableName + " ( " + columnString +  " ) VALUES ( "  + valuesString + " ) ");

            /*
           to be Implemented in this way
            INSERT INTO table_name (column1,column2,column3,...)
            VALUES (value1,value2,value3,...);*/



            rs = stmt.execute("INSERT INTO " + tableName + " ( " + columnString +  " ) VALUES ( "  + valuesString + " ) ");

            System.out.println(columnString);



        } catch (Exception e) {
            System.err.println("Got an exception! ");

            System.err.println(e.getMessage());
            throw e;

        }
        conn.close();

        return rs;
    }




}


