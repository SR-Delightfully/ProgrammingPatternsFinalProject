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

    /**
     * Method to add a card to the database
     * @param card refers to the card to be added
     */
    public void addCard(Card card) {
        if (!entries.containsKey(card.getCardID())) {
            entries.put(card.getCardID(), card);
            cardList.add(card);
        } else {
            System.out.println("Card with ID " + card.getCardID() + " already exists");
        }
    }

    /**
     * Method to remove a card from the database
     * @param cardID refers to the identifier of the card to find which card needs to be removed
     */
    public void removeCard(String cardID) {
        Card cardToRemove = entries.remove(cardID);
        if (cardToRemove != null) {
            cardList.remove(cardToRemove);
        } else {
            System.out.println("Card with ID " + cardID + " does not exist");
        }
    }

    /**
     * Method to edit an existing card instance's information.
     * @param cardID refers the identifying number of the user instance.
     */
    public void editCard(String cardID, Card updatedCard) {
        if (!entries.containsKey(cardID)) {
            entries.put(cardID, updatedCard);

            for (int i = 0; i < cardList.size(); i++) {
                if (cardList.get(i).getCardID().equals(cardID)) {
                    cardList.set(i, updatedCard);
                    break;
                }
            }
            System.out.println("Card with ID " + cardID + " has been updated");
        }
        else {
            System.out.println("Card with ID " + cardID + " does not exist");
        }
    }

    /**
     * Method to add a user to the database
     * @param user refers to the user to be added
     */
    public void addUser(User user) {
        for (User existingUser : userList) {
            if (existingUser.getUserID().equals(user.getUserID())) {
                System.out.println("User with ID " + user.getUserID() + " already exists");
            }
        }
        userList.add(user);
        System.out.println("User added to the database");
    }

    /**
     * Method to remove a user from the database
     * @param userID refers to the identifier of the user to find which user needs to be removed
     */
    public void removeUser(String userID) {
        boolean removed = userList.removeIf(user -> user.getUserID().equals(userID));
        if (removed) {
            System.out.println("User with ID " + userID + " has been removed");
        }
        else {
            System.out.println("User with ID " + userID + " does not exist");
        }
    }

    /**
     * Method to edit an existing user instance's information.
     * @param userID refers the identifying number of the user instance.
     * @param updatedUser refers to the new information for the user
     */
    public void editUser(String userID, User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserID().equals(userID)) {
                userList.set(i, updatedUser);
                System.out.println("User with ID " + userID + " has been updated");
                return;
            }
        }
        System.out.println("User with ID " + userID + " does not exist");
    }

    /**
     * Method to add a new deck to the database
     * @param deck refers to the deck to be added
     */
    public void addDeck(Deck deck) {
        for (Deck existingDeck : deckList) {
            if (existingDeck.getDeckID().equals(deck.getDeckID())) {
                System.out.println("Deck with ID " + deck.getDeckID() + " already exists");
            }
        }
        deckList.add(deck);
        System.out.println("Deck added to the database");
    }

    /**
     * Method to remove a deck from the database
     * @param deckID refers to the identifier of the deck to find which deck needs to be removed
     */
    public void removeDeck(String deckID) {
        boolean removed = deckList.removeIf(deck -> deckID.equals(deck.getDeckID()));
        if (removed) {
            System.out.println("Deck with ID " + deckID + " has been removed");
        }
        else {
            System.out.println("Deck with ID " + deckID + " does not exist");
        }
    }

    /**
     * Method to edit an existing card instance's information.
     * @param deckID refers the identifying number of the deck instance.
     */
    public void editDeck(String deckID, Deck updateDeck) {
        for (int i = 0; i < deckList.size(); i++) {
            if (deckList.get(i).getDeckID().equals(deckID)) {
                deckList.set(i, updateDeck);
                return;
            }
        }
        System.out.println("Deck with ID " + deckID + " does not exist");
    }
}
