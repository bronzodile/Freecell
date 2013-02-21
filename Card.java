
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own
    private int rank;
    private int suite;
    private Location location;
    private boolean moveable;

    /**
     * Constructor for objects of class Card
     */
    public Card(int r, int s)
    {
        rank = r;
        suite = s;
        moveable = false;
        location = null;
    }

    public String toString()
    {        
        return convertRank(rank) + convertSuite(suite);
    }

    private String convertRank(int r) {
        String result = "";
        switch (r) {
            case 1:  result = "A";
                     break;
            case 2:  result = "2";
                     break;
            case 3:  result = "3";
                     break;
            case 4:  result = "4";
                     break;
            case 5:  result = "5";
                     break;
            case 6:  result = "6";
                     break;
            case 7:  result = "7";
                     break;
            case 8:  result = "8";
                     break;
            case 9:  result = "9";
                     break;
            case 10:  result = "10";
                     break;
            case 11:  result = "J";
                     break;
            case 12:  result = "Q";
                     break;
            case 13:  result = "K";
                     break;
        }
        return result;
    }
    private String convertSuite(int s) {
        String result = "";
        switch (s) {
            case 0:  result = "H";
                     break;
            case 1:  result = "D";
                     break;
            case 2:  result = "S";
                     break;
            case 3:  result = "C";
                     break;
        }
        return result;
    }
    public int getRank(){
        return rank;
    }
    public int getSuite(){
        return suite;
    }
    public Location getLocation(){
        return location;
    }
    public void setLocation(Location l) {
        location = l;
    }
    public void setMoveable(boolean newMoveable) {
        moveable = newMoveable;
    }
    public boolean getMoveable() {
        return moveable;
    }
}
