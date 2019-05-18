import java.util.LinkedList;

// hand starts with 13 cards (is sorted) and then loses cards one at a time
public class Hand extends LinkedList<Card> {

  public boolean isAllHearts() {
    for (Card card : this) {
      if (card.isHeart()) {
        return false;
      }
    }
    return true;
  }

  public boolean isAllPoints() {
    for (Card card : this) {
      if (card.isHeart() || card.isQueenOfSpades()) {
        return false;
      }
    }
    return true;
  }

  public boolean hasSuit(Suit suit) {
    for (Card card : this) {
      if (card.getSuit().equals(suit)) {
        return true;
      }
    }
    return false;
  }
}