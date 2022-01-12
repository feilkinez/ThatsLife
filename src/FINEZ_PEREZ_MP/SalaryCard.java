package FINEZ_PEREZ_MP;

/** SalaryCard defines the blue card object to be drawn by a player
 *
 * <p> SalaryCard is drawn along with a CareerCard. </p>
 *
 * <p> SalaryCard includes its salary amount, tax due amount, and pay raise amount. </p>
 *
 * <p> Attributes in this class are assigned in SalaryDeck. </p>
 */

public class SalaryCard
{
    private final int SALARY;
    private final int TAXDUE;
    private final int P_RAISE;

    /** instantiates a SalaryCard object with SALARY and TAXDUE
     *
     * @param SALARY amount of salary
     * @param TAXDUE amount of tax due
     */
    public SalaryCard (int SALARY, int TAXDUE)
    {
        this.SALARY = SALARY;
        this.TAXDUE = TAXDUE;
        P_RAISE = SALARY / 10;
    }

    /** returns the salary amount of this card
     *
     * @return SALARY amount of salary
     */
    public int getSalary ()
    {
        return SALARY;
    }

    /** returns the tax due amount of this card
     *
     * @return TAXDUE amount of tax due
     */
    public int getTaxDue ()
    {
        return TAXDUE;
    }

    /** returns the pay raise amount of this card
     *
     * @return P_RAISE amount of pay raise
     */
    public int getRaise ()
    {
        return P_RAISE;
    }
}