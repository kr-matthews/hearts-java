import java.util.Comparator;

class RankComparator implements Comparator<Rank> {

  public int compare(Rank r1, Rank r2) {
    if (r1.equals(r2)) {
      return 0;
    } else if (r1.ordinal() < r2.ordinal()) {
      return -1;
    } else {
      return 1;
    }
  }

}

public enum Rank {
  // order is used for sorting, don't change (game would not work)
  N2('2'), N3('3'), N4('4'), N5('5'), N6('6'), N7('7'), N8('8'), N9('9'), T('T'), J('J'), Q('Q'), K('K'), A('A');
  
  // instead of supplying values, should just have toString take the final character in the name?
  private char rank;
  
  Rank(char rank) {
    this.rank = rank;
  }
  
  @Override
  public String toString() {
    return Character.toString(rank);
  }
}