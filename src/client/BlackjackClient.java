package client;

import java.io.*;
import java.net.Socket;
import java.net.URI;

public class BlackjackClient {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public BlackjackClient(String serverUrl) {
        try {
            URI uri = new URI(serverUrl);
            String host = uri.getHost();
            int port = uri.getPort() != -1 ? uri.getPort() : 80; // Use default port 80 if not specified
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            System.out.println("Error connecting to server: " + e);
        }
    }
    public void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("Error sending message: " + e);
        }
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
