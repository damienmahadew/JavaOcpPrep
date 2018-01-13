package co.za.damien.chapter10.closing;

import java.sql.*;

/**
 * Must close JDBC resources,
 *
 * Connection is expensive to create, not closing causes a resource leak
 *
 * Closing a connection also closes the Statement and the ResultSet
 * Closing a Statement also closes the ResultSet
 * JDBC automatically closes a ResultSet once a new sql is run from the same statement
 */
public class ClosingDatabaseResourcesExamples{

    private void closingDbResouces() throws SQLException {
        String url = "someJdbcUrl";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("some query")) {
            //do some logic here

            //result set is closed first, then statement then connection
        }
    }
}
