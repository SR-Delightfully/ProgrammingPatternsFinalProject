package org.example;

public class CardController {
    private Database database;

    public CardController(Database database) {
        this.database = database;
    }

    public void addCard(Card card) {
        database.addCard(card);
    }
    public void updateCard(String cardID, Card card) {
        database.updateCard(cardID, card);
    }

    public void removeCard(String cardID) {
        database.removeCard(cardID);
    }
}
