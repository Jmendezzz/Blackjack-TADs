package server.utils;

import datastructures.CircularLinkedList;
import domain.model.Dealer;
import domain.model.Player;
import server.sockets.PlayerSocket;

public class BlackjackTableUtil {
  public static String displayTable(Dealer dealer, CircularLinkedList<PlayerSocket> players) {
    StringBuilder table = new StringBuilder();

    table.append("╔══════════════════════════╗\n");
    table.append("║            Blackjack Table           ║\n");
    table.append("╚══════════════════════════╝\n\n");

    table.append("Dealer's Hand:\n");
    table.append(dealer.displayHand());
    table.append("------------------------------\n\n");

    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i).getPlayer();
      table.append("Player " + (i + 1) + ": " + player.getName() + " | Cash: $" + player.getCash() + "\n");
      table.append("------------------------------\n");
      table.append("Hand:\n");
      table.append(player.displayHand());
      table.append("------------------------------\n");
    }

    return table.toString();
  }
}
