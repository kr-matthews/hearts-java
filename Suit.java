public enum Suit {
  // order is used for sorting, don't change (game would still work)
  CLUBS, DIAMONDS, SPADES, HEARTS;

  @Override
  public String toString() {
    return Character.toString(this.name().charAt(0));
  }
}