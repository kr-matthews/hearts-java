import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
  
  public Deck() {
    super(52);
    
    for (Suit suit: Suit.values()) {
      for (Rank rank: Rank.values()) {
        this.add(new Card(rank,suit));
      }
    }
    
    Collections.shuffle(this);
    //temp
    System.out.println("Deck is: " + this);
  }
  
}