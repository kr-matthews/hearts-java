class Round {

  private int[] roundScores = { 0, 0, 0, 0 };

  private Hand[] playerHands = new Hand[4];

  private boolean heartsBroken = false;
  private boolean roundOver = false;

  public boolean areHeartsBroken() {
    return heartsBroken;
  }

  public boolean isRoundOver() {
    return roundOver;
  }

  public void heartsAreBroken() {
    heartsBroken = true;
  }

  public void roundIsOver() {
    roundOver = true;
  }

  public Hand getPlayerHand(int player) {
    return playerHands[player];
  }

  public Round(Deck deck) {
    for (int i = 0; i < playerHands.length; i++) {
      playerHands[i] = new Hand();
    }

    for (int i = 0; i < 52; i++) {
      playerHands[i % 4].add(deck.get(i));
    }
  }

  public void playtrick(Game game, int firstPlayer) {
    System.err.println("TODO: play trick; play 4 cards and update scores");
    System.out.println("Next Player: " + firstPlayer);
    Card[] cardsPlayed = new Card[4];
    for (int player = 0; player < 4; player++) {
      playCard(game, cardsPlayed, player % 4);
      System.out.println(cardsPlayed[player]);
    }

  }

  private void playCard(Game game, Card[] cardsPlayed, int player) {
    // temp
    cardsPlayed[player] = playerHands[player].get(0);
  }
}