package org.example;

import java.util.LinkedList;

public class User {
    private String userID;
    private String userName;
    private String emailAddress;
    private String password;
    private LinkedList<Card> cardList;
    private LinkedList<Deck> deckList;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public User(String userID, String userName, String emailAddress, String password, LinkedList<Card> cardList, LinkedList<Deck> deckList) {
        this.userID = userID;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.cardList = cardList;
        this.deckList = deckList;
    }

    public User(String userID, String userName, String emailAddress, String password) {
        this.userID = userID;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    // No-Argument constructor:
    public User() {
        userID = "";
        userName = "";
        emailAddress = "";
        password = "";
        cardList = new LinkedList<>();
        deckList = new LinkedList<>();
    }

    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Method to facilitate the process of creating a new instance of the User class.
     * @param userID refers to the reference number of the user's instance.
     * @param userName refers to the name associated with the user instance.
     * @param emailAddress refers to the email address of the user instance.
     * @param password refers to the user's password which will be required to access & edit the user's information.
     * @param cardList refers to the list of all the cards that the user has in their collections.
     * @param deckList refers to the many sub-collections of cards that the user may have.
     * @return a new user with the given information.
     */
    public User createNewUser(String userID, String userName, String emailAddress, String password, LinkedList<Card> cardList, LinkedList<Deck> deckList) {
        return new User(userID, userName, emailAddress, password, cardList, deckList);
    }


    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        User user = (User) object;
        return userID == user.userID && java.util.Objects.equals(userName, user.userName) && java.util.Objects.equals(emailAddress, user.emailAddress) && java.util.Objects.equals(password, user.password) && java.util.Objects.equals(cardList, user.cardList) && java.util.Objects.equals(deckList, user.deckList);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), userID, userName, emailAddress, password, cardList, deckList);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", cardList=" + cardList +
                ", deckList=" + deckList +
                '}';
    }
}
