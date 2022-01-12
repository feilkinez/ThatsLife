package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

/** BlueDeck defines a deck of blue cards
 *
 * <p> Each deck consists of 7 cards. </p>
 *
 * <p> Each deck is shuffled upon creation. </p>
 *
 * <p> The topmost unused/undrawn card can be drawn from the deck. Topmost is defined as the lowest index, it starts from index 0. </p>
 *
 * <p> The number of cards that have been drawn from the deck can be returned for processing. </p>
 *
 * <p> In the event that all cards have been drawn, the deck will be reshuffled. </p>
 */
public class BlueDeck
{
    private ArrayList<BlueCard> blueCards;
    private int ctr;

    public static final int NUMBLUE = 7;

    /** instantiates a BlueDeck object with 7 cards: Lawsuit, Salary Tax Due, Computer Repair, Ski Accident, Tip the Server, F1 Race, and World Cup. Shuffles it afterwards. It also assigns the CareerCard that matches the event each BlueCard represents, along with an amount related to the amount to be paid.
     */
    public BlueDeck ()
    {
        CareerDeck jobMatches = new CareerDeck (false);
        blueCards = new ArrayList<>();
        ctr = 0;

        blueCards.add(new BlueCard("Lawsuit", jobMatches.drawTopCard(), ThreadLocalRandom.current().nextInt(5, 16) * 10000));
        blueCards.add(new BlueCard("Salary Tax Due", jobMatches.drawTopCard(), 0));
        blueCards.add(new BlueCard("Computer Repair", jobMatches.drawTopCard(), 5000));
        blueCards.add(new BlueCard("Ski Accident", jobMatches.drawTopCard(), 10000));
        blueCards.add(new BlueCard("Tip the Server", jobMatches.drawTopCard(), 1000));
        blueCards.add(new BlueCard("F1 Race", jobMatches.drawTopCard(), 10));
        blueCards.add(new BlueCard("World Cup", jobMatches.drawTopCard(), 5000));

        shuffle();
    }

    /** shuffles the contents/cards of this deck
     *
     */
    public void shuffle ()
    {
        Collections.shuffle(blueCards);
    }

    /** returns the topmost unused card of this deck, the topmost being the lowest index. Also updates ctr for the number of cards drawn from this deck.
     *
     *  @return the topmost unused BlueCard
     */
    public BlueCard drawTopCard ()
    {
        ctr++;

        noCardLeft ();
        return blueCards.get(ctr-1);
    }

    /** checks if 7 cards have been drawn which means all cards have been drawn. If all cards have been drawn, shuffles this deck and resets ctr. Displays "Blue Deck Reshuffled" to acknowledge reshuffling of deck
     */
    public void noCardLeft ()
    {
        if (ctr == NUMBLUE) {
            shuffle();
            ctr = 0;
            System.out.println ("Blue Deck Reshuffled");
        }
    }
}