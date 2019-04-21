import java.util.ArrayList;
import java.util.List;

// most of this hasn't been revisiting since restructuring to include Score/History classes

public class Hearts {

  public static void main(String[] args) {

    System.out.println("TESTING");
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
    System.out.println("TESING END");
    System.out.println();
    // end testing

    System.err.println("TODO: Get user's name here.");

    Game game = new Game();

    while (!game.isGameOver()) {
      // while the game is not over (nobody has reached 100 points) play another round
      game.playRound();
    }

    game.displayWinners();
    
    game.displayHistory();

  }

}
