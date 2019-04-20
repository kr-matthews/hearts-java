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

    while (!game.isGameOver()) {
      // while the game is not over (nobody has reached 100 points) play another round
      game.playRound();
    }
    
    System.err.println("TODO: Display results and winner.");
  }

}
