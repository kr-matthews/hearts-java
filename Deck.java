import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

  public static final Card TWO_OF_CLUBS = new Card(Rank.N2,Suit.CLUBS);

  public static final Card QUEEN_OF_SPADES = new Card(Rank.Q,Suit.SPADES);
  
  public Deck() {
    super(52);

    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        this.add(new Card(rank, suit));
      }
    }

    Collections.shuffle(this);
    // temp 2 lines
    System.out.println();
    System.out.println("(temp) Deck is: " + this);
  }

}