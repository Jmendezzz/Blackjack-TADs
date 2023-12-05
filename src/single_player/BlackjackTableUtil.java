package single_player;

import datastructures.CircularLinkedList;
import domain.model.Dealer;
import domain.model.Player;

public class BlackjackTableUtil {

  public static String displayTable(Dealer dealer, CircularLinkedList<Player> players) {
    StringBuilder table = new StringBuilder();

    table.append("╔═════════════════════════════╗\n");
    table.append("║                 Blackjack Table                ║\n");
    table.append("╚═════════════════════════════╝\n");

    table.append("Dealer's Hand:\n");
    table.append(dealer.displayHand());
    table.append("------------------------------\n");
    table.append("\n");

    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      table.append("Player " + (i + 1) + ": " + player.getName() + " | Cash: $" + player.getCash() + " | Bet: $ "+ player.getBet() + "\n");
      table.append("Hand:\n");
      table.append(player.displayHand());
      table.append("------------------------------\n");
    }

    return table.toString();
  }

}
