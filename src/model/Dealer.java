package model;

import datastructures.Stack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private Stack<Card> hand;

    public Dealer() {
        this.hand = new Stack<>();
    }

    public void dealInitialHand(Card card) {
        hand.push(card);
    }

    public void dealCard(Card card) {
        hand.push(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int calculateHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            value += card.getPointValue();
            if (card.getRank().equals("A")) {
                numAces++;
            }
        }

        //Ases para evitar pasar 21 puntos
        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }

        return value;
    }
}
