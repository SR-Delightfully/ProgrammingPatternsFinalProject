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
        String dbPath = "jdbc:sqlite:data.db";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPath);
            System.out.println("Connected to database!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

}
