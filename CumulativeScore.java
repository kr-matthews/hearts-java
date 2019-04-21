import java.util.ArrayList;
import java.util.List;

public class CumulativeScore {

  // the scores, starting with user and going clockwise
  private List<Integer> scores = new ArrayList<Integer>(4);

  CumulativeScore(int player1Score, int player2Score, int player3Score, int player4Score) {
    scores.add(player1Score);
    scores.add(player2Score);
    scores.add(player3Score);
    scores.add(player4Score);
  }

  public List<Integer> getScores() {
    return scores;
  }

  @Override
  public String toString() {
    return "CumulativeScore " + scores;
  }
}