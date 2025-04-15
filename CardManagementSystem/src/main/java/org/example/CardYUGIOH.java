package org.example;

public class CardYUGIOH extends Card {
    private String attribute;
    private int power;
    private String series;
    private String type;
    private int level;

    public CardYUGIOH(String attribute, int power, String series, String type, int level) {
        super();
        this.attribute = attribute;
        this.power = power;
        this.series = series;
        this.type = type;
        this.level = level;
    }

    public CardYUGIOH() {
        super();
        attribute = "";
        power = 0;
        series = "";
        type = "";
        level = 0;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
