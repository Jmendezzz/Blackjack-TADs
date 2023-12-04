package domain.model;

import domain.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = generateDeck();
        shuffle();
    }

    private List<Card> generateDeck() {
        List<Card> deck = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (Suit suit : Suit.values()) {
            for (String rank : ranks) {
                int value = getValue(rank);
                String cardName = rank + " de " + suit;
                deck.add(new Card(cardName, suit, value));
            }
        }
        return deck;
    }

    private int getValue(String rank) {
        switch (rank) {
            case "A":
                return 11;
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(rank);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("La baraja esta vacia, no se pueden repartir mas cartas.");
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
