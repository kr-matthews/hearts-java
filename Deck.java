import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends ArrayList<Card> {
  
  public Deck() {
    super(52);
    
    for (Suit suit: Suit.values()) {
      for (int rank=1;rank<=13;rank++) {
        this.add(new Card(rank,suit));
      }
    }
    
    Collections.shuffle(this);
    System.out.println("Deck is: " + this);
  }
  
}