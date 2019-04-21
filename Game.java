import java.util.ArrayList;
import java.util.List;

public class Game {

  // names of players, set by constructor
  private String[] playerNames = new String[4];
  // contains the information of all previous rounds
  private CumulativeHistory cumulativeHistory = new CumulativeHistory();
  // the current round
  private Round currentRound;

  public String getPlayerName(int player) {
    return playerNames[player];
  }

  public boolean isGameOver() {
    return cumulativeHistory.getMaxScore() >= 100;
    // temp below:
    // return cumulativeHistory.getRoundNumber() > 2;
  }

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

  // TODO
  public void playRound() {
    // the process of playing a round (dealing out 4 hands, playing 13 tricks,
    // tallying scores, updating game history)
    currentRound = new Round(new Deck());

    // pass 3 cards left/right/across/nowhere/...
    currentRound.passThreeCards(cumulativeHistory.getRoundNumber());

    // the player to start the next trick is the one who won the previous trick
    // (for the first hand, this will be whoever has the 2 of CLUBS)
    int nextToPlay = -1;
    for (int player = 0; player < 4; player++) {
      if (currentRound.getPlayerHand(player).contains(new Card(Rank.N2, Suit.CLUBS))) {
        nextToPlay = player;
      }
    }

    while (!currentRound.isRoundOver()) {
      // while the round is not over (players still have cards (13 tricks)), play a
      // trick
      currentRound.playtrick(this, nextToPlay);
    }

    cumulativeHistory.addRoundScore(currentRound.getRoundScores());
    System.out.println();
    displayHistory();
  }

  public boolean playerWins(int player) {
    return isGameOver() & cumulativeHistory.getCurrentScore().getScore(player) == winningScore();
  }

  private int winningScore() {
    CumulativeScore currentScores = cumulativeHistory.getCurrentScore();
    return Math.min(Math.min(currentScores.getScore(0), currentScores.getScore(1)),
        Math.min(currentScores.getScore(2), currentScores.getScore(3)));
  }

  public List<Integer> winners() {
    List<Integer> winnerList = new ArrayList<Integer>(4);
    for (int player = 0; player < 4; player++) {
      if (playerWins(player)) {
        winnerList.add(player);
      }
    }
    return winnerList;
  }

  // feels messy
  public void displayWinners() {
    System.out.print("\nWinner");
    if (winners().size() > 1) {
      System.out.print("s");
    }
    System.out.print(": ");
    for (int player : winners()) {
      System.out.print(getPlayerName(player));
      if (player != winners().get(winners().size() - 1)) {
        System.out.print(" & ");
      }
    }
    System.out.println("!");
  }

  // TODO: improve formatting
  public void displayHistory() {
    for (String player : playerNames) {
      System.out.print(player + "  ");
    }
    System.out.println();
    for (CumulativeScore scores : cumulativeHistory) {
      System.out.println(scores);
    }
  }

}
