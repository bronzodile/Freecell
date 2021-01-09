import java.util.ArrayList;

public class FreeCell implements Location
{

    private Card card;

    public boolean isEmpty() {
        return (card == null);
    }
    
    public void remove() {
        card = null;
    }
    
    public void place(Card c) {
        card = c;
        card.setLocation(this);
    }
    
    public Card peek() {
        return card;
    }
    public String toString() {
        return "" + card;
    }
    public ArrayList<Card> getStackToMove(Card currCard){
        ArrayList<Card> stack = new ArrayList<Card>();
        stack.add(currCard);
        return(stack);
    }
}
