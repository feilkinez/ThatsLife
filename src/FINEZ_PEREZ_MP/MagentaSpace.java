package FINEZ_PEREZ_MP;

import java.lang.*;

/** MagentaSpace defines the magenta spaces. This extends the abstract class Space
 *
 * <p> When a player lands on this space, depending on its description, a specific action is performed on the player. </p>
 */

public class MagentaSpace extends Space
{
    /** instantitates the magenta space with a description based on its position in the board
     *
     * @param desc description of magenta space
     */
    public MagentaSpace (String desc)
    {
        super (desc);
    }

    /** method gives the player passed a degree, in effect graduating the player
     *
     * @param player player who landed on the Graduation space
     */
    public static void graduation (Player player)
    {
        player.earnDegree ();
    }

    /** method allows the player to buy a house that they chose
     *
     * @param player player who landed on the Buy a House space
     * @param hc house chosen by the player
     */
    public static void buyAHouse (Player player, HouseCard hc)
    {
        player.buyHouseCard(hc);
    }

    /** Method marries the player, in effect rewarding the player if the player is previously single. If the player rolls an even number, the player receives 10000 cash from everyone. If the player rolls an odd number, the player receives 5000 cash from everyone.
     *
     * @param rand multiplier for the reward of the player
     */
    public static void getMarried (int rand)
    {
        int i;

        Main.gameBoard.players.get(Main.gameBoard.cur).marry();

        if (rand % 2 == 0) { // even
            for (i = 0; i < Main.gameBoard.numPlayer; i++)
                if (i != Main.gameBoard.cur && !Main.gameBoard.players.get(i).isRetired()) {
                    Main.gameBoard.players.get(i).updateCash(-10000);
                    Main.gameBoard.players.get(Main.gameBoard.cur).updateCash(10000);
                }
        }

        else { // odd
            for (i = 0; i < Main.gameBoard.numPlayer; i++)
                if (i != Main.gameBoard.cur && !Main.gameBoard.players.get(i).isRetired()) {
                    Main.gameBoard.players.get(i).updateCash(-5000);
                    Main.gameBoard.players.get(Main.gameBoard.cur).updateCash(5000);
                }
        }
    }

    /** Method adds a child for the player who lands on this space. Each baby gives the player 5000 cash, given that the player must be married.
     *
     */
    public static void haveBaby ()
    {
        int i;

        Main.gameBoard.players.get(Main.gameBoard.cur).addChild();

        for (i = 0; i < Main.gameBoard.numPlayer; i++) { // other players pay 5000 to current player
            if (i != Main.gameBoard.cur && !Main.gameBoard.players.get(i).isRetired()) {
                Main.gameBoard.players.get(i).updateCash(-5000);
                Main.gameBoard.players.get(Main.gameBoard.cur).updateCash(5000);
            }
        }
    }
}