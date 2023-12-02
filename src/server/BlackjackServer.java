package server;

import datastructures.CircularLinkedList;
import model.Player;
import server.sockets.PlayerSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BlackjackServer {
  public static final int PORT = 8080;
  public static CircularLinkedList<PlayerSocket> players = new CircularLinkedList<PlayerSocket>();
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PORT,10);
      while(!serverSocket.isClosed()){
          System.out.println("Waiting for a client to connect...");
          Socket socket = serverSocket.accept();
          System.out.println("Client connected!");
          handleClient(socket);

          if(players.size() == 2){
            handleMatch();
          }


      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void handleClient(Socket socket) {
    try {
      DataInputStream in = new DataInputStream(socket.getInputStream());
      while(true) {
        String name = in.readUTF();
        PlayerSocket player = new PlayerSocket(new Player(name),socket);
        players.add(player);
      }
    } catch (IOException e) {
      System.out.println("Error handling client: " + e);
    }
  }
  public static void handleMatch(){
    BlackjackMatch match = new BlackjackMatch(players);
    players.deleteAll();
  }
}
