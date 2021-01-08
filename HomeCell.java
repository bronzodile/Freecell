import java.util.*;

public class HomeCell implements Location
{
    // instance variables - replace the example below with your own
    private ArrayList<Card> cards;
    
    public HomeCell()
    {       
        cards = new ArrayList<Card>();
    }

    public boolean isEmpty() {
        return (cards.isEmpty());
    }
    
    public void remove() {
    }
    
    public void place(Card c) {
        cards.add(c);
        c.setLocation(this);
    }
    
    public Card peek() {
        return cards.get(cards.size() - 1);
    }
    
    public String toString() {
        if (isEmpty()) return null;
        Iterator<Card> i = cards.iterator();
        StringBuilder s = new StringBuilder();
        while (i.hasNext()) {
            if (s.length() != 0) {
                s.append(' ');
            }
            s.append(i.next());
        }
        return s.toString();
    }
    
    public ArrayList<P> getCards() {
        ArrayList<P> list = new ArrayList<P>();
        for (Card c: cards) {
            list.add(new P(c.getRank(), c.getSuite()));
        }
        return list;
    }
}
