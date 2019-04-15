import java.util.List;

public class Game {

  private String[] players = new String[4];

  private List<List<Integer>> gameScoresHistory = null;
  private int[] gameScores = { 0, 0, 0, 0 };

  private class Round {

    private int[] roundScores = { 0, 0, 0, 0 };

    private Hand[] playerHands = new Hand[4];
  }

  private Round currentRound = new Round();

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

}
