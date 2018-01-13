package co.za.damien.chapter10.resultset;

import java.sql.*;

/**
 * Scrolling result allows you to position the cursor at any row
 * There is a next() and previous() method on result set
 *      previous() goes back one row and returns true if pointing to valid data
 *
 * Also methods to start at beginning and end of the resultSet
 * first(), last() return booleans for whether they were successful in returning their respective row
 *
 * beforeFirst(), afterLast() are void methods - they do not point to rows in the resultSet
 *
 * absolute(int rowNum) - takes in the rownum you want to move the cursor to - starts at 0,
 * negative number means to start counting backwards from the end, -1 is the last row
 *
 * relative(int numberOfRows) - moves the cursor forward or backward a certain number of rows
 */
public class ScrollingResultSetExamples {

    private void scrollInsensitiveExamples() throws SQLException {
        Connection conn = DriverManager.getConnection("someConnection");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from species");
        resultSet.afterLast();
        System.out.println(resultSet.previous());
        System.out.println(resultSet.getInt(1));
        System.out.println(resultSet.first());
        System.out.println(resultSet.getInt(1));
        System.out.println(resultSet.last());
        System.out.println(resultSet.getInt(1));
        resultSet.beforeFirst();
        System.out.println(resultSet.getInt(1)); //throws SQLException
    }
}
