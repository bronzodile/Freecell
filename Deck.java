/**
 * @author Robert Noonan September 21, 2004 CS 301 Fall  204
 *
 *Deck deals with the processes of a card deck including initializing a
 *deck, randomly dealing the cards.
 */

import java.util.Random;
import java.util.Date;

public class Deck {
    private Card[ ] deck = new Card[52];
    private Random random;
    private int count = 0;
    
    /**
     *Constructor - Creates a deck of 52 cards with 13 cards in spades,
     *13 cards in hearts, 13 cards in diamonds, 13 cards in clubs.
     *@param seed - the random seed that is used in the shuffle
     */
    public Deck(long seed)  {
        random = new Random(seed);
        // char[ ] suite = {'C', 'D', 'H', 'S'}; // clubs .. spades
        for (int s = 0; s < 4; s++) {
            for (int r = 1; r < 14; r++) { // ace 2 .. king
                deck[count] = new Card(r, s);
                count++;
            }//for
        }//for
    }//constructor


    /**
       Creates a deck of cards using clock as a seed
    */
    public Deck( ) { this(new Date( ).getTime( )); }

    
    /**
     * returns a random card from the deck
     * @return a random card from the deck
     * @pre cannot be called more than 52 times
     */
    public Card getCard( )  {
        int ix = random.nextInt(count);
        Card c = deck[ix];
        deck[ix] = deck[count - 1];
        deck[count - 1] = c;
        count--;
        return c;
    }//getCard
    
    public Card findCard(int r, int s) {
        for(int i = 0; i < 52; i++) {
            if (r == deck[i].getRank()) {
                if (s == deck[i].getSuite()) {
                    return deck[i];
                }
            }
        }
        return null;
    }

}//Deck

