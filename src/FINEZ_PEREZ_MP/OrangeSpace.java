package FINEZ_PEREZ_MP;

import java.util.*;

/** OrangeSpace defines the orange space object. This extends the abstract class Space
 *
 * <p> When a player lands on this space, the player draws an action card. The player does the action specified in the card </p>
 *
 * <p> The space executes the methods required based on the action card drawn. The player's details are updated in the methods, </p>
 *
 * <p> The space also executes the methods that require a player to pick another player. </p>
 */
public class OrangeSpace extends Space
{
    /** instantiates a OrangeSpace object with a description
     *
     */
    public OrangeSpace ()
    {
        super ("Draw an Action Card");
    }

    /** draws an ActionCard and executes the methods required based on the card drawn. Returns an ActionCard if the card requires the current player to pick another player.
     *
     * @param playerIndex index of the current player
     * @param players ArrayList of the players in the game
     * @param deck deck of action cards where a card will be drawn
     * @return ActionCard action card that requires another player to be picked. Null if action card only requires the current player.
     */
    public static ActionCard drawCard (int playerIndex, ArrayList<Player> players, ActionDeck deck)
    {
        ActionCard card = deck.drawTopCard();
        Main.gameBoard.aDispCard = card;

        if (card.getPayAmt() > 0) { // positive
            if (card.checkPayPlayer ()) { // Collect from Player
                if (card.checkPayAll()) {
                    card.collectAllPlayers (players, playerIndex);
                }
                else {
                    return card;
                }
            }
            else { // Collect from Bank
                card.collectBank (players.get(playerIndex));
            }
        }

        else { // negative
            if (card.checkPayPlayer()) { // Pay player
                if (card.checkPayAll()) {
                    card.payAllPlayers (players, playerIndex);
                }
                else {
                    return card;
                }
            }
            else { // Pay the Bank
                card.payBank (players.get(playerIndex));
            }
        }

        return null;
    }

    /** executes the methods of the cards that require another player to be picked.
     *
     * @param curIndex index of the current player
     * @param tarIndex index of the target player
     * @param players ArrayList of the players in the game
     * @param card action card drawn
     */
    public static void targetMethods (int curIndex, int tarIndex, ArrayList<Player> players, ActionCard card)
    {
        if (card.getPayAmt() > 0 && !players.get(tarIndex).isRetired())
            card.collectPlayer(players.get(curIndex), players.get(tarIndex));

        else if (card.getPayAmt() < 0 && !players.get(tarIndex).isRetired())
            card.payPlayer(players.get(curIndex), players.get(tarIndex));
    }
}