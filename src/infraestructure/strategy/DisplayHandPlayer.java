package infraestructure.strategy;

import datastructures.Node;
import datastructures.Stack;
import domain.model.Card;

public class DisplayHandPlayer implements DisplayHand{
  @Override
  public String displayHand(Stack<Card> hand) {
    Node<Card> current = hand.getTop();
    StringBuilder sb = new StringBuilder();
    sb.append("-----------------------------------------------------------------------------------------------\n");
    for (int i = 0; i < hand.getSize(); i++) {
      sb.append(current.getData().toString());
      sb.append(" | ");
      current = current.getNext();
    }
    sb.append("\n-----------------------------------------------------------------------------------------------\n");
    return sb.toString();
  }
}
