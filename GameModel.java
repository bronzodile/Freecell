import java.util.ArrayList;
/**
 * Logical model of the FreeCell game.
 */
public class GameModel
{
    private Deck deck;
    private FreeCell[] freeCells;
    private HomeCell[] homeCells;
    private Tableau[] tableaus;

    public GameModel()
    {

        freeCells = new FreeCell[4];
        homeCells = new HomeCell[4];
        for (int i = 0; i < 4; i++) {
            freeCells[i] = new FreeCell();
            homeCells[i] = new HomeCell();
        }
        tableaus = new Tableau[8];
        deck = new Deck(1);
        for (int i = 0; i < 4; i++) {
            tableaus[i] = new Tableau();
            for (int j = 0; j < 7; j++) {
                tableaus[i].place(deck.getCard());
            }
        }
        for (int i = 4; i < 8; i++) {
            tableaus[i] = new Tableau();
            for (int j = 0; j < 6; j++) {
                tableaus[i].place(deck.getCard());
            }
        }

    }

    public void move(int r, int s, int where) {
        Card currentCard = deck.findCard(r, s);
        ArrayList<Card> stackToMove = currentCard.getLocation().getStackToMove(currentCard);
        if (!currentCard.getMoveable()) return;
        switch (where) {
            case 1: case 2: case 3: case 4:
                if (stackToMove.size() > 1) return;
                if (freeCells[where - 1].isEmpty()) {
                    Location l = currentCard.getLocation();
                    l.remove();
                    freeCells[where - 1].place(currentCard);
                }
                break;
            case 5: case 6: case 7: case 8:
                if (stackToMove.size() > 1) return;
                if (homeCells[where - 5].isEmpty()) {
                    if (currentCard.getRank() == 1) {
                        Location l = currentCard.getLocation();
                        l.remove();
                        homeCells[where - 5].place(currentCard);
                        currentCard.setMoveable(false);
                    }
                } else if (homeCells[where - 5].peek().getRank() + 1 == currentCard.getRank()) {
                    if (homeCells[where - 5].peek().getSuite() == currentCard.getSuite()) {
                        Location l = currentCard.getLocation();
                        l.remove();
                        homeCells[where - 5].place(currentCard);
                        currentCard.setMoveable(false);
                    }
                }
                break;
            case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16:
                if (tableaus[where - 9].isEmpty()) {
                    if (stackToMove.size() > emptySlotsCount()) return;
                    for(Card c: stackToMove){
                        Location l = c.getLocation();
                        l.remove();
                        tableaus[where - 9].place(c);
                    }
                } else if (tableaus[where - 9].peek().getRank() - 1 == currentCard.getRank()) {
                    if (stackToMove.size() - 1 > emptySlotsCount()) return;
                    if (isOpposite(tableaus[where - 9].peek().getSuite(), currentCard.getSuite())) {
                        for(Card c: stackToMove){
                            Location l = c.getLocation();
                            l.remove();
                            tableaus[where - 9].place(c);
                        }
                    }
                }
                break;
            default:
                break;
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

    public void printMe()
    {
        for (int i = 0; i < 4; i++) {
            System.out.print(freeCells[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.print(homeCells[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.println(tableaus[i]);
        }
    }

    public ArrayList<CardSpecification> getTableau(int tableauNumber) {
        return tableaus[tableauNumber - 1].getCards();
    }

    public ArrayList<CardSpecification> getFreeCell() {
        ArrayList<CardSpecification> list = new ArrayList<CardSpecification>();
        for (int i = 0; i < 4; i++) {
            if (freeCells[i].peek() != null) {
                list.add(new CardSpecification(freeCells[i].peek().getRank(),freeCells[i].peek().getSuite()));
            } else {
                list.add(null);
            }
        }
        return list;
    }

    public ArrayList<CardSpecification> getHomeCell(int cellNumber) {
        return homeCells[cellNumber - 1].getCards();
    }

    private int emptySlotsCount(){
        int count = 0;
        for(FreeCell fc: freeCells) {
            if (fc.isEmpty()){
                count++;
            }
        }
        for(Tableau t: tableaus){
            if (t.isEmpty()){
                count++;
            }
        }
        return count;
    }
}
