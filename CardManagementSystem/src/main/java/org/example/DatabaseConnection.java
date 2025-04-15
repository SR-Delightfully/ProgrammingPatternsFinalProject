package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /**
     * connects to the SQLite database
     * @return a Connection object to the database
     */
    public static Connection connect() {
        String basePath = "jdbc:sqlite:src/main/resources/database/";

        String dbBasePath = basePath + "data.db";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbBasePath);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
