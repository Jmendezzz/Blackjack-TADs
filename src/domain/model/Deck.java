package domain.model;

import datastructures.Stack;
import domain.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final Stack<Card> cardStack;
    public Deck() {
        this.cardStack = new Stack<>();
        this.cards = generateDeck();
        shuffle();
        for (Card card : cards) {
            cardStack.push(card);
        }
    }

    private List<Card> generateDeck() {
        List<Card> deck = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (Suit suit : Suit.values()) {
            for (String rank : ranks) {
                int value = getValue(rank);
                deck.add(new Card(rank, suit, value));
            }
        }
        return deck;
    }

    private int getValue(String rank) {
        return switch (rank) {
            case "A" -> 11;
            case "K", "Q", "J" -> 10;
            default -> Integer.parseInt(rank);
        };
    }

    public Stack<Card> getCardStack() {
        return cardStack;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cardStack.peek() == null) {
            reset();
        }
        return cardStack.pop();
    }

    public void reset() {
        cardStack.clear();
        shuffle();
        for (Card card : cards) {
            cardStack.push(card);
        }
    }

    public int size() {
        return cards.size();
    }
}
