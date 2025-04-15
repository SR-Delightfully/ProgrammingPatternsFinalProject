package org.example;

import java.util.LinkedList;

public class Deck {
    private int deckID;
    private GameType deckType;
    LinkedList<Card> cardList;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public Deck(int deckID, GameType deckType) {
        this.deckID = deckID;
        this.deckType = deckType;
        cardList = new LinkedList<>();
    }

    // No-Argument constructor:
    public Deck() {
        deckID = 0;
        deckType = null;
        cardList = new LinkedList<>();
    }
    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------

    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
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

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Deck deck = (Deck) object;
        return deckID == deck.deckID && java.util.Objects.equals(deckType, deck.deckType) && java.util.Objects.equals(cardList, deck.cardList);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), deckID, deckType, cardList);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Deck{" +
                "deckID=" + deckID +
                ", deckType=" + deckType +
                ", cardList=" + cardList +
                '}';
    }
}
