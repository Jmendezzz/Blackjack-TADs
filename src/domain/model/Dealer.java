package domain.model;

import datastructures.Stack;
import infraestructure.strategy.DisplayHand;
import infraestructure.strategy.DisplayHandDealer;

public class Dealer {
    private final Stack<Card> hand;
    private int handValue;
    private final Deck deck;
    public final DisplayHand displayHandStrategy;

    public Dealer() {
        this.hand = new Stack<>();
        this.deck = new Deck();
        this.displayHandStrategy = new DisplayHandDealer();
    }
    public void receiveCard(Card card) {
        if (hand.getSize() == 1) {
            card.setFaceUp(false);
        }
        hand.push(card);
    }
    public Stack<Card> getHand() {
        return hand;
    }
    public int getHandValue() {
        setHandValue();
        return handValue;
    }
    private void setHandValue() {
        this.handValue = hand.getTotalValue();
    }
    public Card deal(){
        return this.deck.dealCard();
    }
    public void clearHand() {
        hand.clear();
    }
    public String displayHand() {
        return displayHandStrategy.displayHand(hand);
    }
}
