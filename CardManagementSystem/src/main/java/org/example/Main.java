package org.example;


import javax.swing.*;

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