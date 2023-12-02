package client;

import model.Player;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.util.Scanner;

public class BlackjackClient {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Player player;
    Scanner scanner = new Scanner(System.in);

    public BlackjackClient(String serverAddress, int serverPort) throws IOException {
        this.connect(serverAddress, serverPort);
        this.setPlayer();
        Thread serverListenerThread = new Thread(this::listenToServer);
        serverListenerThread.start();
    }
    private void connect(String serverAddress, int serverPort){
        try {
            socket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            System.out.println("Error connecting to server: " + e);
        }
    }
    private void setPlayer() throws IOException {
        System.out.println("Enter your name: ");
        String name = scanner.next();
        this.player = new Player(name);
        this.player.setCash(500.0);
        System.out.println("Welcome " + this.player.getName() + "!");
    }
    private void listenToServer() {
        try {
            while (true) {
                String messageFromServer = in.readLine();
                if (messageFromServer != null) {
                    handleServerMessage(messageFromServer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from server: " + e);
        }
    }
    private void handleServerMessage(String message) {
        System.out.println("Received from server: " + message);
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
}
