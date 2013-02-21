
/**
 * Write a description of interface Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Location
{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the result produced by sampleMethod 
     */
    void place(Card c);
    void remove();
    boolean isEmpty();
    Card peek();
}
