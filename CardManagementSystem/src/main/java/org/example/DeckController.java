package org.example;

public class DeckController {
    private Database database;

    public DeckController(Database database) {
        this.database = database;
    }

    public void addDeck(Deck deck) {
        database.addDeck(deck);
    }

    public void updateDeck(String deckID, Deck updatedDeck) {
        database.updateDeck(deckID, updatedDeck);
    }

    public void removeDeck(String deckID) {
        database.removeDeck(deckID);
    }
}
