import java.util.Arrays;
import java.util.Iterator;

public class RoundScore implements Iterable<Integer> {

  // the scores, starting with user and going clockwise
  private Integer[] scores = new Integer[4];

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

  public int getScore(int player) {
    return scores[player];
  }

  public void setScore(int player, int score) {
    scores[player] = score;
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

  @Override
  public Iterator<Integer> iterator() {
    return Arrays.asList(scores).iterator();
  }
}