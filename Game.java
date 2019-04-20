import java.util.ArrayList;
import java.util.List;

public class Game {

  // names of players, set by constructor
  private String[] players = new String[4];
  // used to terminate the while loop once a player reaches at least 100 points
  private boolean gameOver = false;

  // position i has the cumulative scores of each player up to the end of ith
  // round
  private List<List<Integer>> gameScoresHistory = null;
  // don't need this because it's just the final entry in gameScoresHistory
  // private int[] gameScores = { 0, 0, 0, 0 };

  public List<List<Integer>> getGameScoresHistory() {
    return gameScoresHistory;
  }

  public void addToGameScoresHistory(List<Integer> roundScores) {
    // the new list of scores to add to the history
    List<Integer> newCumulativeScores = new ArrayList<Integer>(4);
    for (int player = 0; player < 4; player++) {
      if (gameScoresHistory.size() > 0) {
        // if this is not the first round, then add given round scores to current game scores
        newCumulativeScores.add(gameScoresHistory.get(gameScoresHistory.size() - 1).get(player) + roundScores.get(player));
      } else {
        // if this is the first round, then just insert the round score as the game score
        newCumulativeScores.add(roundScores.get(player));
      }
    }
    // actually add new scores to game scores history
    gameScoresHistory.add(newCumulativeScores);
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

  // would it be better to have this like this?
  // private Round currentRound = new Round(new Deck());

  public Game(String player1, String player2, String player3, String player4) {
    players[0] = player1;
    players[1] = player2;
    players[2] = player3;
    players[3] = player4;
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
    // the process of playing a round (dealing out 4 hands, playing 13 tricks, tallying scores)
    // should round be variable directly in the game class ("currentRound")?
    Round round = new Round(new Deck());
    // who won the previous hand, to determine who goes first next hand
    
    // need to do passing 3 cards! (most rounds)
    System.err.println("TODO: Pass 3 cards left/right/across/nowhere");
    
    int trickWinner = -1;
    for (int player = 0; player < 4; player++) {
      // for the first round, set trick1winner to whoever has the 2 of SPADES
      if (round.getPlayerHand(player).contains(new Card(Rank.N2, Suit.SPADES))) {
        trickWinner = player;
      }
    }

    while (!round.isRoundOver()) {
      // while the round is not over (players still have cards), play a trick
      round.playtrick(this, trickWinner);

      // temp
      round.roundIsOver();
    }

    System.err.println("TODO: update scores");
    // temp
    gameOver = true;
  }

}
