package server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BlackjackServer {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8080,10);
      while(!serverSocket.isClosed()){
        try {
          System.out.println("Waiting for a client to connect...");
          Socket socket = serverSocket.accept();
          System.out.println("Client connected!");
          DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
          String message = dataInputStream.readUTF();
          System.out.println("Message from client: " + message);
        }catch (EOFException e){
          System.out.println("Cloudflare!");
        }

      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
