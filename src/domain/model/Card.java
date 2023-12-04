package domain.model;

import domain.enums.Suit;

public class Card {
    private final String name;
    private final Suit suit;
    private int value;
    private boolean isFaceUp;

    public Card(String name, Suit suit, int pointValue) {
        this.name = name;
        this.suit = suit;
        this.value = pointValue;
        this.isFaceUp = true;
    }

    public String getName() {
        return name;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }
    @Override
    public String toString() {
        if (isFaceUp) {
            return "│ " + value + " " + suit + " │";
        } else {
            return "│ Face Down │";
        }
    }
}
