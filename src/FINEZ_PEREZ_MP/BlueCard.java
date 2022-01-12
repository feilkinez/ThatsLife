package FINEZ_PEREZ_MP;

import java.lang.*;

/** BlueCard defines the blue card object to be drawn by a player
 *
 * <p> A blue card is drawn by the player when they land on a blue space in the board. </p>
 *
 * <p> When the card is drawn, it checks for the careers of each player to know its corresponding action on the players. </p>
 *
 * <p> Each blue card has a corresponding action depending on which career it is associated with. </p>
 *
 * <p> If the blue card drawn by a player has no career match in all players, the player who drew the card will pay the bank. </p>
 *
 * <p> If the blue card drawn by a player matches their own career, the bank pays the player. </p>
 *
 * <p> If the blue card drawn by a player matches a career of another player, the player who drew the card will pay the player who matched that career. </p>
 */

public class BlueCard
{
    private String name;
    private CareerCard careerMatch;
    private final int PAY_AMT;

    /** instantiates an BlueCard object with its name, associated career, and amount needed for transaction
     *
     * @param name name of BlueCard
     * @param career CareerCard associated with the name of BlueCard
     * @param amt amount needed to be paid/collected
     */
    public BlueCard (String name, CareerCard career, int amt)
    {
        this.name = name;
        careerMatch = career;
        PAY_AMT = amt;
    }

    /** returns the name of BlueCard
     *
     * @return name of BlueCard
     */
    public String getName ()
    {
        return name;
    }

    /** returns the name of career associated with BlueCard
     *
     * @return String name of CareerCard
     */
    public String getCareerMatch ()
    {
        return careerMatch.getCareer();
    }

    /** returns a boolean for checking if the parameter career matches the career name associated with BlueCard
     *
     * @param career career of the player who drew the card
     * @return boolean if the career matches with BlueCard
     */
    public boolean doesCareerMatch (String career)
    {
        return careerMatch.getCareer().equals(career);
    }

    /** method is performed if the blue card drawn does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     */
    public void lawsuit (Player from)
    {
        from.updateCash (-PAY_AMT);
    }

    /** method is performed if the blue card drawn by the current player matches another player's career
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     */
    public void lawsuit (Player from, Player to)
    {
        lawsuit (from);
        to.updateCash (PAY_AMT);
    }

    /** method is performed if the blue card drawn does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     */
    public void salaryTaxDue (Player from)
    {
        from.updateCash (-from.getTaxDue ());
    }

    /** method is performed if the blue card drawn by the current player matches another player's career
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     */
    public void salaryTaxDue (Player from, Player to)
    {
        salaryTaxDue (from);
        to.updateCash (from.getTaxDue ());
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game, rand is also passed which acts as a multiplier for the payment
     *
     * @param from player who drew the card
     * @param rand random generated number for multiplying PAY_AMT
     */
    public void tipTheServer (Player from, int rand)
    {
        from.updateCash (-(rand * PAY_AMT));
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game, rand is also passed which acts as a multiplier for the payment
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     * @param rand random generated number for multiplying PAY_AMT
     */
    public void tipTheServer (Player from, Player to, int rand)
    {
        tipTheServer (from, rand);
        to.updateCash (rand * PAY_AMT);
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     */
    public void skiAccident (Player from)
    {
        from.updateCash (-PAY_AMT);
    }

    /** method is performed if the blue card drawn by the current player matches another player's career
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     */
    public void skiAccident (Player from, Player to)
    {
        skiAccident (from);
        to.updateCash (PAY_AMT);
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game, rand is also passed which acts as a multiplier for the payment
     *
     * @param from player who drew the card
     * @param rand random generated number for multiplying PAY_AMT
     */
    public void computerRepair (Player from, int rand)
    {
        from.updateCash (-(PAY_AMT + (rand % 2) * PAY_AMT));
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game, rand is also passed which acts as a multiplier for the payment
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     * @param rand random generated number for multiplying PAY_AMT
     */
    public void computerRepair (Player from, Player to, int rand)
    {
        computerRepair (from, rand);
        to.updateCash (PAY_AMT + (rand % 2) * PAY_AMT);
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     * @param numPlayer number of players in the game
     */
    public void worldCup (Player from, int numPlayer)
    {
        from.updateCash (-(PAY_AMT * numPlayer));
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     * @param numPlayer number of players in the game
     */
    public void worldCup (Player from, Player to, int numPlayer)
    {
        worldCup (from, numPlayer);
        to.updateCash (PAY_AMT * numPlayer);
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     */
    public void f1Race (Player from)
    {
        from.updateCash (-(from.getSalary() / PAY_AMT));
    }

    /** method is performed if the blue card does not match any of the careers of the players in the game
     *
     * @param from player who drew the card
     * @param to player that matches the career associated with BlueCard
     */
    public void f1Race (Player from, Player to)
    {
        f1Race (from);
        to.updateCash (from.getSalary() / PAY_AMT);
    }
}