package server;

import datastructures.CircularLinkedList;
import domain.model.Player;
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
          handleClient(socket);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void handleClient(Socket socket) {

    try {
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String name = in.readUTF();
        System.out.println("Client connected: " + name);
        PlayerSocket player = new PlayerSocket(new Player(name),socket);
        players.add(player);
        player.getDataOutputStream()
              .writeUTF("Bienvenido " + name + " al juego de Blackjack" +
                      "\n" + "Esperando a que se conecten los demas jugadores..." +
                      "\n" + "Jugadores conectados: " + players.size() + "/5"
              );
        player.getDataOutputStream().flush();
    } catch (IOException e) {
      System.out.println("Error handling client: " + e);
    }finally {
      if(players.size() == 2){
        handleMatch();
      }
    }
  }
  public static void handleMatch(){
    new BlackjackMatch(players);
    players.deleteAll();
  }
}
