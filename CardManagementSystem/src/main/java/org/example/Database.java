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
        if (!entries.containsKey(card.getCardID())) {
            entries.put(card.getCardID(), card);
            cardList.add(card);
        } else {
            System.out.println("Card with ID " + card.getCardID() + " already exists");
        }
    }

    /*
    removes a card from the cardList
     */
    public void removeCard(String cardID) {
        Card cardToRemove = entries.remove(cardID);
        if (cardToRemove != null) {
            cardList.remove(cardToRemove);
        } else {
            System.out.println("Card with ID " + cardID + " does not exist");
        }
    }

    /*
    adds a user to the user list
     */
    public void addUser(User user) {
        userList.add(user);
    }

    /*
    removes a user from the user list
     */
    public void removeUser(String userID) {
        userList.removeIf(user -> user.getUserID().equals(userID));
    }
    /*
    adds a deck to the deck list
     */
    public void addDeck(Deck deck) {
        deckList.add(deck);
    }
     /*
     removes a deck from the deck list
      */
    public void removeDeck(String deckID) {
        deckList.removeIf(deck -> deckID.equals(deck.getDeckID()));
    }
}
