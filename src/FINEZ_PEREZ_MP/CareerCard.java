package FINEZ_PEREZ_MP;

import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

/** CareerCard defines the career card objects available for the players
 *
 * <p> Each card has a career, degreeRequired, payRaise, LOWRAISE, and HIGHRAISE that determines what the job of the player is. </p>
 *
 * <p> An career card is created with 4 parameters which cannot be changed after its creation. These parameters will determine what the job is, if it requires a degree, and how many pay raises it is allowed to get. </p>
 *
 * <p> The player that owns the card has the job that card indicates. </p>
 *
 * <p> When the player chooses to replace their job, their old CareerCard are returned to the deck. </p>
 */

public class CareerCard
{
    private String career;
    private boolean degreeRequired;
    private int payRaise;

    private final int LOWRAISE;
    private final int HIGHRAISE;

    /** instantiates a CareerCard object with its career, degreeRequired, LOWRAISE, and HIGHRAISE
     *
     * @param career name of the job assigned to the CareerCard
     * @param degreeRequired determines if the job requires a college degree
     * @param LOWRAISE lowest possible value of payRaise
     * @param HIGHRAISE highest possible value of payRaise
     */
    public CareerCard (String career, boolean degreeRequired, int LOWRAISE, int HIGHRAISE)
    {
        this.career = career;
        this.degreeRequired = degreeRequired;
        this.LOWRAISE = LOWRAISE;
        this.HIGHRAISE = HIGHRAISE;

        payRaise = ThreadLocalRandom.current().nextInt(LOWRAISE, HIGHRAISE+1);
    }

    /** returns the job assign to CareerCard
     *
     * @return career of CareerCard
     */
    public String getCareer ()
    {
        return career;
    }

    /** returns true if the job requires a degree
     *
     * @return degreeRequired of CareerCard
     */
    public boolean getRequired ()
    {
        return degreeRequired;
    }

    /** returns the maximum number of pay raises this CareerCard are allowed to receive
     *
     * @return payRaise of CareerCard
     */
    public int getPayRaise ()
    {
        return payRaise;
    }
}