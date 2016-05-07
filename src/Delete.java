import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by danieleinternicola on 29/04/16.
 */
public class Delete {


    private static Connection conn = null;


        public static boolean deleteAll (String table_name) throws SQLException {

           conn = ConfConn.getConnection();




            boolean rs = false;
            try {

                Statement stmt = conn.createStatement();

          /*DELETE FROM table_name;*/

                rs = stmt.execute("DELETE "  +  " FROM " + table_name );


            } catch (Exception e) {
                throw e;
            }
            conn.close();

            return rs;
        }

    public static boolean deleteSome (String table_name, String column, String value) throws SQLException{

        conn = ConfConn.getConnection();

        boolean rs = false;

        try{

            Statement stmt = conn.createStatement();

            /*DELETE FROM table_name WHERE  some_column=some_value;*/

            rs = stmt.execute("DELETE "  +  " FROM " + table_name + " WHERE " + column + " = "+ '"'+value+ '"'  );

        } catch (Exception e) {
            throw e;
        }
        conn.close();

        return rs;


        }

    }




