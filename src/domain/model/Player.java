package domain.model;

import datastructures.Stack;

public class Player {
  private String name;
  private Double cash;
  private Stack hand;
  public Player(String name) {
    this.name = name;
  }
  public Double getCash() {
    return cash;
  }
  public void setCash(Double cash) {
    this.cash = cash;
  }
  public Stack getHand() {
    return hand;
  }
  public void setHand(Stack hand) {
    this.hand = hand;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
