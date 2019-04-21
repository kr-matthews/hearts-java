public class Game {

  // names of players, set by constructor
  private String[] playerNames = new String[4];
  // contains the information of all previous rounds
  private CumulativeHistory cumulativeHistory = new CumulativeHistory();
  // the current round
  private Round currentRound;

  // TODO: improve spacing/formatting
  public void printPlayerNames() {
    for (String name : playerNames) {
      System.out.print(name + " ");
    }
  }

  public CumulativeHistory getCumulativeHistory() {
    return cumulativeHistory;
  }

  public boolean isGameOver() {
    // return cumulativeHistory.getMaxScore() >= 100);
    // temp
    return cumulativeHistory.getRoundNumber() > 1;
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
    // tallying scores, updating cumulative history)
    currentRound = new Round(new Deck());

    // need to do passing 3 cards! (most rounds)
    System.err.println("TODO: Pass 3 cards left/right/across/nowhere");

    // the player to start the next trick is the one who won the previous trick
    // (for the first hand, this will be whoever has the 2 of CLUBS)
    int nextToPlay = -1;
    for (int player = 0; player < 4; player++) {
      if (currentRound.getPlayerHand(player).contains(new Card(Rank.N2, Suit.CLUBS))) {
        nextToPlay = player;
      }
    }

    while (!currentRound.isRoundOver()) {
      // while the round is not over (players still have cards), play a trick
      currentRound.playtrick(this, nextToPlay);
    }

    System.err.println("TODO: update scores, display scores to user");
    cumulativeHistory.addRoundScore(currentRound.getRoundScores());
    System.out.println();
    printPlayerNames();
    System.out.println();
    cumulativeHistory.printScoreHistory();
  }

}
