import java.util.Collections;

class Round {

  // the tally of hearts and queen of spades collected so far per player
  private RoundScore roundScores = new RoundScore();
  // the hands of the players, list of (at most) 13 cards
  private Hand[] playerHands = new Hand[4];
  // the current trick being played
  private Trick currentTrick;

  // essentially whether hearts have been broken: false iff no or all hearts have
  // been played
  // better to have an instance variable and set it to true once somebody plays a
  // heart??
  public boolean canLeadHeart() {
    return (roundScores.getScore(0) + roundScores.getScore(1) + roundScores.getScore(2) + roundScores.getScore(3))
        % 13 != 0;
  }

  // is the round over, so that we can exit the loop playing the round
  public boolean isRoundOver() {
    return playerHands[0].size() + playerHands[1].size() + playerHands[2].size() + playerHands[3].size() == 0;
  }

  public Hand getPlayerHand(int player) {
    return playerHands[player];
  }

  public RoundScore getRoundScores() {
    return roundScores;
  }

  // initializing a round requires a deck (new decks are automatically shuffled)
  public Round(Deck deck) {
    // initialize Hands
    for (int i = 0; i < playerHands.length; i++) {
      playerHands[i] = new Hand();
    }

    // deal out deck cards to hands
    for (int i = 0; i < 52; i++) {
      playerHands[i % 4].add(deck.get(i));
    }

    for (int player = 0; player < 4; player++) {
      Collections.sort(playerHands[player]);
      //temp 2 lines
      System.out.print("Player " + player + "'s hand: ");
      System.out.println(playerHands[player]);
    }
  }

  // TODO: need to look at and probably completely redo
  public void playtrick(Game game, int firstPlayer) {
    System.err.println("TODO: play trick; play 4 cards and update scores");
    System.out.println("Next Player: " + firstPlayer);
    Card[] cardsPlayed = new Card[4];

    for (int player = 0; player < 4; player++) {
      playCard(game, cardsPlayed, player % 4);
      // temp
      System.out.println(cardsPlayed[player]);
    }

  }

  // TODO: need to do
  private void playCard(Game game, Card[] cardsPlayed, int player) {
    // temp
    cardsPlayed[player] = playerHands[player].get(0);
    playerHands[player].remove(cardsPlayed[player]);
  }
}