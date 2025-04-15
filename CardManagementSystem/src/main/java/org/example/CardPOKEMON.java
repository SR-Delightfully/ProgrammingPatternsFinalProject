package org.example;

import java.util.Arrays;

public class CardPOKEMON extends Card {
    private int health;
    private String weakness;
    private String resistance;
    private boolean retreat;
    public String[] types;
    public int stage;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public CardPOKEMON(int health, String weakness, String resistance,
                       boolean retreat, String[] types, int stage) {
        super();
        this.health = health;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreat = retreat;
        this.types = types;
        this.stage = stage;
    }

    // No-Argument constructor:
    public CardPOKEMON() {
        super();
        health = 0;
        weakness = "";
        resistance = "";
        retreat = false;
        types = new String[0];
        stage = 0;
    }

    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------

    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public boolean isRetreat() {
        return retreat;
    }

    public void setRetreat(boolean retreat) {
        this.retreat = retreat;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CardPOKEMON that = (CardPOKEMON) object;
        return health == that.health && retreat == that.retreat && stage == that.stage && java.util.Objects.equals(weakness, that.weakness) && java.util.Objects.equals(resistance, that.resistance) && java.util.Objects.deepEquals(types, that.types);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), health, weakness, resistance, retreat, java.util.Arrays.hashCode(types), stage);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "CardPOKEMON{" +
                "health=" + health +
                ", weakness='" + weakness + '\'' +
                ", resistance='" + resistance + '\'' +
                ", retreat=" + retreat +
                ", types=" + Arrays.toString(types) +
                ", stage=" + stage +
                '}';
    }
}
