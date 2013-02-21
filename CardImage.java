import java.awt.image.*;

public class CardImage
{
    private int x;
    private int y;
    private BufferedImage img;
    private boolean draggable;    
    private int layer;

    public CardImage(int xPos, int yPos, BufferedImage imgIn) {
        x = xPos;
        y = yPos;
        img = imgIn;
        draggable = true;
        layer = 0;
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
    
    public int layer() {
        return layer;
    }
    
    public void upOneLayer() {
        layer = 1;
    }
    
    public void downOneLayer() {
        layer = 0;
    }
}
