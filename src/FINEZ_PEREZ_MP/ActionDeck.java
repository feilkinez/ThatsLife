package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;

/** ActionDeck defines a deck of action cards
 *
 * <p> Each deck consists of 50 cards: 20 Collect from Bank, 20 Pay the Bank, 5 Pay the Player, 5 Collect from Player. </p>
 *
 * <p> Each deck is shuffled upon creation. </p>
 *
 * <p> The topmost unused/undrawn card can be drawn from the deck. Topmost is defined as the lowest index, it starts from index 0. </p>
 *
 * <p> The number of cards that have been drawn from the deck can be returned for processing. </p>
 *
 * <p> In the event that all cards have been drawn, the deck will be reshuffled. </p>
 */
public class ActionDeck
{
    private ArrayList<ActionCard> actionCards;
    private int ctr;  // counts how many cards have been drawn

    public static final int NUMACTION = 50;

    /** instantiates an ActionDeck object with 20 Collect from Bank cards, 20 Pay the Bank cards, 5 Pay the Player cards, and 5 Collect from Player cards; 50 cards total. Shuffles it afterwards.
     */
    public ActionDeck ()
    {
        actionCards = new ArrayList<>();
        ActionCard card;
        ctr = 0;

        int i, tempAmt;

        String[] bankCollect = new String[] {"Tax refund", "Sell an item", "Bonus payday",
                "Setup school", "Write a book"};

        String[] bankPay = new String[] {"Buy an item", "Visit a place", "Hiking", "Watch a show",
                "Win a competition", "Traffic violation"};

        Integer[] amts = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000};
        List<Integer> amtList = Arrays.asList(amts);
        Collections.shuffle(amtList);
        amts = amtList.toArray(new Integer[amtList.size()]);

        for (i = 0; i < NUMACTION; i++)
        {
            tempAmt = amts[i % 9];

            // COLLECT FROM BANK
            if (i < NUMACTION * 0.40) {
                card = new ActionCard("Collect from the Bank", bankCollect[i % 5], false, false, tempAmt);
                actionCards.add(card);
            }

            // PAY THE BANK
            else if (i < NUMACTION * 0.80) {
                card = new ActionCard("Pay the Bank", bankPay[i % 6], false, false, -tempAmt);
                actionCards.add(card);
            }

            // PAY THE PLAYER
            else if (i < NUMACTION * 0.84) {
                card = new ActionCard("Pay the Player", "Lawsuit", true, false, -tempAmt);
                actionCards.add(card);
            }
            else if (i < NUMACTION * 0.90) {
                card = new ActionCard("Pay the Player", "Christmas Bonus", true, true, -tempAmt);
                actionCards.add(card);
            }

            // COLLECT FROM PLAYER
            else if (i < NUMACTION * 0.94) {
                card = new ActionCard("Collect from the Player", "File a Lawsuit", true, false, tempAmt);
                actionCards.add(card);
            }
            else {
                card = new ActionCard("Collect from the Player", "It's your Birthday", true, true, tempAmt);
                actionCards.add(card);
            }

            shuffle();
        }

    }

    /** shuffles the contents/cards of this deck
     *
     */
    public void shuffle ()
    {
        Collections.shuffle(actionCards);
    }

    /** returns the topmost unused card of this deck, the topmost being the lowest index. Also updates ctr for the number of cards drawn from this deck.
     *
     *  @return the topmost unused ActionCard
     */
    public ActionCard drawTopCard ()
    {
        ctr++;

        noCardLeft();
        return actionCards.get(ctr-1);
    }

    /** checks if 50 cards have been drawn which means all cards have been drawn. If all cards have been drawn, shuffles this deck and resets ctr. Displays "Action Deck Reshuffled" to acknowledge reshuffling of deck
     */
    public void noCardLeft ()
    {
        if (ctr == NUMACTION) {
            shuffle();
            ctr = 0;
            System.out.println ("Action Deck Reshuffled");
        }
    }
}