import java.util.ArrayList;
import java.util.List;

public class Trick {

  // who plays the first card, set by constructor
  private int firstToPlay;
  // cards played so far, in order played
  private List<Card> cardsPlayed = new ArrayList<Card>(4);

  Trick(int player) {
    firstToPlay = player;
  }

  public int getNumberOfCardsPlayed() {
    return cardsPlayed.size();
  }

  // can only be called after the first player has played (bad? alternatives?)
  public Suit getLeadSuit() {
    return cardsPlayed.get(0).getSuit();
  }

  // assumes all four cards have been played, finds trick winner
  public int getWinner() {
    // the index, in order of cards played, of winner so far
    int winnerSoFarIndex = 0;
    // skip the first card played because we initialize to that being winner so far
    for (int index = 1; index < 4; index++) {
      if (cardsPlayed.get(winnerSoFarIndex).getSuit().equals(cardsPlayed.get(index).getSuit())
          & cardsPlayed.get(winnerSoFarIndex).compareTo(cardsPlayed.get(index)) < 0) {
        winnerSoFarIndex = index;
      }
    }
    // now adjust based on which player played first
    return (winnerSoFarIndex + firstToPlay) % 4;
  }

  // add the next played card
  public void playCard(Card card) {
    cardsPlayed.add(card);
  }

  // how many points does the trick contain
  // works no matter how many cards have been played
  public int getPoints() {
    int points = 0;
    for (Card card : cardsPlayed) {
      // iterate over all 4 cards played
      if (card.isHeart()) {
        // if the card was a heart then add a point
        points += 1;
      } else if (card.isQueenOfSpades()) {
        // if the card was the queen of spades then add 13 points
        points += 13;
      }
    }
    return points;
  }

}