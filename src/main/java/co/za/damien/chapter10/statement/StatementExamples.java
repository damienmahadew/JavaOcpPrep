package co.za.damien.chapter10.statement;

import java.sql.*;

/**
 * In order to run an SQL you need to tell a Statement about it
 *
 * Exam only focues on Statement, in the real world use PreparedStatement- increase in performance, security
 * and readability
 *
 * If you use the wrong sql statement e.g. executeUpdate("Select * from species");
 * you get a SQLException - A result was returned when none was expected
 * or if you use executeQuery("update ...") - No results were returned by the query
 */
public class StatementExamples {

    private void statementExample() throws SQLException {
        Connection conn = DriverManager.getConnection("someConnectionString");
        Statement statement = conn.createStatement(); //represents an sql statement that you want to run

        //other signatures
        statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        /**
         * EXAM:
         * First is result set type
         * By default - TYPE_FORWARD_ONLY - you can go through the data once in the order it was retrieved
         *
         * Two other modes
         * 1. TYPE_SCROLL_INSENSITIVE - static view of result set, changing data will not affect your result set
         * 2. TYPE_SCROLL_SENSITIVE - see the latest data when scrolling through dataset
         *
         * Both allow you to go through the data in any order, forward or backward, even a specific spot
         * in the result
         * difference between the two is what happens when the data changes in the actual database while
         * busy scrolling
         *
         * ResultSet Type           Can Go Backward         See latest data from DB table       Supported by most Drivers
         * ResultSet
         * .TYPE_FORWARD_ONLY       No                      No                                  Yes
         *
         * ResultSet
         * .TYPE_SCROLL_INSENSITIVE Yes                     No                                  Yes
         *
         * ResultSet
         * .TYPE_SCROLL_SENSITIVE   Yes                     Yes                                 No
         *
         * Second part is ResultSet Concurrency Mode
         * - Default is ResultSet.CONCUR_READ_ONLY - generally used
         *  means that you CANT update the result set, most of the time you will INSERT, UPDATE, DELETE
         *  to modify the DB rather than a result set
         *
         * One other mode - CONCUR_UPDATABLE - - lets you modify the database through a ResultSet
         * for exam - know name and know that is not universally supported
         *
         * If the mode you request is not available, the driver will downgrade you to CONCUR_READ_ONLY
         *
         * ResultSet Type       Can Read Data           Can Update Data         Supported by All Drivers
         * ResultSet
         * .CONCUR_READ_ONLY    Yes                     No                      Yes
         *
         * ResultSet
         * .CONCUR_UPDATABLE    Yes                     Yes                     No
         */
    }

    private void executingStatementExamples() throws SQLException {
        /**
         * INSERT, DELETE, UPDATE
         * using executeUpdate
         */
        Connection connection = DriverManager.getConnection("someConnection");
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("INSERT into SPECIES values (10, 'Deer', 3)");
        System.out.println(result); // output = 1 - because one row was affected
        result = statement.executeUpdate("UPDATE SPECIES SET NAME = '' where NAME = 'None'");
        System.out.println(result); // output = 0 => no results were affected so 0 rows
        result = statement.executeUpdate("DELETE FROM species WHERE id = 10");
        System.out.println(result); // output = 1 => row affected

        /**
         * SELECT
         * using executeQuery
         */
        ResultSet resultSet = statement.executeQuery("SELECT * FROM species");

        /**
         * using execute - can be used for INSERT, UPDATE, DELETE and SELECT
         */
        boolean isResultSet = statement.execute("someSQL");
        if (isResultSet) {
            resultSet = statement.getResultSet();
            System.out.println("ran a query with select");
        } else {
            result = statement.getUpdateCount();
        }
    }
}
