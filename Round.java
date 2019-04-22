import java.util.Collections;
import java.util.Scanner;

class Round {

  // the tally of hearts and queen of spades collected so far per player
  private RoundScore roundScores = new RoundScore();
  // the hands of the players, list of (at most) 13 cards
  private Hand[] playerHands = new Hand[4];
  // the current trick being played
  private Trick currentTrick;

  // essentially whether hearts have been broken: false iff no or all hearts have
  // been played or it's the first round
  // is it better to have an instance variable and set it to true once somebody
  // plays a heart?
  public boolean canLeadHeart() {
    return (roundScores.getScore(0) + roundScores.getScore(1) + roundScores.getScore(2) + roundScores.getScore(3))
        % 13 != 0 & getTrickNumber() != 0;
  }

  private int getTrickNumber() {
    return 14 - Math.max(Math.max(getPlayerHand(0).size(), getPlayerHand(1).size()),
        Math.max(getPlayerHand(2).size(), getPlayerHand(3).size()));
  }

  // is the round over, so that we can exit the loop playing the round
  public boolean isRoundOver() {
    return getPlayerHand(0).size() + getPlayerHand(1).size() + getPlayerHand(2).size() + getPlayerHand(3).size() == 0;
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
    for (int player = 0; player < 4; player++) {
      playerHands[player] = new Hand();
    }

    // deal out deck cards to hands
    for (int i = 0; i < 52; i++) {
      getPlayerHand(i % 4).add(deck.get(i));
    }

    for (int player = 0; player < 4; player++) {
      Collections.sort(getPlayerHand(player));
      // temp 2 lines
      // System.out.print("(temp) Player " + player + "'s hand: ");
      // System.out.println(getPlayerHand(player));
    }
  }

  public void playtrick(int firstPlayer, String[] playerNames) {

    currentTrick = new Trick(firstPlayer);

    // each player plays a card in turn
    for (int player = firstPlayer; player < firstPlayer + 4; player++) {
      playCard(player % 4, playerNames[player % 4]);
    }

    // see who won the trick
    int winner = currentTrick.getWinner();
    // update their score
    roundScores.setScore(winner, roundScores.getScore(winner) + currentTrick.getPoints());
    // set them as first player for next trick
    firstPlayer = winner;

    System.out.println("Winner: " + playerNames[winner]);
    System.out.println("Points: " + currentTrick.getPoints());
    System.out.println();

  }

  public int currentTrickWinner() {
    return currentTrick.getWinner();
  }

  private void playCard(int player, String playerName) {
    // the card to be played
    Card cardToPlay = Deck.TWO_OF_CLUBS;

    // if it's not the user, apply built-in strategy
    if (player != 0) {
      cardToPlay = computerStrategy(player, getPlayerHand(player), roundScores);
    }

    if (player == 0) {
      cardToPlay = getPlayerHand(player).get(getUserCardIndex());
    }

    currentTrick.playCard(cardToPlay);
    getPlayerHand(player).remove(cardToPlay);
    System.out.println(playerName + " plays " + cardToPlay);
    //System.out.println("(temp) remaining hand: " + getPlayerHand(player));
  }

  // as below, not sure where this belongs
  private int getUserCardIndex() {
    int index = 0;
    System.out.println();
    for (int i = 0; i < getPlayerHand(0).size(); i++) {
      System.out.print((i + 1) + ": " + getPlayerHand(0).get(i) + "  ");
    }
    System.out.println();

    // this is probably terrible
    Scanner in = new Scanner(System.in);
    do {
      do {
        System.out.println("Please select the index of a card from your hand to play:");
        try {
          index = in.nextInt();
        } catch (Exception e) {
          in.nextLine();
        }
      } while (index < 1 || index > getPlayerHand(0).size());
    } while (!validCardToPlay(0, getPlayerHand(0).get(index - 1), true));
    
    // why doesn't this line work?
    //in.close();

    return index - 1;
  }

  // this feels like the wrong way/place to do/put a strategy
  // really it also needs the current score in addition to these arguments
  // these shouldn't have access to other player's hands
  // TODO: improve computer strategy
  private Card computerStrategy(int player, Hand playerHand, RoundScore roundScores2) {
    Card cardToPlay = Deck.TWO_OF_CLUBS;
    for (Card card : playerHand) {
      if (validCardToPlay(player, card, false)) {
        cardToPlay = card;
      }
    }
    return cardToPlay;
  }

  // check if player is allowed to play selected card
  // if false, print out message explaining why not allowed
  public boolean validCardToPlay(int player, Card card, boolean printWarning) {
    // you must play a card from your hand
    if (!getPlayerHand(player).contains(card)) {
      if (printWarning) {
        System.out.println("You don't have that card in your hand.");
      }
      return false;
    }

    // you must follow suit if possible
    if (currentTrick.getNumberOfCardsPlayed() > 0) {
      if (getPlayerHand(player).hasSuit(currentTrick.getLeadSuit())
          & !currentTrick.getLeadSuit().equals(card.getSuit())) {
        // if not leading and you can follow suit then you must
        if (printWarning) {
          System.out.println("You must follow the lead suit.");
        }
        return false;
      }
    }

    if (getTrickNumber() == 1) {
      // if this is the first trick of the round...
      if (currentTrick.getNumberOfCardsPlayed() == 0 & !card.equals(Deck.TWO_OF_CLUBS)) {
        // ...and you are leading then you must play the 2 of clubs
        if (printWarning) {
          System.out.println("You must lead the first trick with the 2 of clubs.");
        }
        return false;
      }
      if (card.isPointCard() & !getPlayerHand(player).isAllPoints()) {
        // ...then you can't play points (unless only have points)
        if (printWarning) {
          System.out.println("You can't play a point card on the opening trick.");
        }
        return false;
      }
      // otherwise this isn't the first trick of the round;

    } else if (currentTrick.getNumberOfCardsPlayed() == 0) {
      // if you are leading (and it's not the first trick)...
      if (!canLeadHeart() & card.isHeart() & !getPlayerHand(player).isAllHearts()) {
        // ... and hearts aren't broken then you can't play a heart (unless you only
        // have hearts)
        if (printWarning) {
          System.out.println("You cannot lead with a heart until hearts have been broken.");
        }
        return false;
      }
    }
    return true;
  }

  public void passThreeCards(int roundNumber) {
    // TODO: actually pass cards
    System.out.println("TODO: Pass 3 cards left/right/across/nowhere (round " + roundNumber + ")");
    System.out.println();
  }
}