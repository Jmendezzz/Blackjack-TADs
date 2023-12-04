package domain.model;

import datastructures.Stack;

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
