import java.util.Arrays;

public class RoundScore {

  // the scores, starting with user and going clockwise
  private int[] scores = new int[4];

  RoundScore() {
    for (int player = 0; player < 4; player++) {
      scores[player] = 0;
    }
  }

  RoundScore(int[] scores) {
    for (int player = 0; player < 4; player++) {
      this.scores[player] = scores[player];
    }
  }

  public int[] getScores() {
    return scores;
  }

  public int whoShotTheMoon() {
    // return -1 if nobody shot the moon
    int shootingPlayer = -1;
    for (int player = 0; player < 4; player++) {
      if (scores[player] == 26) {
        shootingPlayer = player;
      }
    }
    return shootingPlayer;
  }

  @Override
  public String toString() {
    return "RoundScore [" + Arrays.toString(scores) + "]";
  }
}