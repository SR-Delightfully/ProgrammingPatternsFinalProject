package org.example;

import java.util.Arrays;

public class CardPOKEMON extends Card {
    private int health;
    private String weakness;
    private String resistance;
    private boolean retreat;
    public String[] types;
    public int stage;

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

    public CardPOKEMON() {
        super();
        health = 0;
        weakness = "";
        resistance = "";
        retreat = false;
        types = new String[0];
        stage = 0;
    }

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
