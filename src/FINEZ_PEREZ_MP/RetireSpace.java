package FINEZ_PEREZ_MP;

/** RetireSpace defines the retired space. This extends the abstract class Space
 *
 * <p> When a player lands on this space, the player is finished with the game and is rewarded with cash depending on what order the player finishes. </p>
 *
 * <p> When a player finishes first, the player receives 100000 in cash. </p>
 *
 * <p> When a player finishes second, the player receives 50000 in cash. </p>
 *
 * <p> When a player finishes third, the player receives 20000 in cash. </p>
 *
 * <p> After the players are rewarded, this calls the specific player's method called retire, where the player sells all their belongings to be used in determining the winner. </p>
 */

public class RetireSpace extends Space
{
    private static int numFinished;

    /** instantiates the retired space with description "Retirement"
     *
     */
    public RetireSpace ()
    {
        super ("Retirement");
        numFinished = 0;
    }

    /** returns the pay that a player receives depending on their order
     *
     * @param player player who landed on this space
     * @return amount received by the player
     */
    public static int retire (Player player)
    {
        int[] pay = {100000, 50000, 20000};
        player.retire(pay[numFinished]);
        numFinished++;

        return pay[numFinished-1];
    }
}