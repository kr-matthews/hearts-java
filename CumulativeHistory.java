import java.util.ArrayList;
import java.util.List;

public class CumulativeHistory {
  // list of score at the end of each previous round
  private List<CumulativeScore> previousRoundScores = new ArrayList<CumulativeScore>(15);

  public CumulativeScore getCurrentScore() {
    if (previousRoundScores.size() == 0) {
      return new CumulativeScore(0, 0, 0, 0);
    }
    return previousRoundScores.get(previousRoundScores.size() - 1);
  }

  public int getRoundNumber() {
    return previousRoundScores.size() + 1;
  }

  // TODO: improve spacing/formatting
  public void printScoreHistory() {
    for (CumulativeScore scores : previousRoundScores) {
      for (int score : scores.getScores()) {
        System.out.print(score + "  ");
      }
      System.out.println();
    }
  }

  public void addRoundScore(RoundScore scores) {
    if (scores.whoShotTheMoon() >= 0) {
      // if somebody shot the moon, then ...
      if (false) { // TODO: figure out is shooting the moon would cause the below
        // if giving everyone else 26 without ending the game would cause that player to
        // use, then subtract 26 from that player
        for (int player = 0; player < 4; player++) {
          if (player == scores.whoShotTheMoon()) {
            scores.getScores()[player] = -26;
          } else {
            scores.getScores()[player] = 0;
          }
        }
      } else {
        // else give all other players 26
        for (int player = 0; player < 4; player++) {
          if (player == scores.whoShotTheMoon()) {
            scores.getScores()[player] = 0;
          } else {
            scores.getScores()[player] = 26;
          }
        }
      }
    } // if nobody shot the moon, then just add scores on
      // if somebody shot the moon, then we have adjusted the round scores accordingly
    previousRoundScores.add(new CumulativeScore(getCurrentScore().getScores().get(0) + scores.getScores()[0],
        getCurrentScore().getScores().get(1) + scores.getScores()[1],
        getCurrentScore().getScores().get(2) + scores.getScores()[2],
        getCurrentScore().getScores().get(3) + scores.getScores()[3]));
  }

}