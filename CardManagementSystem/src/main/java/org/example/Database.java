package org.example;

import java.sql.*;

public class Database {
    private DatabaseConnection dbConnection;

    public Database(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // --------- CRUD Operations for Card ---------

    public void addCard(Card card) {
        String sql = "INSERT INTO cards (cardID, gameType, cardName, description, releaseYear, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, card.getCardID());
            pstmt.setString(2, card.getGameType().toString());  // Assuming GameType is an Enum
            pstmt.setString(3, card.getCardName());
            pstmt.setString(4, card.getDescription());
            pstmt.setInt(5, card.getReleaseYear());
            pstmt.setDouble(6, card.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCard(String cardID, Card updatedCard) {
        String sql = "UPDATE cards SET cardName = ?, description = ?, releaseYear = ?, price = ? WHERE cardID = ?";
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, updatedCard.getCardName());
            pstmt.setString(2, updatedCard.getDescription());
            pstmt.setInt(3, updatedCard.getReleaseYear());
            pstmt.setDouble(4, updatedCard.getPrice());
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
