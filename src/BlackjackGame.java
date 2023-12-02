import client.BlackjackClient;

public class BlackjackGame {
    public static void main(String[] args) {
        // Example usage
                BlackjackClient client = new BlackjackClient("https://violence-give-uganda-sentence.trycloudflare.com/");
                client.sendMessage("Hello, server!");
                client.close();
    }
}
