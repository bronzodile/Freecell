import java.util.*;

/**
 * Write a description of class Tableau here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tableau implements Location
{
    // instance variables - replace the example below with your own
    private ArrayList<Card> cards;
    
    /**
     * Constructor for objects of class Tableau
     */
    public Tableau()
    {       
        cards = new ArrayList<Card>();
    }

    public boolean isEmpty() {
        return (cards.isEmpty());
    }
    
    public void remove() {
        cards.remove(cards.size() - 1);
        updateMoveable();
    }
    
    public void place(Card c) {
        cards.add(c);
        c.setLocation(this);
        updateMoveable();
    }
    
    public Card peek() {
        return cards.get(cards.size() - 1);
    }
    
    public String toString() {
        Iterator i = cards.iterator();
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
    
    private void updateMoveable() {
        if (!cards.isEmpty()) {
            int size = cards.size();
            Card thisCard = cards.get(size - 1);
            Card prevCard = null;
            thisCard.setMoveable(true);
            for (int i = size - 2; i >= 0; i--) {
                prevCard = cards.get(i);
                prevCard.setMoveable(false);
            }
            /* for (int i = size - 2; i >= 0; i--) {
                prevCard = cards.get(i);
                if (thisCard.getRank() - 1 == prevCard.getRank() && isOpposite(thisCard.getSuite(), prevCard.getSuite())) {
                    prevCard.setMoveable(true);
                    thisCard = prevCard;
                } else {
                    break;
                }
            } */
        }    
    }   
    private boolean isOpposite(int suite1, int suite2) {
        if ((suite1 == 0) || (suite1 == 1)) {
            if ((suite2 == 2) || (suite2 == 3)) {
                return true;
            }
        } else {
            if ((suite2 == 0) || (suite2 == 1)) {
                return true;
            }
        }
        return false;
    }
}
