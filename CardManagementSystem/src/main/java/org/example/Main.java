package org.example;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Database.initializeDatabase();

        DatabaseConnection dbConn = new DatabaseConnection("jdbc:sqlite:data.db");
        dbConn.createConnection();

        if (dbConn.getConnection() == null) {
            System.out.println("Failed to establish a database connection.");
            return;
        }

        Database db = new Database(dbConn);

        SwingUtilities.invokeLater(() -> new GUI(db));
    }
}