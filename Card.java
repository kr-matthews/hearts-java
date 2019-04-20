public class Card {

  private int rank;
  private Suit suit;

  public Card(int rank, Suit suit) {
    this.suit = suit;
    this.rank = rank;
  }

  @Override
  public String toString() {
    return rank + " of " + suit;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Card other = (Card) obj;
    if (rank != other.rank)
      return false;
    if (suit != other.suit)
      return false;
    return true;
  }

}
