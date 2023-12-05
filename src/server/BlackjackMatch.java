package server;

import datastructures.CircularLinkedList;
import datastructures.Node;
import domain.enums.ServerInteraction;
import domain.model.Dealer;
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

}
