package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;

/** ActionCard defines the action card object to be drawn by a player
 *
 * <p> Each card has a name and description that determines the action done towards the player who drew the card. </p>
 *
 * <p> An action card is created with 5 parameters which cannot be changed after its creation. These parameters will determine the action. </p>
 *
 * <p> When the player draws a card named "Collect from the Bank", the player who drew the card will receive an amount generated in the ActionDeck.java </p>
 *
 * <p> When the player draws a card named "Pay the Bank", the player who drew the card will pay an amount generated in the ActionDeck.java </p>
 *
 * <p> When the player draws a card named "Pay the Player", it checks first whether it targets all players or a single player. Then, the player who drew the card will pay an amount generated in the ActionDeck.java * the number of players aside from the one who drew, or pay one player. </p>
 *
 * <p> When the player draws a card named "Collect from Player", it checks first whether it targets all players or a single player. Then, the player who drew the card will collect an amount generated in the ActionDeck.java * the number of players aside from the one who drew, or collect from one player. </p>
 */

public class ActionCard
{
    private String name;
    private String description;
    private boolean payPlayer;
    private boolean payAll;
    private int payAmt;

    /** instantiates an ActionCard object with its name, description, and action towards the player
     *
     * @param name name of the ActionCard
     * @param description description of the ActionCard
     * @param payPlayer condition for target of the picker (player or bank)
     * @param payAll condition for target all players or one player
     * @param payAmt amount deducted/added from the player who picked the card
     */
    public ActionCard (String name, String description, boolean payPlayer, boolean payAll, int payAmt)
    {
        this.name = name;
        this.description = description;
        this.payPlayer = payPlayer;
        this.payAll = payAll;
        this.payAmt = payAmt;
    }

    /** returns the name of ActionCard
     *
     * @return name of ActionCard
     */
    public String getName ()
    {
        return name;
    }

    /** returns the description of ActionCard
     *
     * @return description of ActionCard
     */
    public String getDescription ()
    {
        return description;
    }

    /** returns boolean of payPlayer; true targets the player/s, false targets the bank
     *
     * @return boolean of payPlayer
     */
    public boolean checkPayPlayer ()
    {
        return payPlayer;
    }

    /** returns boolean of payAll; true targets all players, false targets one player
     *
     * @return boolean of payAll
     */
    public boolean checkPayAll ()
    {
        return payAll;
    }

    /** returns the amount of the ActionCard
     *
     * @return amount of ActionCard
     */
    public int getPayAmt ()
    {
        return payAmt;
    }

    /** deducts the current player's cash to pay the bank
     *
     * @param current player who drew the card
     */
    public void payBank (Player current)
    {
        current.updateCash(payAmt);
    }

    /** deducts the current player's cash to pay a target player
     *
     * @param current player who drew the card
     * @param target player who receives the payment
     */
    public void payPlayer (Player current, Player target)
    {
        target.updateCash(-payAmt);
        current.updateCash(payAmt);
    }

    /** deducts the current player's cash to pay all collectAllPlayers
     *
     * @param players ArrayList of players in the game
     * @param playerIndex index of the player who drew the card
     */
    public void payAllPlayers (ArrayList<Player> players, int playerIndex)
    {
        int i;

        for (i = 0; i < players.size(); i++) {
            if (i != playerIndex && !players.get(i).isRetired()) {
                players.get(i).updateCash(-payAmt);
                players.get(playerIndex).updateCash(payAmt * (players.size() - 1));
            }
        }
    }

    /** adds the current player's cash collected from the bank
     *
     * @param current player who drew the card
     */
    public void collectBank (Player current)
    {
        current.updateCash(payAmt);
    }

    /** adds the current player's cash collected from a target player
     *
     * @param current player who drew the card
     * @param target player who pays current
     */
    public void collectPlayer (Player current, Player target)
    {
        current.updateCash(payAmt);
        target.updateCash(-payAmt);
    }

    /** adds the current player's cash collected from all players
     *
     * @param players ArrayList of players in the game
     * @param playerIndex index of the player who drew the card
     */
    public void collectAllPlayers (ArrayList<Player> players, int playerIndex)
    {
        int i;

        for (i = 0; i < players.size(); i++) {
            if (i != playerIndex && !players.get(i).isRetired()) {
                players.get(i).updateCash(-payAmt);
                players.get(playerIndex).updateCash(payAmt);
            }
        }
    }
}