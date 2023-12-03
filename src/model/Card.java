package model;

public class Card {
    private String name;
    private String suit;
    private int pointValue;
    private boolean isFaceUp;

    public Card(String name, String suit, int pointValue) {
        this.name = name;
        this.suit = suit;
        this.pointValue = pointValue;
        this.isFaceUp = false;
    }

    public String getName() {
        return name;
    }

    public String getSuit() {
        return suit;
    }

    public int getPointValue() {
        return pointValue;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public String getRank() {
        // Devuelve el valor de la carta "2", "A", "K"
        return name.split(" ")[0];
    }

    @Override
    public String toString() {
        return name;
    }
}
