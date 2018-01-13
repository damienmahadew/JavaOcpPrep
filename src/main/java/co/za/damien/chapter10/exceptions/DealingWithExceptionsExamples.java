package co.za.damien.chapter10.exceptions;

import java.sql.*;

/**
 * Created by damien.mahadew on 2018-01-13.
 */
public class DealingWithExceptionsExamples {
    private void catchingExceptionsExamples() {
        String url = "someJdbcUrl";

        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from sepicies")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }
}
