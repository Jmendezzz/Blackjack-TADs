package domain.model;

import datastructures.Stack;
import infraestructure.strategy.DisplayHand;
import infraestructure.strategy.DisplayHandPlayer;

public class Player {
  private String name;
  private Double cash;
  private Stack<Card> hand;
  private int handValue;
  private Double bet;
  private DisplayHand displayHandStrategy;
  public Player(String name) {
    this.displayHandStrategy = new DisplayHandPlayer();
    this.hand = new Stack<>();
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
  public void setBet(Double bet) {
    this.bet = bet;
  }
  public Double Bet() {
    if (bet > cash) {
      return null;
    }
    cash -= bet;
    return bet;
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

  public String displayHand() {
    return displayHandStrategy.displayHand(hand);
  }
  @Override
  public String toString() {
    return name + " | Cash: $" + cash + " | Bet: $" + bet;
  }
}
