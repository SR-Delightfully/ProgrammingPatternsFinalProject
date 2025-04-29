package org.example;


public class Card {
    private String id;
    private GameType gameType;
    private String name;
    private String description;
    private String releaseDate;
    private double price;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public Card(String id, GameType gameType, String name,
                String description, String releaseDate, double price) {
        this.id = id;
        this.gameType = gameType;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Card(String name, GameType gameType, String releaseDate, double price) {
        this.name = name;
        this.gameType = gameType;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    // No-Argument constructor:
    public Card() {
        id = "";
        gameType = null;
        name = null;
        description = null;
        releaseDate = null;
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
     * @param releaseDate refers to the year in which the card was first released.
     * @param price refers to the average price of the card.
     * @return a new card with the given information.
     */
    public Card createNewCard(String cardID, GameType gameType, String cardName,
                              String description, String releaseDate, double price) {
        return new Card(cardID, gameType, cardName,description, releaseDate, price);
    }



    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public void setId(String cardID) {
        this.id = cardID;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getCardName() {
        return name;
    }

    public void setCardName(String cardName) {
        this.name = cardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
        return id == card.id && releaseDate == card.releaseDate && java.lang.Double.compare(price, card.price) == 0 && java.util.Objects.equals(gameType, card.gameType) && java.util.Objects.equals(name, card.name) && java.util.Objects.equals(description, card.description);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, gameType, name, description, releaseDate, price);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + id +
                ", gameType=" + gameType +
                ", cardName='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
