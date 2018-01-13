package co.za.damien.chapter10.connecting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * First step - connect to DB
 * JDBC has variety of formats - 3 parts in common
 *
 * part 1 : protocol
 * part 2 : name of the database e.g. derby, mysql, postgres
 * part 3 : rest of it - database specific format, typically contains the location and name of db
 *
 * separated by colons
 *
 * e.g.
 *
 * jdbc:postgres://localhost:5432/zoo
 * jbcd:oracle:thin:@123.123.123.123:1521:zoo
 * jdbc:mysql:localhost:3306/zoo?profileSQL=true
 *
 * IMORTANT : all of them include the name of the database i.e. zoo, also the port is optional when using
 * default
 *
 */
public class ConnectingToDbExamples {

    private void databaseConnectionExample() throws SQLException {
        /**
         * Two main ways :
         * 1. Connection:DriverManager - covered in the exam - do not use this in code in prod
         *      it can store database connection info and pool connections outside the application
         * 2. DataSource - has connection pools and reuses them
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo");
        System.out.println(conn);
        /**
         * Running this throws an exception : No suitable driver for jdbc.... this means
         * that something went wrong when connecting. We did not tell java where to find the
         * database driver JAR file
         *
         * java -cp "<java_home>/db/lib/derby.jar;." ConnectingToDbExamples -- : for linus, ; for windows
         *
         * see below for signature that takes in username and password
         */
        Connection connection = DriverManager.getConnection("jdbc:derby:zoo", "username", "password");
        /**
         * EXAM: will explicitly ask you about the driver JAR if they want you to think about it
         *
         * DriverManager looks in the classpath for a class that implements java.sql.Driver to get the
         * implementation - found in META-INF/service/java.sql.Driver
         */
    }

    private void classForNameLegacy() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        /**
         * loads a class, lets the DriverManager use a Driver even if the JAR doesnt have a
         * META-INF/service/java.sql.Driver file
         *
         *                                          JDBC <= 3.0 Driver              JDBC >= 4.0 Driver
         *  Required to contain java.sql.Driver         No                              Yes
         *  Java will use file if present               Yes                             Yes
         *  Required to use Class.forName               Yes                             No
         *  Allowed to use Class.forName                Yes                             Yes
         */
    }
}
