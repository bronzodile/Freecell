import java.util.ArrayList;
import java.awt.Graphics;

public class Layer
{
    private ArrayList<CardImage> list;

    public Layer() {
        list = new ArrayList<CardImage>();
    }

    public void addCard(CardImage card) {
        list.add(card);
    }
    
    public void showLayer(Graphics g) {
        for (CardImage c: list) {
            g.drawImage(c.image(), c.getX(), c.getY(), null);
        }
    }
    
    public void removeCard(CardImage card) {
        int ind = list.indexOf(card);
        if (ind >= 0) {
            list.remove(ind);
        }
    }
    
    public int length() {
        return list.size();
    }
}
