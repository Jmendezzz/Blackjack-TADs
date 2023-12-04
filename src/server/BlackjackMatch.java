package server;

import datastructures.CircularLinkedList;
import datastructures.Node;
import datastructures.Stack;
import domain.model.Card;
import domain.model.Player;
import server.sockets.PlayerSocket;

public class BlackjackMatch{

  private final CircularLinkedList<PlayerSocket> players;

  BlackjackMatch(CircularLinkedList<PlayerSocket> players) {
    this.players = players;
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      Thread thread = new Thread(playerSocket::waiting);
      thread.start();
    }
    startMatch();
  }

  public void startMatch(){
    StringBuilder message = new StringBuilder("Iniciando partida... \n" +
                                              "Jugadores: \n");
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      message.append(playerSocket.getPlayer().getName()).append("\n");
    }

    sendToAll(message.toString());
  }
  public void sendToAll(String message){
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      try {
        playerSocket.getDataOutputStream().writeUTF(message);
        playerSocket.getDataOutputStream().flush();
      } catch (Exception e) {
        System.out.println("Error sending message to client: " + e);
      }
    }
  }

  private void displayTable() {
    //TODO:
    //clearConsole();

    System.out.println("  ┌───────────────────────────────────────────────────────────────────────┐");
    System.out.println("  │                         Blackjack Table                              │");
    System.out.println("  └───────────────────────────────────────────────────────────────────────┘");
    System.out.println("   ┌─────────────────────────────────────────────────────────────────────┐");
    System.out.println("   │                          Dealer                                     │");
    System.out.println("   │                         ┌───────────┐                               │");
    displayHand(dealer);
    System.out.println("   │                         └───────────┘                               │");
    System.out.println("   │                                                                     │");
    System.out.println("   │                                                                     │");
    System.out.println("   └─────────────────────────────────────────────────────────────────────┘");
    System.out.println("   ┌─────────────────────────────────────────────────────────────────────┐");
    System.out.println("   │Player 1                 Player 2                 Player 3           │");

    for (int i = 0; i < players.size(); i++) {
      System.out.print("   │");
      displayPlayer(players.get(i).getPlayer());
    }

    System.out.println("   └─────────────────────────────────────────────────────────────────────┘");
    System.out.println("  ┌───────────────────────────────────────────────────────────────────────┐");
    System.out.println("  │ Instructions: Enter your bet and commands (hit, stand, etc.)          │");
    System.out.println("  └───────────────────────────────────────────────────────────────────────┘");
  }

  private void displayPlayer(Player player) {
    System.out.print(" " + player.getName().substring(0, Math.min(6, player.getName().length())) + " ");
    System.out.print("│ " + String.format("%-21s", player.toString()) + "│");
    displayHand(player);
  }

  private void displayHand(Player player) {
    Stack<Card> hand = player.getHand();
    Node<Card> current = hand.getTop();
    for (int i = 0; i < hand.getSize(); i++) {
      if (current.getData().isFaceUp()) {
        System.out.print("│ " + current.getData().toString() + " ");
      } else {
        System.out.print("│ Face Down ");
      }
      current = current.getNext();
    }
    System.out.println("│");
  }

}
