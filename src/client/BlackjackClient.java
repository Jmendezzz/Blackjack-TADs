package client;

import domain.enums.ServerInteraction;
import domain.model.Player;
import infraestructure.factory.PlayerActionsFactory;
import infraestructure.factory.impl.PlayerFactoryImpl;
import server.service.PlayerAction;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BlackjackClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Player player;
    Scanner scanner = new Scanner(System.in);

    public BlackjackClient(String serverAddress, int serverPort){
        this.connect(serverAddress, serverPort);
        Thread serverListenerThread = new Thread(this::listenToServer);
        serverListenerThread.start();
    }
    private void connect(String serverAddress, int serverPort){
        try {
            System.out.println("Enter your name: ");
            String name = scanner.next();
            socket = new Socket(serverAddress, serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            Thread serverListenerThread = new Thread(this::listenToServer);
            serverListenerThread.start();

            Thread.sleep(1000);
            out.writeUTF(name);
            out.flush();

            setPlayer(name);
        } catch (Exception e) {
            close();
            System.out.println("Error connecting to server: " + e);
        }
    }
    private void setPlayer(String name){
        this.player = new Player(name);
    }
    public Player getPlayer() {
        return player;
    }
    private void listenToServer() {
        try {
            while (true) {
                String messageFromServer = in.readUTF();
                handleServerMessage(messageFromServer);
                Object object = objectIn.readObject();
                handleServerObject(object);
            }
        } catch (IOException e) {
            close();
            System.out.println("Error reading from server: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleServerMessage(String message) {
        System.out.println(message);
    }
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e);
        }
    }
    public void handleServerObject(Object object){
        PlayerActionsFactory factory = new PlayerFactoryImpl(objectOut);
        if (object instanceof ServerInteraction serverInteraction){
            PlayerAction action = factory.createPlayerAction(serverInteraction);
            if (action != null) {
                action.execute();
            }
        }
    }
}
