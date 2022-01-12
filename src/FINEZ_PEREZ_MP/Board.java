package FINEZ_PEREZ_MP;

import java.util.*;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

/** Board defines the board object that that holds all the decks and players
 *
 * <p> All decks are initiated here.. </p>
 *
 * <p> Action cards and blue cards that require further action are stored here. </p>
 *
 * <p> All the spaces initiated here in order based on the UML diagram. </p>
 *
 * <p> Action cards and blue cards that require further action are stored here. </p>
 *
 * <p> Cards to be displayed are also stored here. </p>
 *
 * <p> The board also keeps track of whose turn it is. </p>
 *
 * <p> Player rankings are stored here. </p>
 */
public class Board
{
    public ArrayList<Player> players;
    public ArrayList<Space> spaces;

    public BlueDeck blueDeck;
    public ActionDeck actionDeck;
    public SalaryDeck salaryDeck;
    public CareerDeck careerDeck;
    public HouseDeck houseDeck;

    public ActionCard aCard;
    public ActionCard aDispCard;

    public BlueCard bCard;
    public BlueCard bDispCard;

    public CareerCard newJob;
    public SalaryCard newSalary;

    public CareerCard newJob2;
    public SalaryCard newSalary2;

    public HouseCard retiredHouse;

    public int numPlayer;
    public int turnCtr;

    public int cur;
    public int pos;

    public ArrayList<String> finishOrder;

    /** instantiates a Board object with an action deck, blue deck, salary deck, career deck, house deck, and a predetermined set of spaces. Players are initialized based on the number of players selected.
     *
     * @param num number of players that will play the game
     */
    public Board (int num)
    {
        int i;

        numPlayer = num;
        turnCtr = 0;

        cur = 0;
        pos = 0;

        blueDeck = new BlueDeck ();
        actionDeck = new ActionDeck ();
        salaryDeck = new SalaryDeck ();
        careerDeck = new CareerDeck (true);
        houseDeck = new HouseDeck ();

        spaces = new ArrayList<>();
        initializeSpaces ();

        players = new ArrayList<>();
        for (i = 0; i < numPlayer; i++)
            players.add(new Player());

        finishOrder = new ArrayList<>();
    }

    /** creates colored spaces in order (based on the UML diagram)
     *
     */
    public void initializeSpaces ()
    {
        int i;

        spaces.add(new MagentaSpace("START"));
        for (i = 1; i < 72; i++) {
            // magenta spaces
            if (i == 11)
                spaces.add(new MagentaSpace("Graduation"));
            else if (i == 13)
                spaces.add(new MagentaSpace("College Career Choice"));
            else if (i == 14 || i == 44)
                spaces.add(new MagentaSpace("Get Married"));
            else if (i == 22 || i == 42)
                spaces.add(new MagentaSpace("Which Path?"));
            else if (i == 24)
                spaces.add(new MagentaSpace("Job Search"));
            else if (i == 38 || i == 48)
                spaces.add(new MagentaSpace("Buy a House"));
            else if (i == 58)
                spaces.add(new MagentaSpace("Have Twins"));
            else if (i == 64)
                spaces.add(new MagentaSpace("Have Baby"));

                // green spaces: pay day
            else if (i == 17 || i == 25 || i == 30 || i == 41 || i == 55 || i == 59 || i == 66 || i == 70)
                spaces.add(new GreenSpace(true, "Pay Day"));

                // green spaces: pay raise
            else if (i == 20 || i == 33 || i == 34 || i == 49)
                spaces.add(new GreenSpace(false, "Pay Raise"));

                // blue spaces
            else if (i == 36 || i == 54)
                spaces.add(new BlueSpace());

                // orange spaces
            else
                spaces.add(new OrangeSpace());
        }
        spaces.add(new RetireSpace());
    }

    /** generates a random number
     *
     * @return int random generated number
     */
    public static int rollNumber ()
    {
        return ThreadLocalRandom.current().nextInt(1, 11);
    }

    /** checks the type of the space on indicated index. Returns an integer: 1-Orange, 2-Blue, 3-Green, 4-Magenta, 5-Retire
     *
     * @param spaceNum index of specified space
     * @return int depending on the type of space
     */
    public int checkSpace (int spaceNum)
    {
        if (spaces.get(spaceNum) instanceof OrangeSpace)
            return 1;
        else if (spaces.get(spaceNum) instanceof BlueSpace)
            return 2;
        else if (spaces.get(spaceNum) instanceof GreenSpace)
            return 3;
        else if (spaces.get(spaceNum) instanceof MagentaSpace)
            return 4;
        return 5;
    }

    /** gives the player a loan to pay off college to start College Path
     *
     */
    public void collegePath ()
    {
        players.get(cur).addLoan();
        players.get(cur).addLoan();
        players.get(cur).updateCash(-40000);
    }

    /** gives the player a job that doesn't require a degree and a salary card. Updates move to 2.
     *
     */
    public void careerPath ()
    {
        CareerCard cc = careerDeck.drawTopCard();
        SalaryCard sc = salaryDeck.drawTopCard();
        while (cc.getRequired()) {
            careerDeck.returnCard(cc);
            cc = careerDeck.drawTopCard();
        }

        players.get(cur).updateMove(2);
        players.get(cur).drawCareerCard(cc);
        players.get(cur).drawSalaryCard(sc);
    }

    /** gives the player a new jobdegree and a new salary card. Updates move to 2.
     *
     */
    public void changeCareerPath ()
    {
        players.get(cur).updateMove(2);
        careerDeck.returnCard(players.get(cur).returnCareerCard());
        salaryDeck.returnCard(players.get(cur).returnSalaryCard());

        players.get(cur).drawCareerCard(newJob);
        players.get(cur).drawSalaryCard(newSalary);

        players.get(cur).resetPayRaise();

        newJob = null;
        newSalary = null;
    }

    /** gives the player a job and a salary card. Updates move to 2
     *
     * @param cc career card chosen
     * @param sc salary card chosen
     */
    public void collegeCareer (CareerCard cc, SalaryCard sc)
    {
        players.get(cur).drawCareerCard(cc);
        players.get(cur).drawSalaryCard(sc);

        newJob = null;
        newSalary = null;
        newJob2 = null;
        newSalary2 = null;
    }

    /** Checks if the game is finished. If finished, determines the player rankings.
     *
     * @return true if all players are retired, false otherwise
     */
    public boolean isFinished ()
    {
        int i;

        for (i = 0; i < numPlayer; i++)  // checks all players
            if (!players.get(i).isRetired())  // if a player is not retired
                return false;

        int highPlayer = 0;
        int midPlayer = 0;

        String num;

        for (i = 1; i < numPlayer; i++)  // finds the richest player
            if (players.get(highPlayer).getCash() < players.get(i).getCash())
                highPlayer = i;

        for (i = 1; i < numPlayer; i++) // finds the second richest player
            if ((players.get(midPlayer).getCash() < players.get(i).getCash() && i != highPlayer) || midPlayer == highPlayer)
                midPlayer = i;


        num = Integer.toString(highPlayer + 1);
        finishOrder.add(num);

        num = Integer.toString(midPlayer + 1);
        finishOrder.add(num);

        if (numPlayer == 3) {  // if 3 player game
            int lowPlayer = 0;
            while (lowPlayer == highPlayer || lowPlayer == midPlayer)  // finds the third richest player
                lowPlayer++;

            num = Integer.toString(lowPlayer + 1);
            finishOrder.add(num);
        }

        return true;
    }
}