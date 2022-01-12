package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;

/** HouseDeck defines a deck of house cards
 *
 * <p> Each deck consists of 6 cards. </p>
 *
 * <p> Each deck is shuffled upon creation. </p>
 *
 * <p> Any card can be drawn according to index. </p>
 *
 * <p> A card can be returned. The deck will be shuffled afterwards. </p>
 */
public class HouseDeck
{
    private ArrayList<HouseCard> houseCards;

    public static final int NUMHOUSE = 6;

    /** instantiates a HouseDeck object with 6 cards: Apartment, Condominium, Cabin, Cottage, Bungalow, and Mansion. It also creates each card with its corresponding name, buy price, sell price, and end price.
     *
     */
    public HouseDeck ()
    {
        houseCards = new ArrayList<>();

        String[] houseNames = {"Apartment", "Condominium", "Cabin", "Cottage", "Bungalow", "Mansion"};

        int i;
        int endPrice = 1000;

        for (i = 1; i <= NUMHOUSE; i++) {
            houseCards.add(new HouseCard(houseNames[i-1], 20000 * i, 15000 * i, (20000 * i) + endPrice));
            endPrice *= 2;
        }

        shuffle();
    }

    /** shuffles the contents/cards of this deck
     *
     */
    public void shuffle ()
    {
        Collections.shuffle(houseCards);
    }

    /** returns the HouseCard in the indicated index
     *
     *  @param house int index of the house to be returned
     *  @return a HouseCard
     */
    public HouseCard drawCard (int house)
    {
        return houseCards.remove(house);
    }

    /** adds the HouseCard hc to the deck
     *
     * @param hc HouseCard to be added to the deck
     */
    public void returnCard (HouseCard hc)
    {
        houseCards.add(hc);
    }
}