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
  // is it better to have an instance variable and set it to true once somebody
  // plays a heart?
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
      // temp 2 lines
      System.out.print("Player " + player + "'s hand: ");
      System.out.println(playerHands[player]);
    }
  }

  // TODO: finish this function (or "method")
  public void playtrick(Game game, int firstPlayer) {
    // temp line
    System.out.println("First to play: " + firstPlayer);
    currentTrick = new Trick(firstPlayer);

    // each player plays a card in turn
    for (int player = firstPlayer; player < firstPlayer + 4; player++) {
      playCard(player % 4);
    }

    // see who won the trick
    int winner = currentTrick.getWinner();
    // update their score
    roundScores.setScore(winner, roundScores.getScore(winner) + currentTrick.getPoints());
    // set them as first player for next trick
    firstPlayer = winner;

    // temp lines
    System.out.println("Winner: " + winner);
    System.out.println("Points: " + currentTrick.getPoints());
    System.out.println();

  }

  private void playCard(int player) {
    // TODO: temp cardToPlay assignment; need to actually pick a proper card
    Card cardToPlay = playerHands[player].get(0);

    currentTrick.playCard(cardToPlay);
    playerHands[player].remove(cardToPlay);
    System.out.println("Player " + player + " plays " + cardToPlay);
  }

  public void passThreeCards(int roundNumber) {
    // TODO: actually pass cards
    System.out.println("TODO: Pass 3 cards left/right/across/nowhere (round " + roundNumber + ")");
  }
}