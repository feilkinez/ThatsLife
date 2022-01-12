package FINEZ_PEREZ_MP;

import java.lang.*;

/** GreenSpace defines the green spaces. This extends the abstract class Space
 *
 * <p> There are two different kinds of green spaces in the board, one is called Pay Day and another is called Pay Raise. </p>
 *
 * <p> When a player lands on Pay Day, the player receives the salary they received upon choosing a career, and is added to their cash. </p>
 *
 * <p> When a player lands on Pay Raise, the player's salary and tax due is increased. However, given a career's maximum number of pay raises, the player is limited to increasing their salary and tax due. </p>
 */

public class GreenSpace extends Space
{
    private boolean payDay;

    /** instantitates the green space with a boolean payDay to determine whether the green space is Pay Day or Pay Raise and its description
     *
     * @param payDay determines green space as Pay Day (true) or Pay Raise (false)
     * @param desc description of green space
     */
    public GreenSpace (boolean payDay, String desc)
    {
        super (desc);

        this.payDay = payDay;
    }

    /** returns a boolean that determines whether the space is Pay Day or Pay Raise
     *
     * @return pay day boolean
     */
    public boolean getPayDay ()
    {
        return payDay;
    }

    /** method adds the salary received by the player that landed on this space to their cash
     *
     * @param player player who landed on this space
     */
    public void payDay (Player player)
    {
        player.updateCash(player.getSalary ());
    }

    /** method increases the player's salary and tax due
     *
     * @param player player who landed on this space
     */
    public void payRaise (Player player)
    {
        player.payRaise ();
    }
}