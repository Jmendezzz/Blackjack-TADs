package server;

import datastructures.CircularLinkedList;
import domain.model.Dealer;
import domain.model.Player;
import server.sockets.PlayerSocket;

import java.io.*;
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
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        String name = in.readUTF();
        System.out.println("Client connected: " + name);
        PlayerSocket player = new PlayerSocket(new Player(name),socket,out, in);
        players.add(player);

        String message = new String("Welcome!  " + name + " to the Blackjack game" +
                "\n" + "Waiting for other players..." +
                "\n" + "Connected players: " + players.size() + "/5"
        );
        out.writeObject(message);
        out.flush();

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
