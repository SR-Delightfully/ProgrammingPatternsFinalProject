package org.example;

import java.util.HashMap;
import java.util.LinkedList;

public class Database {
    private HashMap<String, Card> entries;
    private LinkedList<Card> cardList;
    private LinkedList<User> userList;
    private LinkedList<Deck> deckList;

    public Database() {
        entries = new HashMap<>();
        cardList = new LinkedList<>();
        userList = new LinkedList<>();
        deckList = new LinkedList<>();
    }

    /*
    adds a card to the card list
     */
    public void addCard(Card card) {

    }

    /*
    removes a card from the cardList
     */
    public void removeCard(String cardID) {

    }

    /*
    adds a user to the user list
     */
    public void addUser(User user) {

    }

    /*
    removes a user from the user list
     */
    public void removeUser(String userID) {

    }
    /*
    adds a deck to the deck list
     */
    public void addDeck(Deck deck) {

    }
     /*
     removes a deck from the deck list
      */
    public void removeDeck(String deckID) {

    }
}
