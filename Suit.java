import java.util.Comparator;

class SuitComparator implements Comparator<Suit> {

  public int compare(Suit s1, Suit s2) {
    if (s1.equals(s2)) {
      return 0;
    } else if (s1.ordinal() < s2.ordinal()) {
      return -1;
    } else {
      return 1;
    }
  }

}

public enum Suit {
  // order is used for sorting, don't change (game would still work)
  CLUBS, DIAMONDS, SPADES, HEARTS;
  
  @Override
  public String toString() {
    return Character.toString(this.name().charAt(0));
  }
}