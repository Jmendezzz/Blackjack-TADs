package server.utils;

import datastructures.CircularLinkedList;
import domain.model.Dealer;
import server.sockets.PlayerSocket;

public class BlackjackTableUtil {
  public static String  displayTable(Dealer dealer, CircularLinkedList<PlayerSocket> players) {
    //TODO:
    //clearConsole();
    StringBuilder table = new StringBuilder();


    table.append("  ┌───────────────────────────────────────────────────────────────────────┐" +"\n");
    table.append("  │                         Blackjack Table                               │" + "\n");
    table.append("  └───────────────────────────────────────────────────────────────────────┘"+ "\n");
    table.append("   ┌─────────────────────────────────────────────────────────────────────┐"+ "\n");
    table.append("   │                          Dealer                                     │"+ "\n");
    table.append("   │                         ┌───────────┐                               │"+ "\n");
    table.append(dealer.displayHand() + "\n");
    table.append("   │                         └───────────┘                               │" + "\n");
    table.append("   │                                                                     │" + "\n");
    table.append("   │                                                                     │"+ "\n");
    table.append("   └─────────────────────────────────────────────────────────────────────┘"+ "\n");
    table.append("   ┌─────────────────────────────────────────────────────────────────────┐"+ "\n");
    table.append("   │Player 1                 Player 2                 Player 3           │"+ "\n");

    for (int i = 0; i < players.size(); i++) {
      table.append("   │" + "\n");
      table.append(players.get(i).getPlayer().displayHand() + "\n");
    }

    table.append("   └─────────────────────────────────────────────────────────────────────┘"+ "\n");
    table.append("  ┌───────────────────────────────────────────────────────────────────────┐"+ "\n");
    table.append("  │ Instructions: Enter your bet and commands (hit, stand, etc.)          │"+ "\n");
    table.append("  └───────────────────────────────────────────────────────────────────────┘"+ "\n");

    return table.toString();
  }

}
