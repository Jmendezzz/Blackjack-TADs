package model;

public class Card {
    private String name;
    private String suit;
    private int value;
    private boolean isFaceUp;

    public Card(String name, String suit, int pointValue) {
        this.name = name;
        this.suit = suit;
        this.value = pointValue;
        this.isFaceUp = false;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }


    @Override
    public String toString() {
        return name;
    }
}
