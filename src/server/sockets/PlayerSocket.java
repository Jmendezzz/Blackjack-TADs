package server.sockets;

import domain.model.Player;

import java.io.*;
import java.net.Socket;

public class PlayerSocket {
  private Player player;
  private Socket socket;
  private ObjectOutputStream dataOutputStream;
  private ObjectInputStream dataInputStream;

  public PlayerSocket(Player player,Socket socket, ObjectOutputStream dataOutputStream, ObjectInputStream dataInputStream) {
    this.player = player;
    this.socket = socket;
    this.dataOutputStream = dataOutputStream;
    this.dataInputStream = dataInputStream;
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

  public ObjectInputStream getDataInputStream() {
    return dataInputStream;
  }

  public void setDataInputStream(ObjectInputStream dataInputStream) {
    this.dataInputStream = dataInputStream;
  }

  public ObjectOutputStream getDataOutputStream() {
    return dataOutputStream;
  }

  public void setDataOutputStream(ObjectOutputStream dataOutputStream) {
    this.dataOutputStream = dataOutputStream;
  }
}
