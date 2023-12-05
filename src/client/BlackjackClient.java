package client;

import domain.enums.ServerInteraction;
import domain.model.Player;
import infraestructure.factory.PlayerActionsFactory;
import infraestructure.factory.impl.PlayerFactoryImpl;
import server.service.PlayerAction;

import java.io.*;
import java.net.Socket;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackClient {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
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
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Thread serverListenerThread = new Thread(this::listenToServer);
            serverListenerThread.start();

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
                Object object = in.readObject();
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
        PlayerActionsFactory factory = new PlayerFactoryImpl(out);
        if(object instanceof String message){
            Set<String> validInteractions = EnumSet.allOf(ServerInteraction.class).stream()
                                                    .map(Enum::toString)
                                                    .collect(Collectors.toSet());

            if (validInteractions.contains(message)) {

                ServerInteraction serverInteraction = ServerInteraction.valueOf(message);
                if (serverInteraction.equals(ServerInteraction.TURN)) {
                    PlayerAction action = factory.createPlayerAction(serverInteraction);
                    if (action != null) {
                        action.execute();
                    }
                }

            }else {
                handleServerMessage(message);
            }
        }
        try{
            out.reset();
        }catch (IOException e){
            System.out.println("Error resetting output stream: " + e);
        }
    }
}
