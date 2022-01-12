package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;

/** BlueSpace defines the blue space object. This extends the abstract class Space
 *
 * <p> When a player lands on this space, the player draws a blue card. The player collects money from the bank if their job matches the one on the card. Otherwise, they pay either the bank or another player </p>
 *
 * <p> The space executes the methods required based on the blue card drawn. The player's details are updated in the methods, </p>
 *
 * <p> The space also executes the methods that require a player to roll a number. </p>
 */
public class BlueSpace extends Space
{
    /** instantiates a BlueSpace object with a description
     *
     */
    public BlueSpace ()
    {
        super ("Draw a Blue Card");
    }

    /** draws an BlueCard and executes the methods required based on the card drawn. Returns an BlueCard if the card requires the current player to roll a number.
     *
     * @param playerIndex index of the current player
     * @param players ArrayList of the players in the game
     * @param deck deck of blue cards where a card will be drawn
     * @return BlueCard blue card that requires the player to roll a number. Null if it doesn't.
     */
    public static BlueCard drawCard (int playerIndex, ArrayList<Player> players, BlueDeck deck)
    {
        BlueCard card = deck.drawTopCard();
        Main.gameBoard.bDispCard = card;
        String name = card.getName();
        int match = -1;  // -1 means no match
        int i;

        // if blue card match current player's career, bank pays current player $15000
        if (card.doesCareerMatch(players.get(playerIndex).getCareer()))
            players.get(playerIndex).updateCash(15000);

            // if blue card doesn't match current player's career
        else {
            // find another player that matches blue card career
            for (i = 0; i < players.size(); i++)
                if (i != playerIndex)
                    if (card.doesCareerMatch(players.get(i).getCareer()) && !players.get(i).isRetired())
                        match = i;

            if (name.equals("Lawsuit")) {
                if (match != -1)
                    card.lawsuit(players.get(playerIndex), players.get(match));
                else
                    card.lawsuit(players.get(playerIndex));
            }

            else if (name.equals("Salary Tax Due")) {
                if (match != -1)
                    card.salaryTaxDue(players.get(playerIndex), players.get(match));
                else
                    card.salaryTaxDue(players.get(playerIndex));
            }

            else if (name.equals("Tip the Server")) {
                Main.gameBoard.bCard = card;
                return card;
            }

            else if (name.equals("Ski Accident")) {
                if (match != -1)
                    card.skiAccident(players.get(playerIndex), players.get(match));
                else
                    card.skiAccident(players.get(playerIndex));
            }

            else if (name.equals("Computer Repair")) {
                Main.gameBoard.bCard = card;
                return card;
            }

            else if (name.equals("World Cup")) {
                if (match != -1)
                    card.worldCup(players.get(playerIndex), players.get(match), players.size());
                else
                    card.worldCup(players.get(playerIndex), players.size());
            }

            else if (name.equals("F1 Race")) {
                if (match != -1)
                    card.f1Race(players.get(playerIndex), players.get(match));
                else
                    card.f1Race(players.get(playerIndex));
            }
        }

        return null;
    }

    /** executes the methods of the cards that require the player to roll a number.
     *
     * @param playerIndex index of the current Player
     * @param rand rolled number
     * @param players ArrayList of the players in the game
     */
    public static void rollMethods (int playerIndex, int rand, ArrayList<Player> players)
    {
        BlueCard card = Main.gameBoard.bCard;
        String name = card.getName();
        int match = -1;  // -1 means no match
        int i;

        for (i = 0; i < players.size(); i++)
            if (i != playerIndex)
                if (card.doesCareerMatch(players.get(i).getCareer()) && !players.get(i).isRetired())
                    match = i;

        if (name.equals("Tip the Server")) {
            if (match != -1)
                card.tipTheServer(players.get(playerIndex), players.get(match), rand);
            else
                card.tipTheServer(players.get(playerIndex), rand);
        }

        else if (name.equals("Computer Repair")) {
            if (match != -1)
                card.computerRepair(players.get(playerIndex), players.get(match), rand);
            else
                card.computerRepair(players.get(playerIndex), rand);
        }
    }
}