import java.util.Comparator;

class CardComparator implements Comparator<Card> {

  public int compare(Card c1, Card c2) {
    if (c1.getSuit().equals(c2.getSuit())) {
      return new RankComparator().compare(c1.getRank(), c2.getRank());
    } else {
      return new SuitComparator().compare(c1.getSuit(), c2.getSuit());
    }
  }
}

public class Card {

  private Rank rank;
  private Suit suit;

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Card(Rank rank, Suit suit) {
    this.suit = suit;
    this.rank = rank;
  }

  @Override
  public String toString() {
    return rank.toString() + suit.toString();
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
