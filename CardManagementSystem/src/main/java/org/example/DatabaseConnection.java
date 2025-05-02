package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance; // Singleton instance
    private static final String DB_URL = "jdbc:sqlite:data.db"; // Fixed database URL
    private Connection connection;

    // Private constructor to prevent external instantiation
    DatabaseConnection() {
        createConnection();
    }

    // Singleton instance getter (Lazy Initialization)
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Creates and sets the connection object
     */
    void createConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL); // Uses predefined URL
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    /**
     * Returns the existing connection
     * @return an open connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the connection and resets the instance
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
                instance = null; // Allow reinitialization if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}