package infraestructure.strategy;

import datastructures.Stack;
import domain.model.Card;

public interface DisplayHand {
    String displayHand(Stack<Card> hand);
}
