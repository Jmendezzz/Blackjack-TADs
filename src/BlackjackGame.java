import client.BlackjackClient;

import java.io.IOException;

public class BlackjackGame {
    public static void main(String[] args) throws IOException {
        BlackjackClient client = new BlackjackClient("localhost", 8080);
    }
}
