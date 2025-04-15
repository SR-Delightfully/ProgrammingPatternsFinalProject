package org.example;

import java.util.LinkedList;

public class Deck {
    private int deckID;
    private GameType deckType;
    LinkedList<Card> cardList;

    public Deck(int deckID, GameType deckType) {
        this.deckID = deckID;
        this.deckType = deckType;
        cardList = new LinkedList<>();
    }

    public Deck() {
        deckID = 0;
        deckType = null;
        cardList = new LinkedList<>();
    }

    public int getDeckID() {
        return deckID;
    }

    public void setDeckID(int deckID) {
        this.deckID = deckID;
    }

    public GameType getDeckType() {
        return deckType;
    }

    public void setDeckType(GameType deckType) {
        this.deckType = deckType;
    }

    public LinkedList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(LinkedList<Card> cardList) {
        this.cardList = cardList;
    }
}
