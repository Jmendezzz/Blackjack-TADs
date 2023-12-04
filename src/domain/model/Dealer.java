package domain.model;

import datastructures.Stack;

public class Dealer {
    private Stack<Card> hand;
    private final Deck deck;

    public Dealer() {
        this.hand = new Stack<>();
        this.deck = new Deck();
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
    public Card deal(){
        return this.deck.dealCard();
    }
}
