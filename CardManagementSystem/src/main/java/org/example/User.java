package org.example;

import java.util.LinkedList;

public class User {
    private int userID;
    private String userName;
    private String emailAddress;
    private String password;
    private LinkedList<Card> cardList;
    private LinkedList<Deck> deckList;

    public User(int userID, String userName, String emailAddress, String password) {
        this.userID = userID;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        cardList = new LinkedList<>();
        deckList = new LinkedList<>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(LinkedList<Card> cardList) {
        this.cardList = cardList;
    }

    public LinkedList<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(LinkedList<Deck> deckList) {
        this.deckList = deckList;
    }
}
