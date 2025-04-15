package org.example;


public class Card {
    private String cardID;
    private GameType gameType;
    private String cardName;
    private String description;
    private int releaseYear;
    private double price;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public Card(String cardID, GameType gameType, String cardName,
                String description, int releaseYear, double price) {
        this.cardID = cardID;
        this.gameType = gameType;
        this.cardName = cardName;
        this.description = description;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    // No-Argument constructor:
    public Card() {
        cardID = "";
        gameType = null;
        cardName = null;
        description = null;
        releaseYear = 0;
        price = 0.00;
    }
    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Method to facilitate the process of creating a new instance of the Card class.
     * @param cardID refers to the reference number of the card's instance.
     * @param gameType refers to the game associated with the card.
     * @param cardName refers to the name of the card.
     * @param description refers to the description of the card.
     * @param releaseYear refers to the year in which the card was first released.
     * @param price refers to the average price of the card.
     * @return a new card with the given information.
     */
    public Card createNewCard(String cardID, GameType gameType, String cardName,
                              String description, int releaseYear, double price) {
        return new Card(cardID, gameType, cardName,description, releaseYear, price);
    }
    /**
     * Method to edit an existing card instance's information.
     * @param cardID refers the identifying number of the user instance.
     */
    public void editCard(int cardID){
        //TODO: apply logic to edit a card.
    }


    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
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

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Card card = (Card) object;
        return cardID == card.cardID && releaseYear == card.releaseYear && java.lang.Double.compare(price, card.price) == 0 && java.util.Objects.equals(gameType, card.gameType) && java.util.Objects.equals(cardName, card.cardName) && java.util.Objects.equals(description, card.description);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), cardID, gameType, cardName, description, releaseYear, price);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + cardID +
                ", gameType=" + gameType +
                ", cardName='" + cardName + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                '}';
    }
}
