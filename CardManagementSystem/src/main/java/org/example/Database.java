package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private DatabaseConnection dbConnection;

    public Database(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

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

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
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
        return cards;
    }

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

    public void removeUser(String userID) {
        String sql = "DELETE FROM users WHERE userID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
