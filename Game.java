import java.util.List;

public class Game {

  private String[] players = new String[4];
  private boolean gameOver = false;

  private List<List<Integer>> gameScoresHistory = null;
  private int[] gameScores = { 0, 0, 0, 0 };

  public List<List<Integer>> getGameScoresHistory() {
    return gameScoresHistory;
  }

  public void setGameScoresHistory(List<List<Integer>> gameScoresHistory) {
    this.gameScoresHistory = gameScoresHistory;
  }

  public String[] getPlayers() {
    return players;
  }

  public void setPlayers(String[] players) {
    this.players = players;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public int[] getGameScores() {
    return gameScores;
  }

  public void setGameScores(int[] gameScores) {
    this.gameScores = gameScores;
  }

  // private Round currentRound = new Round(new Deck());

  public Game(String player1, String player2, String player3, String player4) {
    players[0] = player1;
    players[1] = player2;
    players[2] = player3;
    players[3] = player4;
  }

  public Game(String player1) {
    this(player1, "West", "North", "East");
  }

  public Game() {
    this("You");
  }

  public void playRound() {
    Round round = new Round(new Deck());
    int trickWinner = -1;
    for (int player = 0; player < 4; player++) {
      if (round.getPlayerHand(player).contains(new Card(2, Suit.SPADES))) {
        trickWinner = player;
      }
    }

    while (!round.isRoundOver()) {
      round.playtrick(this, trickWinner);

      // temp
      round.roundIsOver();
    }

    System.err.println("TODO: update scores");
    // temp
    gameOver = true;
  }

}
