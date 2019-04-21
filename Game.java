// most of this hasn't been revisiting since restructuring to include Score/History classes

import java.util.ArrayList;
import java.util.List;

public class Game {

  // names of players, set by constructor
  private String[] playerNames = new String[4];
  // contains the information of all previous rounds
  private CumulativeHistory cumulativeHistory = new CumulativeHistory();
  
  // used to terminate the while loop once a player reaches at least 100 points
  private boolean gameOver = false;

  // TODO: improve spacing/formatting
  public void printPlayerNames() {
    for (String name : playerNames) {
      System.out.print(name + " ");
    }
  }

  public String[] getPlayers() {
    return playerNames;
  }
  
  public CumulativeHistory getCumulativeHistory() {
    return cumulativeHistory;
  }

  public void setPlayers(String[] players) {
    this.playerNames = players;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  // would it be better to have this like this?
  // private Round currentRound = new Round(new Deck());

  public Game(String player1, String player2, String player3, String player4) {
    playerNames[0] = player1;
    playerNames[1] = player2;
    playerNames[2] = player3;
    playerNames[3] = player4;
  }

  public Game(String player1) {
    // default names of opponents:
    this(player1, "West", "North", "East");
  }

  public Game() {
    // default name of user
    this("You");
  }

  public void playRound() {
    // the process of playing a round (dealing out 4 hands, playing 13 tricks,
    // tallying scores)
    // should round be variable directly in the game class ("currentRound")?
    Round round = new Round(new Deck());

    // need to do passing 3 cards! (most rounds)
    System.err.println("TODO: Pass 3 cards left/right/across/nowhere");

    // who won the previous hand, to determine who goes first next hand
    // for the first hand, this will be whoever has the 2 of CLUBS
    int nextToPlay = -1;
    for (int player = 0; player < 4; player++) {
      if (round.getPlayerHand(player).contains(new Card(Rank.N2, Suit.CLUBS))) {
        nextToPlay = player;
      }
    }

    while (!round.isRoundOver()) {
      // while the round is not over (players still have cards), play a trick
      round.playtrick(this, nextToPlay);

      // temp
      round.roundIsOver();
    }

    System.err.println("TODO: update scores, display scores to user");
    cumulativeHistory.addRoundScore(round.getRoundScores());
    System.out.println();
    printPlayerNames();
    System.out.println();
    cumulativeHistory.printScoreHistory();
    // temp
    gameOver = true;
  }

}
