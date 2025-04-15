package org.example;

import java.util.Arrays;

public class CardMTG extends Card {
    private int power;
    private int toughness;
    private boolean hasBackside;
    private String cardType;
    private String[] colors;
    private String[] manaCost;
    private String[] abilities;

    public CardMTG(int power, int toughness, String cardType,
                   String[] colors, String[] manaCost, String[] abilities) {
        super();
        this.power = power;
        this.toughness = toughness;
        this.cardType = cardType;
        this.colors = colors;
        this.manaCost = manaCost;
        this.abilities = abilities;
    }

    public CardMTG() {
        super();
        power = 0;
        toughness = 0;
        cardType = "";
        colors = new String[0];
        manaCost = new String[0];
        abilities = new String[0];
    }

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public String[] getManaCost() {
        return manaCost;
    }

    public void setManaCost(String[] manaCost) {
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

    public boolean isHasBackside() {
        return hasBackside;
    }

    public void setHasBackside(boolean hasBackside) {
        this.hasBackside = hasBackside;
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

    @Override
    public String toString() {
        return "CardMTG{" +
                "power=" + power +
                ", toughness=" + toughness +
                ", hasBackside=" + hasBackside +
                ", cardType='" + cardType + '\'' +
                ", colors=" + Arrays.toString(colors) +
                ", manaCost=" + Arrays.toString(manaCost) +
                ", abilities=" + Arrays.toString(abilities) +
                '}';
    }
}
