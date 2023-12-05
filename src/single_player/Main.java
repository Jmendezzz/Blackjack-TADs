package single_player;

import datastructures.CircularLinkedList;
import domain.model.Player;


import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    CircularLinkedList<Player> players = createNPCS();
    String name = JOptionPane.showInputDialog(null, "What is your name?");
    Player mainPlayer = new Player(name);
    players.add(mainPlayer);

    BlackjackMatch blackjackMatch = new BlackjackMatch(players,mainPlayer);

  }

  public static CircularLinkedList<Player> createNPCS(){
    CircularLinkedList<Player> players = new CircularLinkedList<>();
    players.add(new Player("English"));
    players.add(new Player("Santiago"));
    players.add(new Player("Yaser"));
    players.add(new Player("Iván"));
    return players;
  }
}
