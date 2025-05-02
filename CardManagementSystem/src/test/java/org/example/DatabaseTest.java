package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private static Connection connection;
    private static Database database;

    @BeforeAll
    static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (userID TEXT PRIMARY KEY, userName TEXT UNIQUE NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL)");
            stmt.execute("CREATE TABLE cards (cardID TEXT PRIMARY KEY, gameType TEXT, cardName TEXT, description TEXT, releaseYear INTEGER, price REAL)");
            stmt.execute("CREATE TABLE decks (deckID TEXT PRIMARY KEY, deckType TEXT)");
        }

        database = new Database(new TestDatabaseConnection(connection));
    }

    @AfterAll
    static void teardown() throws SQLException {
        connection.close();
    }

    @AfterEach
    void cleanup() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
            stmt.execute("DELETE FROM cards");
            stmt.execute("DELETE FROM decks");
        }
    }

    @Test
    void testAddAndRetrieveUser() {
        User user = new User("u1", "john", "john@example.com", "pass123");
        database.addUser(user);

        assertTrue(database.userExists("john"));
        assertTrue(database.validateLogin("john", "pass123"));
        assertFalse(database.validateLogin("john", "wrong"));
    }

    @Test
    void testUpdateUser() {
        User user = new User("u2", "maria", "maria@example.com", "abc");
        database.addUser(user);

        User updated = new User("u2", "maria", "maria@new.com", "newpass");
        database.updateUser("u2", updated);

        assertTrue(database.validateLogin("maria", "newpass"));
    }

    @Test
    void testRemoveUser() {
        User user = new User("u3", "anna", "anna@example.com", "pw");
        database.addUser(user);

        database.removeUser("u3");
        assertFalse(database.userExists("anna"));
    }

    @Test
    void testAddCard() {
        Card card = new Card("c1", GameType.POKEMON, "Charmander", "Fire type", "1996");
        database.addCard(card);

        List<Card> cards = database.getAllCards();
        assertTrue(cards.stream().anyMatch(c -> c.getCardName().equals("Charmander")));
    }

    @Test
    void testUpdateCard() {
        Card card = new Card("c2", GameType.MTG, "Shivan Dragon", "Fire breathing", "1994");
        database.addCard(card);

        Card updatedCard = new Card("c2", GameType.MTG, "Shivan Dragon", "Fire breathing (updated)", "1994");
        database.updateCard("c2", updatedCard);

        List<Card> cards = database.getAllCards();
        assertTrue(cards.stream().anyMatch(c -> c.getCardName().equals("Shivan Dragon") && c.getDescription().equals("Fire breathing (updated)")));
    }

    @Test
    void testRemoveCard() {
        Card card = new Card("c3", GameType.POKEMON, "Bulbasaur", "Grass type", "1998");
        database.addCard(card);

        database.removeCard("c3");
        List<Card> cards = database.getAllCards();
        assertTrue(cards.stream().noneMatch(c -> c.getCardName().equals("Bulbasaur")));
    }

    @Test
    void testAddUpdateRemoveDeck() {
        Deck deck = new Deck("d1", GameType.MTG);
        database.addDeck(deck);

        Deck updated = new Deck("d1", GameType.POKEMON);
        database.updateDeck("d1", updated);
        database.removeDeck("d1");
        assertDoesNotThrow(() -> database.removeDeck("d1"));
    }
}
