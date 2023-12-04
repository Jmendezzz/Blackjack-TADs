package domain.model;

import datastructures.Stack;

public class Player {
  private String name;
  private Double cash;
  private Stack<Card> hand;
  private int handValue;
  private Double bet;
  public Player(String name) {
    this.name = name;
    this.bet = 0.0;
    this.cash = 500.0;
  }
  public Double getCash() {
    return cash;
  }
  public void setCash(Double cash) {
    this.cash = cash;
  }
  public Stack<Card> getHand() {
    return hand;
  }
  private void setHandValue() {
    this.handValue = this.hand.getTotalValue();
  }
  public int getHandValue() {
    setHandValue();
    return handValue;
  }
  public Double setBet(Double amount) {
    if (amount > cash) {
      return null;
    }
    cash -= amount;
    return amount;
  }
  public Double getBet() {
    return bet;
  }
  public double win(Double amount) {
    cash += amount;
    return cash;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void receiveCard(Card card) {
    hand.push(card);
  }
  public void clearHand() {
    hand.clear();
  }
  @Override
  public String toString() {
    return name + " | Cash: $" + cash + " | Bet: $" + bet;
  }
}
