import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by danieleinternicola on 28/04/16.
 */


    public class Update {

        private static Connection conn = null;



        public static boolean update (String tableName, ArrayList column, ArrayList value, String fromColumn, String fromValue) throws SQLException {

            conn = ConfConn.getConnection();



            String valuesString = " ";



            if (column.size() != value.size() ){

            }

            for (  int i=1; i<=column.size(); i++)  {

                valuesString +=  column.get(i-1).toString() + "="  + "\"" + value.get(i-1).toString()+ "\"" ;

                if (i != column.size()){
                    valuesString += ",";
                }

            }

            System.out.println("Updated " + valuesString);


            boolean rs = false;
            try {

                Statement stmt = conn.createStatement();


                System.out.print("UPDATE " + tableName +  " SET " + valuesString +" WHERE " + fromColumn + "=" +  '"' +fromValue + '"');

                /*UPDATE table_name
                SET column1=value1,column2=value2,...
                WHERE some_column=some_value;  */

                rs = stmt.execute("UPDATE " + tableName +  " SET " + valuesString +" WHERE "  + fromColumn + "=" + '"'+fromValue+'"' );



            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
                throw e;

            }
            conn.close();

            return rs;
        }




    }




