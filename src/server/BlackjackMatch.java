package server;

import datastructures.CircularLinkedList;
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
  }

  public void startMatch(){
    sendToAll("Starting match...");
    sendToAll("Players:");
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      System.out.println(playerSocket.getPlayer().getName());
      sendToAll(playerSocket.getPlayer().getName());
    }

  }
  public void sendToAll(String message){
    for (int i = 0; i < players.size(); i++) {
      PlayerSocket playerSocket = players.get(i);
      try {
        playerSocket.getDataOutputStream().writeUTF(message);
      } catch (Exception e) {
        System.out.println("Error sending message to client: " + e);
      }
    }
  }

}
