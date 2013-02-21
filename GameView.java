import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.ArrayList;

public class GameView extends JPanel implements MouseListener, MouseMotionListener
{
    private GameController gc;
    private CardImage [][] cards;
    private boolean canDrag = false;
    private int dragFromX = 0;
    private int dragFromY = 0;
    private CardImage currentCard = null;
    private static final int LEFT_MARGIN = 50;
    private static final int HOME_LEVEL = 60;
    private static final int TABLEAU_LEVEL = 200;
    private static final int TABLEAU_STEP = 100;
    private static final int CARD_STEP = 30;
    private static final int WIDTH = 73;
    private static final int HEIGHT = 97;
    private Layer[] layers;
    private int currRank;
    private int currSuite;

    public GameView(GameController gc) {
        this.gc = gc;
        setBackground(new Color(107, 106, 104));
        cards = new CardImage[4][13];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[i][j] = new CardImage(500 + i * 150, 20 + j * 30, getImage(i, j + 1));
            }
        }
        setPreferredSize(new Dimension(900,600));
        addMouseListener(this);
        addMouseMotionListener(this);
        layers = new Layer[20];
        for (int i = 0; i < 20; i++) {
            layers[i] = new Layer();
        }
    }

    public void paintComponent(Graphics g) {            
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            g.drawRect(LEFT_MARGIN + i * TABLEAU_STEP, HOME_LEVEL, WIDTH, HEIGHT);
        }
        for (Layer l: layers) {
            l.showLayer(g);
        }
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
                
        currentCard = findCard(x, y);
        if (currentCard != null) {
            canDrag = true;
            dragFromX = x;
            dragFromY = y;
            for (int i = 0; i < 19; i++) {
                layers[i].removeCard(currentCard);
            }
            layers[19].addCard(currentCard);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (canDrag) {
            currentCard.updateCoordinates(currentCard.getX() + e.getX() - dragFromX, currentCard.getY() + e.getY() - dragFromY);
            
            dragFromX = e.getX();
            dragFromY = e.getY();

            this.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        canDrag = false;
        if (currentCard != null) {
            int x = e.getX();
            int y = e.getY();
            for (int i = 0; i < 20; i++) {
                layers[i] = new Layer();
            }            
            gc.click(currRank + 1, currSuite, getDropLocation(x, y, 0, 0));
        }
    }  
    
    public void setTableau(int tableauNumber, ArrayList<P> list) {
        int cardCounter = 0;
        CardImage tempCard = null;
        for (P p: list) {
            tempCard = cards[p.suite][p.rank - 1];
            tempCard.updateCoordinates(LEFT_MARGIN + (tableauNumber - 1) * TABLEAU_STEP, TABLEAU_LEVEL + cardCounter * CARD_STEP);
            layers[cardCounter].addCard(tempCard);
            cardCounter++;
        }
        
    }
    
    public void setFreeCell(ArrayList<P> list) {
        int cellCounter = 0;
        CardImage tempCard = null;        
        for (P p: list) {
            if (p != null) {
                tempCard = cards[p.suite][p.rank - 1];
                tempCard.updateCoordinates(LEFT_MARGIN + cellCounter * TABLEAU_STEP, HOME_LEVEL);
                layers[0].addCard(tempCard);
            }
            cellCounter++;
        }
    }
    
    public void setHomeCell(int cellNumber, ArrayList<P> list) {
        CardImage tempCard = null;
        for (P p: list) {
            tempCard = cards[p.suite][p.rank - 1];
            tempCard.updateCoordinates(LEFT_MARGIN + (cellNumber + 4 - 1) * TABLEAU_STEP, HOME_LEVEL);
            layers[0].addCard(tempCard);
        }
        
    }

    public void mouseExited(MouseEvent e) {}
    public void mouseMoved   (MouseEvent e) {}  // ignore these events
    public void mouseEntered (MouseEvent e) {}  // ignore these events
    public void mouseClicked (MouseEvent e) {}  // ignore these events


    private BufferedImage getImage(String filename) {
        try {
            InputStream in = getClass().getResourceAsStream(filename);
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
            System.exit(1);
        }
        return null;
    }

    private BufferedImage getImage(int i, int j) {
        char c = ' ';        
        switch(i) {
            case 0:
            c = 'h';
            break;
            case 1:
            c = 'd';
            break;
            case 2:
            c = 's';
            break;
            case 3:
            c = 'c';
            break;
        }
        return getImage("images/" + j + c + ".gif");
    }
    private CardImage findCard(int x, int y) {
        CardImage tempCard = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (cards[i][j].isDraggable() &&
                    x >= cards[i][j].getX() &&
                    x <= cards[i][j].getX() + cards[i][j].image().getWidth() &&
                    y >= cards[i][j].getY() &&
                    y <= cards[i][j].getY() + cards[i][j].image().getHeight())
                {
                    if (tempCard == null) {
                        tempCard = cards[i][j];
                        currRank = j;
                        currSuite = i;
                    } else {
                        if (tempCard.getY() < cards[i][j].getY()) {
                            tempCard = cards[i][j];
                            currRank = j;
                            currSuite = i;
                        }
                    }
                }
            }
        }
        return tempCard;
    }
    
    private int getDropLocation(int x1, int y1, int x2, int y2){
        if (x1 < LEFT_MARGIN) return 0;
        if (x1 > LEFT_MARGIN + 7 * TABLEAU_STEP + WIDTH) return 0;
        if (y1 < HOME_LEVEL) return 0;
        for (int i = 0; i < 8; i++) {
            if (x1 >= LEFT_MARGIN + i * TABLEAU_STEP && x1 <= LEFT_MARGIN + (i + 1) * TABLEAU_STEP) {
                if (y1 <= (HOME_LEVEL + HEIGHT + TABLEAU_LEVEL) / 2) {
                    return i + 1;
                } else {
                    return i + 9;
                }
            }
        }
        return 0;        
    }
}