package co.za.damien.chapter10.resultset;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Most common type of result set is TYPE_FORWARD_ONLY
 */
public class ResultSetExamples {

    private void readingFromResultSetExamples_forward_only() throws SQLException {
        /**
         * Normally write a loop to traverse through each result
         *
         * Important to call rs.next first, if there is no data and you call a getter on rs then you will
         * get an SQLException
         */
        Connection connection = DriverManager.getConnection("someConnectionString");
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        Map<Integer, String> idToNameMap = new HashMap<>();
        ResultSet rs = statement.executeQuery("select id, name, from species");
        while(rs.next()) {
            int id = rs.getInt("id"); //you can use index instead of column name, starts from 1
            String name = rs.getString("name");
            idToNameMap.put(id, name);
        }

        //if you want just one row
        if (rs.next()) {
            System.out.println(rs.getInt(1)); //Accessing a column that does not exist throws
            //an SQLException
        }
        System.out.println(idToNameMap);
    }

    private void gettingDataForAColumn() throws SQLException {
        /**
         * Lots of ways to get data - starts with get_ on the ResultSet
         * What to know for exam (only first two columns):
         *
         * Method Name          Return Type             Example Database Type - varies from DB to DB
         * getBoolean           Boolean                 BOOLEAN
         * getDate              java.sql.Date           DATE
         * getDouble            double                  DOUBLE
         * getInt               int                     INTEGER
         * getLong              long                    BIGINT
         * getObject            Object                  Any type
         * getString            String                  CHAR, VARCHAR
         * getTime              java.sql.Time           TIME
         * getTimeStamp         java.sql.TimeStamp      TIMESTAMP
         *
         * no getChar available
         */

        /**
         * Suppose a date is 2001-05-03 02:15
         */
        Statement statement = null;
        ResultSet rs = statement.executeQuery("select date from bla where id = 1");
        if (rs.next()) {
            java.sql.Date sqlDate = rs.getDate(1);
            LocalDate date = sqlDate.toLocalDate();
            System.out.println(date); // only date part of the value is taken // 2001-05-03

            java.sql.Time sqlTime = rs.getTime(1);
            LocalTime time = sqlTime.toLocalTime();
            System.out.println(time); // only time part of the value is taken // 02:15

            java.sql.Timestamp timestamp = rs.getTimestamp(1);
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            System.out.println(dateTime); // date and time part of the value is taken // 2001-05-03T02:15
        }
        /**
         * getObject can return any type, for primitive, it uses the wrapper class
         */
        while (rs.next()) {
            Object idField = rs.getObject("id");
            if (idField instanceof Integer) {
                int id = (Integer) idField;
            }
        }

    }
}
