package org.example;

public class CardYUGIOH extends Card {
    private String attribute;
    private int power;
    private String series;
    private String type;
    private int level;

    // Adding constructors:
    //------------------------------------------------------------------------------------------------------------------
    // All-Argument constructor:
    public CardYUGIOH(String attribute, int power, String series, String type, int level) {
        super();
        this.attribute = attribute;
        this.power = power;
        this.series = series;
        this.type = type;
        this.level = level;
    }

    // No-Argument constructor:
    public CardYUGIOH() {
        super();
        attribute = "";
        power = 0;
        series = "";
        type = "";
        level = 0;
    }

    // Adding Methods:
    //------------------------------------------------------------------------------------------------------------------

    // Adding Getters and Setters
    //------------------------------------------------------------------------------------------------------------------
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

    // Adding Equals & Hashmap:
    //------------------------------------------------------------------------------------------------------------------
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CardYUGIOH that = (CardYUGIOH) object;
        return power == that.power && level == that.level && java.util.Objects.equals(attribute, that.attribute) && java.util.Objects.equals(series, that.series) && java.util.Objects.equals(type, that.type);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), attribute, power, series, type, level);
    }

    // Adding toString method:
    //------------------------------------------------------------------------------------------------------------------
    @java.lang.Override
    public java.lang.String toString() {
        return "CardYUGIOH{" +
                "attribute='" + attribute + '\'' +
                ", power=" + power +
                ", series='" + series + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                '}';
    }
}
