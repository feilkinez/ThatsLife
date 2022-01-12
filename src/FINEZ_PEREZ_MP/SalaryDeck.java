package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;

/** SalaryDeck defines a deck of salary cards
 *
 * <p> Each deck consists of 10 cards. </p>
 *
 * <p> Each deck is shuffled upon creation. </p>
 *
 * <p> The topmost card can be drawn from the deck. Topmost is defined as the lowest index, index 0. </p>
 *
 * <p> A card can be returned. The deck will be shuffled afterwards. </p>
 */
public class SalaryDeck
{
    private ArrayList<SalaryCard> salaryCards;

    public static final int NUMSALARY = 10;

    /** instantiates a SalaryDeck object with 10 cards of varying salary and tax due amounts
     *
     */
    public SalaryDeck ()
    {
        salaryCards = new ArrayList<>();

        int i;

        Integer[] amts = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        List<Integer> amtList = Arrays.asList(amts);
        Collections.shuffle(amtList);
        amts = amtList.toArray(new Integer[amtList.size()]);

        for (i = 1; i < NUMSALARY; i++)
            salaryCards.add(new SalaryCard(10000 * (i+1), amts[i]));

        shuffle();
    }

    /** shuffles the contents/cards of this deck
     *
     */
    public void shuffle ()
    {
        Collections.shuffle(salaryCards);
    }

    /** draws the top card of this deck, indicated by index 0
     *
     * @return top salary card drawn
     */
    public SalaryCard drawTopCard ()
    {
        return salaryCards.remove(0);
    }

    /** returns the card passed when this method is called and shuffles the deck
     *
     * @param sc SalaryCard being returned to this deck
     */
    public void returnCard (SalaryCard sc)
    {
        salaryCards.add(sc);

        shuffle();
    }
}