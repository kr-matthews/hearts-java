// most of this hasn't been revisiting since restructuring to include Score/History classes

public class Hearts {

  private static int myMax(int[] ints) {
    // function to compute the max of an array, giving "-\infty" for empty arrays
    int theMax = Integer.MIN_VALUE;
    for (int i : ints) {
      theMax = Math.max(i, theMax);
    }
    return theMax;
  }

  public static void main(String[] args) {

    System.err.println("TODO: Get user's name here.");

    Game game = new Game();

    while (!game.isGameOver()) { // while the game is not over (nobody has reached 100 points) play another round
      game.playRound();
    }

    System.out.println("\nWinner: ???");
    game.printPlayerNames();
    System.out.println();
    game.getCumulativeHistory().printScoreHistory();

    // Testing
    CumulativeHistory hist = new CumulativeHistory();

    System.out.println(hist.getCurrentScore());
    hist.printScoreHistory();
    System.out.println();

    int[] array = { 0, 26, 0, 0 };
    hist.addRoundScore(new RoundScore(array));

    System.out.println(hist.getCurrentScore());
    hist.printScoreHistory();
    System.out.println();

    int[] array2 = { 0, 26, 0, 0 };
    hist.addRoundScore(new RoundScore(array2));

    System.out.println(hist.getCurrentScore());
    hist.printScoreHistory();
    System.out.println();

    int[] array3 = { 0, 26, 0, 0 };
    hist.addRoundScore(new RoundScore(array3));

    System.out.println(hist.getCurrentScore());
    hist.printScoreHistory();
    System.out.println();

    int[] array4 = { 0, 0, 0, 26 };
    hist.addRoundScore(new RoundScore(array4));
// this should cause 26 to be subtracted from East
    System.out.println(hist.getCurrentScore());
    hist.printScoreHistory();
    System.out.println();

  }

}
