package org.example;

import java.util.LinkedList;

public class Deck {
    private String deckID;
    private GameType deckType;
    private LinkedList<Card> cardList;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public Deck(String deckID, GameType deckType) {
        this.deckID = deckID;
        this.deckType = deckType;
        this.cardList = new LinkedList<>();
    }

    // No-Argument constructor:
    public Deck() {
        deckID = "";
        deckType = null;
        cardList = new LinkedList<>();
    }
    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Method to facilitate the process of creating a new instance of the Card class.
     * @param cardID refers to the reference number of the card's instance.
     * @param gameType refers to the game associated with the card.
     * @param cardName refers to the name of the card.
     * @param description refers to the descript of the card.
     * @param releaseYear refers to the year in which the card was first released.
     * @param price refers to the average price of the card.
     * @return a new card with the given information.
     */
    public Deck createNewDeck(String deckID, GameType deckType) {
        return new Deck(deckID, deckType);
    }
    /**
     * Method to edit an existing card instance's information.
     * @param cardID refers the identifying number of the user instance.
     */
    public void editDeck(String deckID){
        //TODO: apply logic to edit a deck.
    }
    /**
     * Method to find and delete a deck instance.
     * @param deckID refers the identifying number of the deck instance.
     */
    public void deleteDeck(int deckID) {
        //TODO: apply logic to remove a deck.
    }
    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public String getDeckID() {
        return deckID;
    }

    public void setDeckID(String deckID) {
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
