public class Card implements Comparable<Card> {
  
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

  public boolean isHeart() {
    return getSuit().equals(Suit.HEARTS);
  }
  
  public boolean isQueenOfSpades() {
    return equals(Deck.QUEEN_OF_SPADES);
  }
  
  public boolean isPointCard() {
    return isHeart() || isQueenOfSpades();
  }
  
  @Override
  public String toString() {
    return rank.toString() + suit.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((rank == null) ? 0 : rank.hashCode());
    result = prime * result + ((suit == null) ? 0 : suit.hashCode());
    return result;
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

  @Override
  public int compareTo(Card card) {
    int suitOrder = this.getSuit().compareTo(card.getSuit());
    if (suitOrder == 0) {
      return this.getRank().compareTo(card.getRank());
    }
    return suitOrder;
  }
}
