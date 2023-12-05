package single_player;

import datastructures.CircularLinkedList;
import datastructures.Node;
import domain.enums.PlayerInteraction;
import domain.model.Dealer;
import domain.model.Player;

import javax.swing.*;

public class BlackjackMatch{

  private final CircularLinkedList<Player> players;
  private Player mainPlayer;
  private Node<Player> currentPlayer;
  private final Dealer dealer;

  BlackjackMatch(CircularLinkedList<Player> players, Player mainPlayer) {
    this.mainPlayer = mainPlayer;
    this.players = players;
    this.dealer = new Dealer();
    this.currentPlayer = players.getHead();
    startMatch();
  }

  public void startMatch(){
    sendWelcomeMessageToPlayers();
    dealCardsToPlayers();
    selectTurn();
  }

  private void dealCardsToPlayers(){
    for (int i = 0; i < players.size(); i++){
      if (currentPlayer.getData() == mainPlayer){
        double bet = Integer.parseInt(JOptionPane.showInputDialog("Insert the bet not greater than " + currentPlayer.getData().getCash()));
        while (bet > currentPlayer.getData().getCash()){
          bet = Integer.parseInt(JOptionPane.showInputDialog("Insert the bet not greater than " + currentPlayer.getData().getCash()));
        }
        currentPlayer.getData().setBet(bet);
      }else{
        double bet = Math.floor(Math.random() * (currentPlayer.getData().getCash()) + 1);
        currentPlayer.getData().setBet(bet);
      }
      currentPlayer = currentPlayer.getNext();
    }
    for (int i = 0; i < players.size() * 2; i++) {
      currentPlayer.getData().receiveCard(dealer.deal());
      currentPlayer = currentPlayer.getNext();
      if(i == players.size() - 1 || i == players.size() * 2 - 1){
        dealer.receiveCard(dealer.deal());
      }
      try{
        Thread.sleep(500);
        display(BlackjackTableUtil.displayTable(dealer, players));
      }catch (Exception e){
        throw new RuntimeException(e);
      }
    }
  }
  private void selectTurn() {
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      System.out.println(players.size());
      System.out.println("pos: "+i);
      System.out.println("Es el turno de: " + player.getName());
      if (player == mainPlayer){
        PlayerInteraction[] playerInteractions = PlayerInteraction.values();
        int opt = JOptionPane.showOptionDialog(null, "What do you want to do? \n your points are: " + player.getHandValue(),
                "Your turn", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerInteractions, playerInteractions[0]);
        while (opt == 0 && player.getHandValue() < 21){
          display(BlackjackTableUtil.displayTable(dealer, players));
          opt = JOptionPane.showOptionDialog(null, "What do you want to do? \n your points are: " + player.getHandValue(),
                  "Your turn", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playerInteractions, playerInteractions[0]);
          if (opt == 0) {
            this.hitCard(this.mainPlayer);
          } else {
            this.display("You stay in " + this.mainPlayer.getHandValue());
          }
        }
      }else{
        int random = (int) Math.floor(Math.random() * (1 - 0 + 1) + 0);
        while (random == 0 && player.getHandValue() < 21){
          this.hitCard(player);
          random = (int) Math.floor(Math.random() * (1 - 0 + 1) + 0);
        }
      }
    }
    dealerTurn();
  }

  private void hitCard(Player player){
    player.receiveCard(dealer.deal());
    int handValue = player.getHandValue();
    String message = "";
    if (handValue == 21) {
      message = "Player " + player.getName() + " got 21";
      currentPlayer = currentPlayer.getNext();
    } else if (handValue > 21) {
      message = "Player " + player.getName() + " is over 21";
      currentPlayer = currentPlayer.getNext();
    }
    display(BlackjackTableUtil.displayTable(dealer, players));
    if (!message.isEmpty()) {
      display(message);
    }
  }

  private void sendWelcomeMessageToPlayers(){
    StringBuilder playerNames = new StringBuilder();
    playerNames.append("Welcome to the Blackjack game\n");
    playerNames.append("------------------------------\n");
    playerNames.append("Connected players: " + players.size() + "/5\n");
    playerNames.append("------------------------------\n");
    playerNames.append("Players:\n");
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);

      playerNames.append(player.getName()).append("\n");
    }
    display(playerNames.toString());
  }

  private void display(String message){
    JOptionPane.showMessageDialog(null, message, "Blackjack!", JOptionPane.INFORMATION_MESSAGE);
  }

  public void dealerTurn(){
    while(dealer.getHandValue() < 17){
      dealer.receiveCard(dealer.deal());
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(BlackjackTableUtil.displayTable(dealer, players));
    stringBuilder.append("Dealer's score: " + dealer.getHandValue());
    stringBuilder.append("Dealer's hand: ").append(dealer.displayHand());
    stringBuilder.append("Dealer's turn is over");
    stringBuilder.append("------------------------------\n");
    stringBuilder.append("Calculating results...");
    stringBuilder.append("------------------------------\n");
    display(stringBuilder.toString());
    calculateResults();
  }

  private void calculateResults(){
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      double bet = player.getBet();
      double cash = player.getCash();
      int playerValue = player.getHandValue();
      int dealerValue = dealer.getHandValue();

      if (playerValue > 21 || (playerValue < dealerValue && dealerValue <= 21)) {
        cash -= bet;
        display(player.getName() + " lost $" + bet);
      } else if (playerValue == dealerValue) {
        display(player.getName() + " tied with the dealer");
      } else {
        double winnings = bet;
        if (playerValue == 21 && dealerValue != 21) {
          winnings *= 1.5;
        }
        cash += winnings;
        display(player.getName() + " won $" + winnings);
      }
      player.setCash(cash);
      player.setBet(0.0);
    }
  }
}
