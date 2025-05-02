package org.example;


import javax.swing.*;

// -------------------------------------------------------
// Final Project
// Written by: Rachel Herron (1945431) & Sabrina Robinson (1763514)

//   - [Rachel]: Implemented API integration, and database logic.
//   - [Sabrina]: Designed and implemented the GUI and internationalization features.
//
// For “Programming Patterns” Section (Section 1) – Winter 2025
// -------------------------------------------------------


// This project sees the development of a simple swing java application in which we use a database to manage our
// application's data. The data itself is pulled from various API's for the various collectible/trading card games
// that we support. These games include Magic: The gathering, Pokémon and there will be future support for Yugioh players.

public class Main {
    public static void main(String[] args) {
        Database.initializeDatabase();

        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.createConnection();

        if (dbConn.getConnection() == null) {
            System.out.println("Failed to establish a database connection.");
            return;
        }

        Database db = new Database(dbConn);
        String userLanguage = "English";
        MessageProvider mp = MessageFactory.getProvider(userLanguage);
        GUIview view = new GUIview(db, mp);
        GUIController controller = new GUIController(db, view, mp);

        SwingUtilities.invokeLater(() -> {
            view.setController(controller);
        });

    }
}