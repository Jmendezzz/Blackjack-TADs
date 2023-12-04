package server.sockets;

import domain.model.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerSocket {
  private Player player;
  private Socket socket;
  private DataInputStream dataInputStream;
  private DataOutputStream dataOutputStream;

  public PlayerSocket(Player player,Socket socket) {
    this.player = player;
    this.socket = socket;
    try{
      this.dataInputStream = new DataInputStream(socket.getInputStream());
      this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }catch (IOException ioException){
      System.out.println("Error getting input/output stream: " + ioException);
    }

  }
  public void waiting(){
    try {
      String message = dataInputStream.readUTF();
      System.out.println(message);
    } catch (Exception e) {
      System.out.println("Error handling client: " + e);
    }
  }

  public Player getPlayer() {
    return player;
  }
  public Socket getSocket() {
    return socket;
  }
  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public DataInputStream getDataInputStream() {
    return dataInputStream;
  }

  public void setDataInputStream(DataInputStream dataInputStream) {
    this.dataInputStream = dataInputStream;
  }

  public DataOutputStream getDataOutputStream() {
    return dataOutputStream;
  }

  public void setDataOutputStream(DataOutputStream dataOutputStream) {
    this.dataOutputStream = dataOutputStream;
  }

}
