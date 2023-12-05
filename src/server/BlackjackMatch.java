package server;

import datastructures.CircularLinkedList;
import datastructures.Node;
import domain.enums.ServerInteraction;
import domain.model.Dealer;
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
    }
    sendToAll(BlackjackTableUtil.displayTable(dealer, players));
  }
  private void selectTurn() {
    try {
      String turn = ServerInteraction.TURN.toString();
      currentPlayer.getData().getDataOutputStream().writeObject(turn);
      currentPlayer.getData().getDataOutputStream().flush();
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
  public void waiting(PlayerSocket playerSocket) {
    while (!playerSocket.getSocket().isClosed()) {
      try {
        Object object = playerSocket.getDataInputStream().readObject();

        if (object instanceof String message) {
          System.out.println("Socket player: " + message);
        } else {
          // Handle unexpected object type, or ignore if it's intentional
          System.out.println("Received unexpected object type");
        }
      } catch (Exception e) {
        try {
          playerSocket.getSocket().close();
        } catch (Exception ex) {
          System.out.println("Error closing socket: " + ex);
        }
        System.out.println("Error handling client here: " + e);

      }
    }
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
