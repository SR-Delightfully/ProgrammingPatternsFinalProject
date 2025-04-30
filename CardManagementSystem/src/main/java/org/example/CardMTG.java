package org.example;

import java.util.Arrays;

public class CardMTG extends Card {
    private String text;
    private String type;
    private String[] colors;
    private String manaCost;
    private int power;
    private int toughness;
    private String cardType;
    private String[] abilities;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:s
    public CardMTG(  String id, GameType gameType, String name,String description, String releaseDate, double price, String text, String type,
                     String[] colors, String manaCost, int power, int toughness, String cardType, String[] abilities) {
        super(id, gameType, name, description, releaseDate);
        this.power = power;
        this.toughness = toughness;
        this.cardType = cardType;
        this.colors = colors;
        this.manaCost = manaCost;
        this.abilities = abilities;
    }

    // No-Argument constructor:
    public CardMTG() {
        super();
        power = 0;
        toughness = 0;
        cardType = "";
        colors = new String[0];
        manaCost = "";
        abilities = new String[0];
    }

    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------

    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CardMTG cardMTG = (CardMTG) object;
        return power == cardMTG.power && toughness == cardMTG.toughness && java.util.Objects.equals(cardType, cardMTG.cardType) && java.util.Objects.deepEquals(colors, cardMTG.colors) && java.util.Objects.deepEquals(manaCost, cardMTG.manaCost) && java.util.Objects.deepEquals(abilities, cardMTG.abilities);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), power, toughness, cardType, java.util.Arrays.hashCode(colors), java.util.Arrays.hashCode(abilities));
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "CardMTG{" +
                "power=" + power +
                ", toughness=" + toughness +
                ", cardType='" + cardType + '\'' +
                ", colors=" + Arrays.toString(colors) +
                ", manaCost=" + manaCost +
                ", abilities=" + Arrays.toString(abilities) +
                '}';
    }
}
