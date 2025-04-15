package org.example;


public abstract class Card {
    private int cardID;
    private GameType gameType;
    private String cardName;
    private String description;
    private int releaseYear;
    private double price;

    public Card(int cardID, GameType gameType, String cardName,
                String description, int releaseYear, double price) {
        this.cardID = cardID;
        this.gameType = gameType;
        this.cardName = cardName;
        this.description = description;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    public Card() {
        cardID = 0;
        gameType = null;
        cardName = null;
        description = null;
        releaseYear = 0;
        price = 0.00;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
