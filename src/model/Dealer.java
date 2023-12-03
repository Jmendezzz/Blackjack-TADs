package model;

import datastructures.Stack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private Stack<Card> hand;

    public Dealer() {
        this.hand = new Stack<>();
    }
    public void receiveCard(Card card) {
        hand.push(card);
    }
    public Stack<Card> getHand() {
        return hand;
    }

    public int calculateHandValue() {
        return hand.getTotalValue();
    }
}
