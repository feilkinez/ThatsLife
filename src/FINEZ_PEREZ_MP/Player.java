package FINEZ_PEREZ_MP;

import java.lang.*;

/** Player defines each individual player playing the game
 *
 * <p> Each player is created with 200000 cash. </p>
 *
 * <p> The player's attributes will be used for displaying their details as the game progresses. </p>
 *
 * <p> Every time a player lands on a specific space with a specific action, its attributes are updated accordingly. </p>
 */
public class Player
{
    private int cash;
    private int children;
    private int loan;

    private CareerCard career;
    private SalaryCard salary;
    private HouseCard house;

    private boolean degree;
    private boolean married;
    private boolean retired;

    private int salaryAmt;
    private int taxDue;
    private int payRaiseCtr;

    private int move;
    private int playerPos;

    /** instantiates a Player object with 200000 cash, a default movement by one (1), and position in the board at 0, indicating that the player is in the starting space
     *
     */
    public Player ()
    {
        cash = 200000;
        move = 1;
        playerPos = 0;
    }

    /** returns the cash of this player
     *
     * @return cash of player
     */
    public int getCash ()
    {
        return cash;
    }

    /** returns the number of children of this player
     *
     * @return number of children of player
     */
    public int getChild ()
    {
        return children;
    }

    /** returns the loan of this player
     *
     * @return loan of player
     */
    public int getLoan ()
    {
        return loan;
    }

    /** returns the salary amount of this player
     *
     * @return salary of player
     */
    public int getSalary ()
    {
        return salaryAmt;
    }

    /** returns the tax due amount of this player
     *
     * @return tax due of player
     */
    public int getTaxDue ()
    {
        return taxDue;
    }

    /** returns the player's position in the board
     *
     * @return tax due of player
     */
    public int getPosition ()
    {
        return playerPos;
    }

    /** returns this player's career name
     *
     * @return career of player
     */
    public String getCareer ()
    {
        return career.getCareer();
    }

    /** returns the player's house name
     *
     * @return house of player
     */
    public String getHouse ()
    {
        return house.getName();
    }

    /** returns the player's move count
     *
     * @return move count of player
     */
    public int getMove ()
    {
        return move;
    }

    /** checks if the player has a house
     *
     * @return true if player has a house, otherwise false
     */
    public boolean hasHouse ()
    {
        if (house == null)
            return false;

        return true;
    }

    /** checks if the player has a career
     *
     * @return true if player has a career, otherwise false
     */
    public boolean hasCareer ()
    {
        if (career == null)
            return false;

        return true;
    }

    /** checks if the player has a degree
     *
     * @return true if player has a degree, otherwise false
     */
    public boolean hasDegree ()
    {
        return degree;
    }

    /** checks if the player is married
     *
     * @return true if player is married, otherwise false
     */
    public boolean isMarried ()
    {
        return married;
    }

    /** checks if the player is retired
     *
     * @return true if player is retired, otherwise false
     */
    public boolean isRetired ()
    {
        return retired;
    }

    /** updates the cash of the player
     *
     * @param amt amount to be added to this player's cash
     */
    public void updateCash (int amt)
    {
        cash += amt;
    }

    /** updates the move count of the player (occurs in junctions)
     *
     * @param move move count to be updated
     */
    public void updateMove (int move)
    {
        this.move = move;
    }

    /** player receives a loan when player lacks cash
     *
     */
    public void addLoan ()
    {
        cash += 20000;
        loan += 25000;
    }

    /** player pays off their loan
     *
     * @param amt amount to be deducted from their cash and loan
     */
    public void payLoan (int amt)
    {
        cash -= amt;
        loan -= amt;
    }

    /** Player receives a pay raise by increasing their salary amount and tax due amount. The player is limited by their maximum pay raise counter. Player receives new salary.
     *
     */
    public void payRaise ()
    {
        if (payRaiseCtr != career.getPayRaise()) {
            salaryAmt += salary.getRaise();
            payRaiseCtr++;
            taxDue += 2000;
        }
        updateCash (salaryAmt);
    }

    /** method resets player's pay raise counter when they choose a new career
     *
     */
    public void resetPayRaise ()
    {
        payRaiseCtr = 0;
    }

    /** player moves by amount indicated in move
     *
     */
    public void movePlayer ()
    {
        playerPos += move;
    }

    /** player receives degree, updates degree boolean of player
     *
     */
    public void earnDegree ()
    {
        degree = true;
    }

    /** player gets married, updates married boolean of player
     *
     */
    public void marry ()
    {
        married = true;
    }

    /** player becomes retired, updates retired boolean of player, player sells all their belongings and pays off their remaining loans
     *
     * @param pay payment player receives based on order of retirement
     */
    public void retire (int pay)
    {
        retired = true;
        cash += pay;
        cash += children * 10000;
        cash += house.getEndPrice();
        cash -= loan;

        Main.gameBoard.retiredHouse = house;

        Main.gameBoard.houseDeck.returnCard(house);
        Main.gameBoard.careerDeck.returnCard(career);
        Main.gameBoard.salaryDeck.returnCard(salary);

        house = null;
        career = null;
        salary = null;
        salaryAmt = 0;
        taxDue = 0;
        loan = 0;
    }

    /** player draws a salary card, player is assigned with the details of SalaryCard
     *
     * @param sc SalaryCard received by player
     */
    public void drawSalaryCard (SalaryCard sc)
    {
        salary = sc;
        salaryAmt = sc.getSalary();
        taxDue = sc.getTaxDue();
    }

    /** player draws a career card, player is assigned with the details of CareerCard
     *
     * @param cc CareerCard received by player
     */
    public void drawCareerCard (CareerCard cc)
    {
        career = cc;
    }

    /** player draws a house card, player is assigned with the details of HouseCard
     *
     * @param hc HouseCard received by player
     */
    public void buyHouseCard (HouseCard hc)
    {
        house = hc;
        cash -= house.getBuyPrice();
    }

    /** player returns their current salary card
     *
     * @return player's current salary card to salary deck
     */
    public SalaryCard returnSalaryCard ()
    {
        SalaryCard temp = salary;
        salary = null;
        salaryAmt = 0;
        taxDue = 0;

        return temp;
    }

    /** player returns their current career card
     *
     * @return player's current career card to career deck
     */
    public CareerCard returnCareerCard ()
    {
        CareerCard temp = career;
        career = null;

        return temp;
    }

    /** player returns their current house card
     *
     * @return player's current house card to career deck
     */
    public HouseCard sellHouseCard ()
    {
        cash += house.getSellPrice();
        HouseCard temp = house;
        house = null;

        return temp;
    }

    /** player adds their children by one (1)
     *
     */
    public void addChild ()
    {
        children += 1;
    }
}