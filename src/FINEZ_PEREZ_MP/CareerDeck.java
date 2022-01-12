package FINEZ_PEREZ_MP;

import java.util.*;

/** CareerDeck defines a deck of Career Cards
 *
 * <p> The CareerDeck consists of seven (7) cards, corresponding to each career in the game (Lawyer, Accountant, Computer Consultant, Doctor, Server, Racecar Driver, and Athlete). </p>
 *
 * <p> The first four (4) careers mentioned requires a degree from the player, whereas the following three (3) do not. </p>
 *
 * <p> The topmost unused/undrawn card can be drawn from the deck. Topmost is defined as the lowest index, it starts from index 0. </p>
 *
 * <p> The number of cards that have been drawn from the deck can be returned for processing. </p>
 */

public class CareerDeck
{
    private ArrayList<CareerCard> careerCards;

    public static final int NUMCAREER = 7;

    /** instantiates a CareerDeck object with 7 cards: Lawyer, Accountant, Computer Consultant, Doctor, Server, Racecar Driver, and Athlete. It also creates each card with its corresponding name, degree requirement, and minimum/maximum number of pay raise. Depending on the parameter, the instantiation of CareerDeck will either be shuffled or unshuffled.
     *
     * @param shuffled boolean to check if CareerDeck instatiation needs to be shuffled
     */
    public CareerDeck (boolean shuffled)
    {
        careerCards = new ArrayList<>();

        careerCards.add(new CareerCard("Lawyer", true, 5, 8));
        careerCards.add(new CareerCard("Accountant", true, 4, 7));
        careerCards.add(new CareerCard("Computer Consultant", true, 3, 7));
        careerCards.add(new CareerCard("Doctor", true, 5, 8));
        careerCards.add(new CareerCard("Server", false, 1, 4));
        careerCards.add(new CareerCard("Racecar Driver", false, 2, 8));
        careerCards.add(new CareerCard("Athlete", false, 1, 6));

        if (shuffled)
            shuffle();
    }

    /** shuffles the contents/cards of this deck
     *
     */
    public void shuffle ()
    {
        Collections.shuffle(careerCards);
    }

    /** draws the top card of this deck, indicated by index 0
     *
     * @return top career card drawn
     */
    public CareerCard drawTopCard ()
    {
        return careerCards.remove(0);
    }

    /** returns the card passed when this method is called and shuffles the deck
     *
     * @param cc CareerCard being returned to this deck
     */
    public void returnCard (CareerCard cc)
    {
        careerCards.add(cc);

        shuffle();
    }
}