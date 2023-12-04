package infraestructure.strategy;

import datastructures.Node;
import datastructures.Stack;
import domain.model.Card;

public class DisplayHandPlayer implements DisplayHand{
  @Override
  public String displayHand(Stack<Card> hand) {
    Node<Card> current = hand.getTop();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < hand.getSize(); i++) {
        sb.append("│ " + current.getData().toString() + " ");
        current = current.getNext();
      }
    sb.append("│");
    return sb.toString();
  }
}
