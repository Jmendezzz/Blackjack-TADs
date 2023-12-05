package server;

import datastructures.CircularLinkedList;
import datastructures.Node;
import domain.enums.ServerInteraction;
import domain.model.Dealer;
import domain.model.Player;
import server.service.PlayerAction;
import server.sockets.PlayerSocket;
import server.utils.BlackjackTableUtil;

public class BlackjackMatch{

  private final CircularLinkedList<PlayerSocket> players;
  private Node<PlayerSocket> currentPlayer;
  private final Dealer dealer;

  BlackjackMatch(CircularLinkedList<PlayerSocket> players) {
    this.players = players;
    this.dealer = new Dealer();
    this.currentPlayer = players.getHead();
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      Thread thread = new Thread(playerSocket::waiting);
      thread.start();
    }
    startMatch();
  }

  public void startMatch(){
    sendWelcomeMessageToPlayers();
    dealCardsToPlayers();
    selectTurn();
  }

  private void dealCardsToPlayers(){
    for (int i = 0; i < players.size() * 2; i++) {
      currentPlayer.getData().getPlayer().receiveCard(dealer.deal());
      currentPlayer = currentPlayer.getNext();
      if(i == players.size() - 1 || i == players.size() * 2 - 1){
        dealer.receiveCard(dealer.deal());
      }
      sendToAll(BlackjackTableUtil.displayTable(dealer, players));
    }
  }
  private void selectTurn() {
    try {
      currentPlayer.getData().getDataOutputStream().writeObject(ServerInteraction.TURN.toString());
    } catch (Exception e) {
      System.out.println("Error sending message to client: " + e);
    }
  }

  private void hitCard(Player player){
    player.receiveCard(dealer.deal());
    int handValue = player.getHandValue();
    String message = "";
    if (handValue == 21) {
      message = "Player " + player.getName() + " got 21";
      currentPlayer = currentPlayer.getNext();
    } else if (handValue > 21) {
      message = "Player " + player.getName() + " is over 21";
      currentPlayer = currentPlayer.getNext();
    }
    sendToAll(BlackjackTableUtil.displayTable(dealer, players));
    if (!message.isEmpty()) {
      sendToAll(message);
    }
    selectTurn();
  }

  private void sendWelcomeMessageToPlayers(){
    StringBuilder message = new StringBuilder("Starting match... \n" +
            "Players: \n");
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      message.append(playerSocket.getPlayer().getName()).append("\n");
    }

    sendToAll(message.toString());
  }

  private void sendToAll(String message){
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      try {
        playerSocket.getDataOutputStream().writeObject(message);
        playerSocket.getDataOutputStream().flush();
      } catch (Exception e) {
        System.out.println("Error sending message to client: " + e);
      }
    }
  }

  public void dealerTurn(){
    while(dealer.getHandValue() < 17){
      dealer.receiveCard(dealer.deal());
    }
    sendToAll(BlackjackTableUtil.displayTable(dealer, players));
    sendToAll("Dealer's score: " + dealer.getHandValue());
    sendToAll("Dealer's hand: " + dealer.displayHand());
    sendToAll("Dealer's turn is over");
    sendToAll("------------------------------\n");
    sendToAll("Calculating results...");
    sendToAll("------------------------------\n");
    calculateResults();
  }

  private void calculateResults(){
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      Player player = playerSocket.getPlayer();
      double bet = player.getBet();
      double cash = player.getCash();
      int playerValue = player.getHandValue();
      int dealerValue = dealer.getHandValue();

      if (playerValue > 21 || (playerValue < dealerValue && dealerValue <= 21)) {
        cash -= bet;
        sendToAll(player.getName() + " lost $" + bet);
      } else if (playerValue == dealerValue) {
        sendToAll(player.getName() + " tied with the dealer");
      } else {
        double winnings = bet;
        if (playerValue == 21 && dealerValue != 21) {
          winnings *= 1.5;
        }
        cash += winnings;
        sendToAll(player.getName() + " won $" + winnings);
      }
      player.setCash(cash);
      player.setBet(0.0);
    }
  }

}
