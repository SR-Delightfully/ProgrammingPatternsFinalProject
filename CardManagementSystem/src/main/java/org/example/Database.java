package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    private DatabaseConnection dbConnection;

    public Database(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Used to initialize the database, will test the connection and throw an error if the connection is not safe
     */
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db")) {
            Statement stmt = conn.createStatement();


            String createCards = """
            CREATE TABLE IF NOT EXISTS cards (
                cardID TEXT PRIMARY KEY,
                gameType TEXT,
                cardName TEXT,
                description TEXT,
                releaseYear INTEGER,
                price REAL
            );
        """;


            String createUsers = """
            CREATE TABLE IF NOT EXISTS users (
                userID TEXT PRIMARY KEY,
                userName TEXT UNIQUE NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            );
        """;

            String createDecks = """
            CREATE TABLE IF NOT EXISTS decks (
                deckID TEXT PRIMARY KEY,
                deckType TEXT
            );
        """;

        stmt.execute(createUsers);
        stmt.execute(createCards);
        stmt.execute(createDecks);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to validate the user's login information
     * @param username refers to the username being tested
     * @param password refers to the password being tested
     * @return returns true if valid, false if not
     */
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // user found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Used to check if a record with the user's name already exists
     * @param username refers to the username in question
     * @return returns true if they exist, false if not
     */
    public boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE userName = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // user found
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // return true to avoid duplicate registration on error
        }
    }


    // --------- CRUD Operations for Card ---------

    /**
     * Used to ease the process of creating a card
     * @param card refers to an instance of the card class that you would like to add as a record
     */
    public void addCard(Card card) {
        String sql = "INSERT INTO cards (cardID, gameType, cardName, description, releaseYear, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, card.getId());
            pstmt.setString(2, card.getGameType().toString());  // Assuming GameType is an Enum
            pstmt.setString(3, card.getCardName());
            pstmt.setString(4, card.getDescription());
            pstmt.setString(5, card.getReleaseDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to get a list of all the cards that were received and parsed from the API
     * @return returns a List of Card class instances that have been created from the data gathered from the aPI
     */
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards";

        // Get the connection explicitly
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("cardID");
                String gameTypeStr = rs.getString("gameType");
                String name = rs.getString("cardName");
                String description = rs.getString("description");
                String releaseDate = rs.getString("releaseYear");
                double price = rs.getDouble("price");

                GameType gameType = switch (gameTypeStr.toUpperCase()) {
                    case "POKEMON" -> GameType.POKEMON;
                    case "MTG", "MAGIC" -> GameType.MTG;
                    default -> throw new IllegalArgumentException("Unknown game type: " + gameTypeStr);
                };

                Card card = new Card(id, gameType, name, description, releaseDate);
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the list of cards
        return cards;
    }

    /**
     * Used to update a card record
     * @param cardID refers to the id of the record you would like to update
     * @param updatedCard refers to the new card instance
     */
    public void updateCard(String cardID, Card updatedCard) {
        String sql = "UPDATE cards SET cardName = ?, description = ?, releaseYear = ?, price = ? WHERE cardID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, updatedCard.getCardName());
            pstmt.setString(2, updatedCard.getDescription());
            pstmt.setString(3, updatedCard.getReleaseDate());
            pstmt.setString(5, cardID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to remove a card from the database
     * @param cardID refers to the id of the card you would like to delete
     */
    public void removeCard(String cardID) {
        String sql = "DELETE FROM cards WHERE cardID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cardID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --------- CRUD Operations for Deck ---------

    /**
     * Used to ease the process of creating a deck
     * @param deck refers to an instance of the deck class that you would like to add as a record
     */
    public void addDeck(Deck deck) {
        String sql = "INSERT INTO decks (deckID, deckType) VALUES (?, ?)";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, deck.getDeckID());
            pstmt.setString(2, deck.getDeckType().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to remove a card from the database
     * @param deckID refers to the id of the deck you would like to delete
     */
    public void updateDeck(String deckID, Deck updatedDeck) {
        String sql = "UPDATE decks SET deckType = ? WHERE deckID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, updatedDeck.getDeckType().toString());
            pstmt.setString(2, deckID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to remove a deck from the database
     * @param deckID refers to the id of the deck you would like to delete
     */
    public void removeDeck(String deckID) {
        String sql = "DELETE FROM decks WHERE deckID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, deckID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --------- CRUD Operations for User ---------

    /**
     * Used to ease the process of creating a user
     * @param user refers to an instance of the user class that you would like to add as a record
     */
    public void addUser(User user) {
        String sql = "INSERT INTO users (userID, userName, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getEmailAddress());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to update a user from the database
     * @param userID refers to the id of the user you would like to delete
     */
    public void updateUser(String userID, User updatedUser) {
        String sql = "UPDATE users SET userName = ?, email = ?, password = ? WHERE userID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, updatedUser.getUserName());
            pstmt.setString(2, updatedUser.getEmailAddress());
            pstmt.setString(3, updatedUser.getPassword());
            pstmt.setString(4, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to remove a user from the database
     * @param userID refers to the id of the user you would like to delete
     */
    public void removeUser(String userID) {
        String sql = "DELETE FROM users WHERE userID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ------- Sorting Methods Using Streams -------

    // Sort cards by name (Alphabetically)
    public List<Card> getCardsSortedByName() {
        return getAllCards().stream()
                .sorted(Comparator.comparing(Card::getCardName))
                .collect(Collectors.toList());
    }

    // Sort cards by game type (Alphabetically)
    public List<Card> getCardsSortedByGameType() {
        return getAllCards().stream()
                .sorted(Comparator.comparing(Card::getGameType))
                .collect(Collectors.toList());
    }

    // Sort cards by release year (Oldest to Newest)
    public List<Card> getCardsSortedByYearAsc() {
        return getAllCards().stream()
                .sorted(Comparator.comparing(Card::getReleaseDate))
                .collect(Collectors.toList());
    }

    // Sort cards by release year (Newest to Oldest)
    public List<Card> getCardsSortedByYearDesc() {
        return getAllCards().stream()
                .sorted(Comparator.comparing(Card::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }
    public List<Card> searchCardsByName(String keyword) {
        return getAllCards().stream()
                .filter(card -> card.getCardName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Card> filterCardsByGameType(GameType gameType) {
        return getAllCards().stream()
                .filter(card -> card.getGameType() == gameType)
                .collect(Collectors.toList());
    }

    public List<Card> filterCardsByYear(String year) {
        return getAllCards().stream()
                .filter(card -> card.getReleaseDate().equals(year))
                .collect(Collectors.toList());
    }




}
