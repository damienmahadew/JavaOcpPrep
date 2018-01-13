package co.za.damien.chapter10.introduction;

import java.sql.*;

/**
 * Things to know in the exam:
 * 1. Understand the URL set when connecting to the DB
 * 2. Need to know how DriveManager works (normally use datasource)
 * 3. You can skip the Class.forName on a JDBC 3.0 driver that was ahead of its time including the
 * java.sql.Driver file in addition to the public driver class. For the exam assume its mandatory. If you
 * are using a datasource then this wont apply
 * 4. The exam talks about statement, PreparedStatement should be used in practice
 * 5. We normally use default values for the result set modes, you will need to know the alternatives by heart
 * 6. You will need to know scrollable
 * <p>
 * Two main ways to access a database
 * 1. Java Database Connectivity Language (JDBC) - accesses data as rows and columns, JDBC is the api
 * covered in this chapter
 * <p>
 * 2. Java Persistence API (JPA) - acces data through objects using a concept called ORM - not in exam
 * <p>
 * Also get NoSQL - stores data in forms other than tables
 * <p>
 * Java has an embedded database called JavaDB - open source Derby database - comes with JDK
 */
public class JdbcIntroduction {

    private void typesOfStatements() {
        /**
         * INSERT
         *
         * SELECT
         *
         * UPDATE
         *
         * DELETE
         */
    }

    private void ketInterfacesFromJDBC() throws SQLException {
        /**
         * Concrete classes comes from the JDBC driver, each db has different jar file with those classes
         * e.g. postgresql.jar, mysql-connector-java.jar
         *
         * Do not need to know about drivers for exam nor the implementations, just the interfaces
         *
         * 1. Driver -knows how to get a connection to the database
         *
         * 2. Connection - knows how to communicate with the database
         *
         * 3. Statement - knows how to run the SQL
         *
         * 4. ResultSet - knows what was returned by the SQL query
         *
         * All db classes are in the package java.sql
         */

        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select name from animal")) {

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
