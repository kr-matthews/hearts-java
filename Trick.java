import java.util.ArrayList;
import java.util.List;

public class Trick {

  // who plays the first card
  private int firstToPlay;
  // cards played so far, in order played
  private List<Card> cardsPlayed = new ArrayList<Card>(4);
  
  Trick(int player) {
    firstToPlay = player;
  }
  
  // can only be called after the first player has played (bad? alternatives?)
  public Suit getLeadSuit() {
    return cardsPlayed.get(0).getSuit();
  }
  
  public int getWinner() {
    // temp
    return firstToPlay;
  }
}