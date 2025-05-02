package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url;
    private Connection connection;

    // Constructor to set the URL
    public DatabaseConnection(String url) {
        this.url = url;
    }

    /**
     *  This method creates and sets the connection objects
     */
    public void createConnection() {
        try {
            connection = DriverManager.getConnection(url);  // Set the connection to the field
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    /**
     * This method returns the existing connection
     * @return an open connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Method to close the connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
