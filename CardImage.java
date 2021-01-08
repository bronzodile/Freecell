import java.awt.image.*;

public class CardImage
{
    private int x;
    private int y;
    private BufferedImage img;
    private boolean draggable;
    
    public CardImage(int xPos, int yPos, BufferedImage imgIn) {
        x = xPos;
        y = yPos;
        img = imgIn;
        draggable = true;        
    }
    
    public void updateCoordinates( int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public BufferedImage image() {
        return img;
    }
    
    public void setDraggable(boolean newVal) {
        draggable = newVal;
    }
    
    public boolean isDraggable() {
        return draggable;
    }    
}
