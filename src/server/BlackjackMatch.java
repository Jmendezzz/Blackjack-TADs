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

}
