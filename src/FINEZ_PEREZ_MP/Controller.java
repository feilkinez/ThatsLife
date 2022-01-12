package FINEZ_PEREZ_MP;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private Label rollLabel;

    @FXML private Button rollButton;

    @FXML private Button startTheGame;

    @FXML private Button payLoanButton;

    @FXML private Label playerTurnDisplay;

    @FXML private Label landedSpaceLabel;
    @FXML private Label spaceDescLabel;
    @FXML private Label eventLabel1;
    @FXML private Label eventLabel2;
    @FXML private Label eventLabel3;
    @FXML private Label eventLabel4;

    @FXML private Circle Player1_Peg;
    @FXML private Pane Player1_Pane;
    @FXML private Label Player1_Job;
    @FXML private Label Player1_Cash;
    @FXML private Label Player1_Loan;
    @FXML private Label Player1_Salary;
    @FXML private Label Player1_TaxDue;
    @FXML private Label Player1_Married;
    @FXML private Label Player1_House;
    @FXML private Label Player1_Children;

    @FXML private Circle Player2_Peg;
    @FXML private Pane Player2_Pane;
    @FXML private Label Player2_Job;
    @FXML private Label Player2_Cash;
    @FXML private Label Player2_Loan;
    @FXML private Label Player2_Salary;
    @FXML private Label Player2_TaxDue;
    @FXML private Label Player2_Married;
    @FXML private Label Player2_Children;
    @FXML private Label Player2_House;

    @FXML private Circle Player3_Peg;
    @FXML private Pane Player3_Pane;
    @FXML private Label Player3_Job;
    @FXML private Label Player3_Cash;
    @FXML private Label Player3_Loan;
    @FXML private Label Player3_Salary;
    @FXML private Label Player3_TaxDue;
    @FXML private Label Player3_Married;
    @FXML private Label Player3_House;
    @FXML private Label Player3_Children;

    @FXML private AnchorPane junctionPane;
    @FXML private Button junctionOption1;
    @FXML private Button junctionOption2;

    @FXML private Button pickPlayerButton;

    @FXML private AnchorPane pickPlayerPane;
    @FXML private Button playerOption1;
    @FXML private Button playerOption2;
    @FXML private Button playerOption3;

    @FXML private Button blueButton;

    @FXML private AnchorPane changeCareerPane;
    @FXML private Label oldJobLabel;
    @FXML private Label oldSalaryLabel;
    @FXML private Label oldTDLabel;
    @FXML private Label newJobLabel;
    @FXML private Label newSalaryLabel;
    @FXML private Label newTDLabel;

    @FXML private Button marryRoll;

    @FXML private AnchorPane careerChoicePane;
    @FXML private Label firstJobLabel;
    @FXML private Label firstSalaryLabel;
    @FXML private Label firstTDLabel;
    @FXML private Label secondJobLabel;
    @FXML private Label secondSalaryLabel;
    @FXML private Label secondTDLabel;

    @FXML private AnchorPane buyHousePane;
    @FXML private Button apartButton;
    @FXML private Button condoButton;
    @FXML private Button cabinButton;
    @FXML private Button cottageButton;
    @FXML private Button bungalowButton;
    @FXML private Button mansionButton;

    @FXML private AnchorPane changeHousePane;

    @FXML private Button resultButton;

    @FXML private Text gameOverText;
    @FXML private Text congratsText;
    @FXML private Text firstPlaceText;
    @FXML private Text secondPlaceText;
    @FXML private Text thirdPlaceText;
    @FXML private Label firstPlaceLabel;
    @FXML private Label secondPlaceLabel;
    @FXML private Label thirdPlaceLabel;
    @FXML private Button endGameExit;
    @FXML private Button paneDisplayButton;

    // start game with 2 players is pressed
    public void startTwo () throws IOException
    {
        Main.gameBoard = new Board(2);

        Stage primaryStage = Main.stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
        Parent boardRoot = loader.load();
        Scene boardScene = new Scene (boardRoot);

        primaryStage.setScene(boardScene);
        primaryStage.show();

        System.out.println("Started two player game");
    }

    // start game with 3 players is pressed
    public void startThree () throws IOException
    {
        Main.gameBoard = new Board(3);

        Stage primaryStage = Main.stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
        Parent boardRoot = loader.load();
        Scene boardScene = new Scene (boardRoot);

        primaryStage.setScene(boardScene);
        primaryStage.show();

        System.out.println("Started three player game");
    }

    // START button is pressed
    public void startInBoard ()
    {
        // sets the start button to invisible and disabled, and sets the pick button for orange card to visible but disabled
        // roll button is enabled after pressing the start button
        startTheGame.setDisable(true);
        startTheGame.setVisible(false);
        pickPlayerButton.setVisible(true);
        rollButton.setDisable(false);
        playerTurnDisplay.setVisible(true);

        // sets the choices for the junction pane to College Path and Career Path to be chosen by each player
        junctionPane.setVisible(true);
        junctionOption1.setText("College Path");
        junctionOption2.setText("Career Path");

        // update player details
        playerDetails();

        // sets the two players to visible when either a two-player game or a three-player game is chosen
        Player1_Peg.setVisible(true);
        Player1_Pane.setVisible(true);
        Player1_Job.setText("None");

        Player2_Peg.setVisible(true);
        Player2_Pane.setVisible(true);
        Player2_Job.setText("None");

        // only show player 3 details when user chose three-player game
        if (Main.gameBoard.numPlayer == 3) {
            Player3_Peg.setVisible(true);
            Player3_Pane.setVisible(true);
            Player3_Job.setText("None");

            playerOption3.setVisible(true);
        }

        System.out.println("Game started");
    }

    // exit button in main menu is pressed
    public void exit ()
    {
        System.exit(0);
    }

    // exit button in end game screen is pressed
    public void exitTwo ()
    {
        System.exit(0);
    }

    // roll dice button is pressed
    public void rollDice ()
    {
        int rand = Board.rollNumber();  // board will generate number
        rollLabel.setText(Integer.toString(rand)); // rolled number

        // rand is for the random generated number
        // startTurn returns an int for checking the space the player landed on
        int checkReturn = startTurn(rand);

        if (checkReturn == 1) { // if orange space
            landedSpaceLabel.setText("Player " + (Main.gameBoard.cur + 1) + " landed on Orange space.");
            spaceDescLabel.setText(Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription());

            // card drawn is assigned to aCard in board
            Main.gameBoard.aCard = OrangeSpace.drawCard(Main.gameBoard.cur, Main.gameBoard.players, Main.gameBoard.actionDeck);

            // gets the name and description of action card drawn
            eventLabel1.setText(Main.gameBoard.aDispCard.getName());
            eventLabel2.setText("'" + Main.gameBoard.aDispCard.getDescription() + "'");

            String sentence;
            if (Main.gameBoard.aDispCard.checkPayAll()) { // if the card is for all players, then checks for either paying to all or collecting from all
                if (Main.gameBoard.aDispCard.getPayAmt() > 0)
                    sentence = " from everyone";
                else
                    sentence = " to everyone";
            }

            else {	// if the card targets a single player
                sentence = "";
            }

            if (Main.gameBoard.aDispCard.getPayAmt() < 0) {	// if the card requires player to pay
                eventLabel3.setText("Player " + (Main.gameBoard.cur + 1) + " pays");
                eventLabel4.setText(-(Main.gameBoard.aDispCard.getPayAmt()) + sentence);
            }
            else {	// if the card requires player to collect
                eventLabel3.setText("Player " + (Main.gameBoard.cur + 1) + " collects");
                eventLabel4.setText(Main.gameBoard.aDispCard.getPayAmt() + sentence);
            }

            if (Main.gameBoard.aCard != null) {	// activates pick button and disables roll button if action card involves picking a player
                pickPlayerButton.setDisable(false);
                rollButton.setDisable(true);
            }
        }

        else if (checkReturn == 2) {	// if blue space
            landedSpaceLabel.setText("Player " + (Main.gameBoard.cur + 1) + " landed on Blue space.");
            spaceDescLabel.setText(Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription());

            // card drawn is assigned to bCard in board
            Main.gameBoard.bCard = BlueSpace.drawCard(Main.gameBoard.cur, Main.gameBoard.players, Main.gameBoard.blueDeck);

            // gets the name of the blue card and the career associated with it
            eventLabel1.setText(Main.gameBoard.bDispCard.getName());
            eventLabel2.setText("Career match:");
            eventLabel3.setText(Main.gameBoard.bDispCard.getCareerMatch());

            int match = -1;
            int i;

            if (Main.gameBoard.bDispCard != null) {	// checks for a career match within the players that are not retired
                for (i = 0; i < Main.gameBoard.numPlayer; i++) {
                    if (Main.gameBoard.bDispCard.doesCareerMatch(Main.gameBoard.players.get(i).getCareer()) && !Main.gameBoard.players.get(i).isRetired()) {
                        match = i;
                    }
                }
            }

            if (Main.gameBoard.cur == match)	// current player matches the blue card drawn
                eventLabel4.setText("Current player collected from bank");

            else if (match == -1)	// no match found
                eventLabel4.setText("Current player paid the bank");

            else	// current player pays the player with a career that matched the blue card drawn
                eventLabel4.setText("Current player paid another player");

            if (Main.gameBoard.bCard != null) {	// activates roll button for blue and disables roll button for specific methods in blue card
                blueButton.setVisible(true);
                rollButton.setDisable(true);
            }
        }

        else if (checkReturn == 3) {	// if green space
            landedSpaceLabel.setText("Player " + (Main.gameBoard.cur + 1) + " landed on Green space.");
            spaceDescLabel.setText(Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription());

            GreenSpace green = (GreenSpace) Main.gameBoard.spaces.get(Main.gameBoard.pos);

            if (green.getPayDay()) {	// if player lands on pay day space
                green.payDay(Main.gameBoard.players.get(Main.gameBoard.cur));
                eventLabel1.setText("Player receives salary");
                eventLabel2.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getSalary()));
                eventLabel3.setText("");
                eventLabel4.setText("");
            }

            else {	// if player lands on pay raise space
                eventLabel1.setText("Old Salary:");
                eventLabel2.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getSalary()));
                eventLabel3.setText("Old Tax Due:");
                eventLabel4.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getTaxDue()));
                green.payRaise(Main.gameBoard.players.get(Main.gameBoard.cur));
            }
        }

        else if (checkReturn == 4) {  // if magenta space
            landedSpaceLabel.setText("Player " + (Main.gameBoard.cur + 1) + " landed on Magenta space.");
            spaceDescLabel.setText(Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription());

            if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Graduation")) {  // if landed on graduation
                MagentaSpace.graduation(Main.gameBoard.players.get(Main.gameBoard.cur));  // player graduates

                eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " graduated!");
                eventLabel2.setText("Congratulations!");
                eventLabel3.setText("You can now apply for");
                eventLabel4.setText("jobs requiring a degree!");
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("College Career Choice")) {  // if landed on college career choice
                // draws two cards
                Main.gameBoard.newJob = Main.gameBoard.careerDeck.drawTopCard();
                Main.gameBoard.newSalary = Main.gameBoard.salaryDeck.drawTopCard();
                Main.gameBoard.newJob2 = Main.gameBoard.careerDeck.drawTopCard();
                Main.gameBoard.newSalary2 = Main.gameBoard.salaryDeck.drawTopCard();

                careerChoicePane.setVisible(true);

                // displays two job choices for the player
                firstJobLabel.setText(Main.gameBoard.newJob.getCareer());
                firstSalaryLabel.setText(Integer.toString(Main.gameBoard.newSalary.getSalary()));
                firstTDLabel.setText(Integer.toString(Main.gameBoard.newSalary.getTaxDue()));
                secondJobLabel.setText(Main.gameBoard.newJob2.getCareer());
                secondSalaryLabel.setText(Integer.toString(Main.gameBoard.newSalary2.getSalary()));
                secondTDLabel.setText(Integer.toString(Main.gameBoard.newSalary2.getTaxDue()));
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Job Search")) {  // if landed on job search
                Main.gameBoard.newJob = Main.gameBoard.careerDeck.drawTopCard();
                Main.gameBoard.newSalary = Main.gameBoard.salaryDeck.drawTopCard();

                while (!Main.gameBoard.players.get(Main.gameBoard.cur).hasDegree() && Main.gameBoard.newJob.getRequired()) {  // returns the career card if it requires a degree and the player doesn't have one. Draws new career card
                    Main.gameBoard.careerDeck.returnCard(Main.gameBoard.newJob);
                    Main.gameBoard.newJob = Main.gameBoard.careerDeck.drawTopCard();
                }

                changeCareerPane.setVisible(true);

                // displays two choices: the player's old job, and a new one
                oldJobLabel.setText(Main.gameBoard.players.get(Main.gameBoard.cur).getCareer());
                oldSalaryLabel.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getSalary()));
                oldTDLabel.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getTaxDue()));

                newJobLabel.setText(Main.gameBoard.newJob.getCareer());
                newSalaryLabel.setText(Integer.toString(Main.gameBoard.newSalary.getSalary()));
                newTDLabel.setText(Integer.toString(Main.gameBoard.newSalary.getTaxDue()));
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Get Married")) {  // if landed on get married
                if (!Main.gameBoard.players.get(Main.gameBoard.cur).isMarried()) {  // if player is not married
                    eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " got married!");
                    eventLabel2.setText("Congratulations!");
                    eventLabel3.setText("");
                    eventLabel4.setText("Roll a number");

                    marryRoll.setVisible(true);
                }
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Buy a House")) {  // if landed on buy a house
                if (Main.gameBoard.players.get(Main.gameBoard.cur).hasHouse())  // checks if player already has a house
                    changeHousePane.setVisible(true);
                else
                    buyHousePane.setVisible(true);
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Have Twins")) {  // if landed on have twins
                if (Main.gameBoard.players.get(Main.gameBoard.cur).isMarried()) {  // checks if player is married
                    // gives player two babies
                    MagentaSpace.haveBaby();
                    MagentaSpace.haveBaby();

                    eventLabel1.setText("Player gave birth to twins!");
                    eventLabel2.setText("Congratulations!");
                    eventLabel3.setText("");
                    eventLabel4.setText("Everyone paid 10000");
                }
                else {  // if not married, nothing happens
                    eventLabel1.setText("Player is not married");
                    eventLabel2.setText("");
                    eventLabel3.setText("Nothing happens");
                    eventLabel4.setText("");
                }
                playerDetails();
            }

            else if (Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription().equals("Have Baby")) {  // if landed on have baby
                if (Main.gameBoard.players.get(Main.gameBoard.cur).isMarried()) {  // checks if player is married
                    // gives player a baby
                    MagentaSpace.haveBaby();

                    eventLabel1.setText("Player gave birth to a baby!");
                    eventLabel2.setText("Congratulations!");
                    eventLabel3.setText("");
                    eventLabel4.setText("Everyone paid 5000");
                }
                else {  // if not married, nothing happens
                    eventLabel1.setText("Player is not married");
                    eventLabel2.setText("");
                    eventLabel3.setText("Nothing happens");
                    eventLabel4.setText("");
                }
                playerDetails();
            }

            else {  // if landed on second or third junction
                if (Main.gameBoard.pos == 22) {  // if second juction
                    junctionPane.setVisible(true);
                    junctionOption1.setText("Ordinary Path");
                    junctionOption2.setText("Change Career Path");
                }
                else if (Main.gameBoard.pos == 42) {  // if third junction
                    junctionPane.setVisible(true);
                    junctionOption1.setText("Ordinary Path");
                    junctionOption2.setText("Start Family Path");
                }
            }

            Main.gameBoard.turnCtr += Main.gameBoard.numPlayer;  // makes sure player rolls again if landed on magenta space
        }

        else {  // if retire space
            landedSpaceLabel.setText("Player reached the end");
            spaceDescLabel.setText(Main.gameBoard.spaces.get(Main.gameBoard.pos).getDescription());
            int retirementMoney = RetireSpace.retire(Main.gameBoard.players.get(Main.gameBoard.cur));  // retires the current player and determines how much they received as a reward for finishing the game

            if (Main.gameBoard.retiredHouse != null) {  // allows the sold house of the retired player to be available for buying
                if (Main.gameBoard.retiredHouse.getName().equals("Apartment"))
                    apartButton.setDisable(false);
                else if (Main.gameBoard.retiredHouse.getName().equals("Condominium"))
                    condoButton.setDisable(false);
                else if (Main.gameBoard.retiredHouse.getName().equals("Cabin"))
                    cabinButton.setDisable(false);
                else if (Main.gameBoard.retiredHouse.getName().equals("Cottage"))
                    cottageButton.setDisable(false);
                else if (Main.gameBoard.retiredHouse.getName().equals("Bungalow"))
                    bungalowButton.setDisable(false);
                else
                    mansionButton.setDisable(false);
            }

            // updates the event display
            eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " has retired.");
            eventLabel2.setText("Player earns: " + retirementMoney);
            eventLabel3.setText("Player finished with");
            eventLabel4.setText(Integer.toString(Main.gameBoard.players.get(Main.gameBoard.cur).getCash()));
        }

        System.out.println("Player rolled " + rand);  // displays in the console the rolled number

        if (Main.gameBoard.isFinished()) {  // checks if the game is finished
            resultButton.setVisible(true);
            rollButton.setDisable(true);

            playerDetails();
        }

        if (Main.gameBoard.aCard == null && Main.gameBoard.bCard == null && checkReturn != 4 && !Main.gameBoard.isFinished()) {  // checks if current player is done with their turn
            Main.gameBoard.turnCtr++;
            Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;

            while (Main.gameBoard.players.get(Main.gameBoard.cur).isRetired()) {  // checks if next player is retired
                Main.gameBoard.turnCtr++;
                Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;
            }

            Main.gameBoard.pos = Main.gameBoard.players.get(Main.gameBoard.cur).getPosition();
            if (Main.gameBoard.pos == 0) {  // updates the junction options if next player is at start
                junctionOption1.setText("College Path");
                junctionOption2.setText("Career Path");
                junctionPane.setVisible(true);
            }

            playerTurnDisplay.setText("Player " + (Main.gameBoard.cur + 1) +"'s Turn");

            playerDetails();
        }

        // activates the pay loan button if current player has a loan
        if (Main.gameBoard.players.get(Main.gameBoard.cur).getLoan() > 0 &&
                Main.gameBoard.players.get(Main.gameBoard.cur).getCash() > Main.gameBoard.players.get(Main.gameBoard.cur).getLoan())
            payLoanButton.setDisable(false);

        else
            payLoanButton.setDisable(true);
    }

    // executes the start turn
    public int startTurn(int roll)
    {
        do {
            Main.gameBoard.players.get(Main.gameBoard.cur).movePlayer();  // moves player 1 jump
            roll--;  // reduces moves
            Main.gameBoard.pos = Main.gameBoard.players.get(Main.gameBoard.cur).getPosition();  // gets position of current player
            movePegs();

            // updates move of player in junctions
            if (Main.gameBoard.pos == 1 || Main.gameBoard.pos == 23 || Main.gameBoard.pos == 43)  // 1 23 43
                Main.gameBoard.players.get(Main.gameBoard.cur).updateMove(2);

            else if (Main.gameBoard.pos == 14 || Main.gameBoard.pos == 15 || Main.gameBoard.pos == 36 ||
                    Main.gameBoard.pos == 37 || Main.gameBoard.pos == 60 || Main.gameBoard.pos == 61)  // 14 15 36 37 60 61
                Main.gameBoard.players.get(Main.gameBoard.cur).updateMove(1);

        } while (roll > 0 && Main.gameBoard.checkSpace(Main.gameBoard.pos) < 4);  // checks moves left and space is not magenta/retire

        return Main.gameBoard.checkSpace(Main.gameBoard.pos);  // returns
    }

    // method to move the players' pegs in the GUI
    public void movePegs ()
    {
        // previous position of a player peg
        int pos1 = Main.gameBoard.players.get(Main.gameBoard.cur).getPosition() -
                Main.gameBoard.players.get(Main.gameBoard.cur).getMove();

        // current position of a player peg
        int pos2 = Main.gameBoard.pos;

        // based on our board draft numbering, these movements result to an upward movement
        // 22 23 25 27 28 29 30 31 32 33 34 36 38 39 40 44
        if (pos2 == 22 || pos2 == 23 || pos2 == 25 || pos2 == 27 ||
                pos2 == 28 || pos2 == 29 || pos2 == 30 || pos2 == 31 ||
                pos2 == 32 || pos2 == 33 || pos2 == 34 || pos2 == 36 ||
                pos2 == 38 || pos2 == 39 || pos2 == 40 || pos2 == 44 ||
                (pos1 == 36 && pos2 == 37) || (pos1 == 59 && pos2 == 61)) {
            if (Main.gameBoard.cur == 0)
                Player1_Peg.setLayoutY(Player1_Peg.getLayoutY() - 45);

            else if (Main.gameBoard.cur == 1)
                Player2_Peg.setLayoutY(Player2_Peg.getLayoutY() - 45);

            else
                Player3_Peg.setLayoutY(Player3_Peg.getLayoutY() - 45);
        }

        // based on our board draft numbering, these movements result to an downward movement
        // 2 4 5 6 7 8 13 43 65 66 67 72
        else if (pos2 == 2 || pos2 == 4 || pos2 == 5 || pos2 == 6 ||
                pos2 == 7 || pos2 == 8 || pos2 == 13 || pos2 == 43 ||
                pos2 == 65 || pos2 == 66 || pos2 == 67 || pos2 == 72 ||
                (pos1 == 13 && pos2 == 15) || (pos1 == 60 && pos2 == 61)) {
            if (Main.gameBoard.cur == 0)
                Player1_Peg.setLayoutY(Player1_Peg.getLayoutY() + 45);

            else if (Main.gameBoard.cur == 1)
                Player2_Peg.setLayoutY(Player2_Peg.getLayoutY() + 45);

            else
                Player3_Peg.setLayoutY(Player3_Peg.getLayoutY() + 45);
        }

        // based on our board draft numbering, these movements result to an rightward movement
        // 1 3 9 10 11 12 14 16 17 18 19 20 21 24 26 35 68 69 70 71
        else if (pos2 == 1 || pos2 == 3 || pos2 == 9 || pos2 == 10 ||
                pos2 == 11 || pos2 == 12 || pos2 == 14 || pos2 == 16 ||
                pos2 == 17 || pos2 == 18 || pos2 == 19 || pos2 == 20 ||
                pos2 == 21 || pos2 == 24 || pos2 == 26 || pos2 == 35 ||
                pos2 == 68 || pos2 == 69 || pos2 == 70 || pos2 == 71 ||
                (pos1 == 14 && pos2 == 15) || (pos1 == 35 && pos2 == 37)) {
            if (Main.gameBoard.cur == 0)
                Player1_Peg.setLayoutX(Player1_Peg.getLayoutX() + 65);

            else if (Main.gameBoard.cur == 1)
                Player2_Peg.setLayoutX(Player2_Peg.getLayoutX() + 65);

            else
                Player3_Peg.setLayoutX(Player3_Peg.getLayoutX() + 65);
        }

        // based on our board draft numbering, these movements result to an leftward movement
        // 41 42 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 62 63 64
        else {
            if (Main.gameBoard.cur == 0)
                Player1_Peg.setLayoutX(Player1_Peg.getLayoutX() - 65);

            else if (Main.gameBoard.cur == 1)
                Player2_Peg.setLayoutX(Player2_Peg.getLayoutX() - 65);

            else
                Player3_Peg.setLayoutX(Player3_Peg.getLayoutX() - 65);
        }

    }

    // pay loan button in board is set to enabled when player has a loan, disabled if none, player can choose to pay their loan at any time during the game
    public void repayLoan ()
    {
        Main.gameBoard.players.get(Main.gameBoard.cur).payLoan(25000);
        if (Main.gameBoard.players.get(Main.gameBoard.cur).getLoan() > 0 &&
                Main.gameBoard.players.get(Main.gameBoard.cur).getCash() > Main.gameBoard.players.get(Main.gameBoard.cur).getLoan())
            payLoanButton.setDisable(false);

        else
            payLoanButton.setDisable(true);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " paid");
        eventLabel2.setText("25000");
        eventLabel3.setText("off their loan.");
        eventLabel4.setText("");

        playerDetails();
    }

    // left button in the junction pane, label depending on the junction path
    // this choice corresponds to: College Path (first junction) and Ordinary Path (second and third junction)
    public void pathOne ()
    {
        Main.gameBoard.pos = Main.gameBoard.players.get(Main.gameBoard.cur).getPosition();

        if (Main.gameBoard.pos == 0) { // first junction college path
            Main.gameBoard.collegePath();
            playerDetails();

            if (Main.gameBoard.cur == 0) {
                Player2_Job.setText("None");
                Player3_Job.setText("None");
            }
            else if (Main.gameBoard.cur == 1)
                Player3_Job.setText("None");
        }

        else if (Main.gameBoard.pos == 22) {
            eventLabel1.setText("Second junction");
            eventLabel2.setText("");
            eventLabel3.setText("Player chose");
            eventLabel4.setText("Ordinary Path");
        }

        else {
            eventLabel1.setText("Third junction");
            eventLabel2.setText("");
            eventLabel3.setText("Player chose");
            eventLabel4.setText("Ordinary Path");
        }

        junctionPane.setVisible(false);
    }

    // right button in the junction pane, label changes depending on the junction path
    // this choice corresponds to: Career Path (first junction), Change Career Path (second junction), and Start Family Path (third junction)
    public void pathTwo ()
    {
        Main.gameBoard.pos = Main.gameBoard.players.get(Main.gameBoard.cur).getPosition();

        if (Main.gameBoard.pos == 0) { // first junction career path
            Main.gameBoard.careerPath();
            playerDetails();

            if (Main.gameBoard.cur == 0) {
                Player2_Job.setText("None");
                Player3_Job.setText("None");
            }
            else if (Main.gameBoard.cur == 1)
                Player3_Job.setText("None");
        }

        else if (Main.gameBoard.pos == 22) { // second junction job search path
            Main.gameBoard.players.get(Main.gameBoard.cur).updateMove(2);

            eventLabel1.setText("Second junction");
            eventLabel2.setText("");
            eventLabel3.setText("Player chose");
            eventLabel4.setText("Change Career Path");
        }

        else { // third junction family path
            Main.gameBoard.players.get(Main.gameBoard.cur).updateMove(2);

            eventLabel1.setText("Third junction");
            eventLabel2.setText("");
            eventLabel3.setText("Player chose");
            eventLabel4.setText("Start Family Path");
        }

        junctionPane.setVisible(false);
    }

    // pick button is set to enabled when a player lands on an orange space
    // specific to when a player draws either Pay a Player or Collect from Player
    public void pickButton ()
    {
        pickPlayerPane.setVisible(true);

        if (Main.gameBoard.cur == 0) {
            playerOption1.setDisable(true);
        }

        else if (Main.gameBoard.cur == 1) {
            playerOption2.setDisable(true);
        }

        else {
            playerOption3.setDisable(true);
        }
    }

    // button for choosing player 1, disabled when the player number matches the button label
    public void pickOne ()
    {
        OrangeSpace.targetMethods(Main.gameBoard.cur, 0, Main.gameBoard.players, Main.gameBoard.aCard);
        Main.gameBoard.aCard = null;

        Main.gameBoard.turnCtr++;
        Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;

        while (Main.gameBoard.players.get(Main.gameBoard.cur).isRetired()) {  // if next player is retired
            Main.gameBoard.turnCtr++;
            Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;
        }

        pickPlayerPane.setVisible(false);
        playerOption1.setDisable(false);
        playerOption2.setDisable(false);

        pickPlayerButton.setDisable(true);
        rollButton.setDisable(false);

        playerTurnDisplay.setText("Player " + (Main.gameBoard.cur + 1) + "'s Turn");

        playerDetails();
    }

    // button for choosing player 2, disabled when the player number matches the button label
    public void pickTwo ()
    {
        OrangeSpace.targetMethods(Main.gameBoard.cur, 1, Main.gameBoard.players, Main.gameBoard.aCard);
        Main.gameBoard.aCard = null;

        Main.gameBoard.turnCtr++;
        Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;

        while (Main.gameBoard.players.get(Main.gameBoard.cur).isRetired()) {  // if next player is retired
            Main.gameBoard.turnCtr++;
            Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;
        }

        pickPlayerPane.setVisible(false);
        playerOption1.setDisable(false);
        playerOption2.setDisable(false);

        pickPlayerButton.setDisable(true);
        rollButton.setDisable(false);

        playerTurnDisplay.setText("Player " + (Main.gameBoard.cur + 1) + "'s Turn");

        playerDetails();
    }

    // button for choosing player 3, disabled when the player number matches the button label
    public void pickThree ()
    {
        OrangeSpace.targetMethods(Main.gameBoard.cur, 2, Main.gameBoard.players, Main.gameBoard.aCard);
        Main.gameBoard.aCard = null;

        Main.gameBoard.turnCtr++;
        Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;

        while (Main.gameBoard.players.get(Main.gameBoard.cur).isRetired()) {  // if next player is retired
            Main.gameBoard.turnCtr++;
            Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;
        }

        pickPlayerPane.setVisible(false);
        playerOption1.setDisable(false);
        playerOption2.setDisable(false);
        playerOption3.setDisable(false);

        pickPlayerButton.setDisable(true);
        rollButton.setDisable(false);

        playerTurnDisplay.setText("Player " + (Main.gameBoard.cur + 1) + "'s Turn");

        playerDetails();
    }

    // button for keeping the player's career, sets the drawn career and salary cards to null, indication of returning them
    public void keepCareer ()
    {
        changeCareerPane.setVisible(false);

        Main.gameBoard.careerDeck.returnCard(Main.gameBoard.newJob);
        Main.gameBoard.salaryDeck.returnCard(Main.gameBoard.newSalary);

        Main.gameBoard.newJob = null;
        Main.gameBoard.newSalary = null;
    }

    // button for changing the player's career, sets the drawn career and salary cards to the player's career and salary
    public void changeCareer ()
    {
        Main.gameBoard.changeCareerPath();
        changeCareerPane.setVisible(false);
        playerDetails();
    }

    // button is present when the player lands on a blue space that is either for Tip the Server or Computer Repair
    public void rollBlue ()
    {
        int rand = Board.rollNumber();
        rollLabel.setText(Integer.toString(rand));

        BlueSpace.rollMethods(Main.gameBoard.cur, rand, Main.gameBoard.players);
        Main.gameBoard.bCard = null;

        Main.gameBoard.turnCtr++;
        Main.gameBoard.cur = Main.gameBoard.turnCtr % Main.gameBoard.numPlayer;

        rollButton.setDisable(false);
        blueButton.setVisible(false);

        playerTurnDisplay.setText("Player " + (Main.gameBoard.cur + 1) + "'s Turn");

        playerDetails();
    }

    // button is present when player lands on Get Married space, given that the player should not be married beforehand
    public void rollMarried ()
    {
        int rand = Board.rollNumber();
        rollLabel.setText(Integer.toString(rand));

        MagentaSpace.getMarried(rand);
        marryRoll.setVisible(false);

        if (rand % 2 == 0)
            eventLabel4.setText("Players paid 10000");
        else
            eventLabel4.setText("Players paid 5000");

        playerDetails();
    }

    // button for first choice of career when player lands on College Career Choice space
    public void careerOne ()
    {
        Main.gameBoard.careerDeck.returnCard(Main.gameBoard.newJob2);
        Main.gameBoard.salaryDeck.returnCard(Main.gameBoard.newSalary2);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " got a job");
        eventLabel2.setText(Main.gameBoard.newJob.getCareer());
        eventLabel3.setText(Integer.toString(Main.gameBoard.newSalary.getSalary()));
        eventLabel4.setText(Integer.toString(Main.gameBoard.newSalary.getTaxDue()));

        Main.gameBoard.collegeCareer(Main.gameBoard.newJob, Main.gameBoard.newSalary);

        careerChoicePane.setVisible(false);

        playerDetails();
    }

    // button for second choice of career when player lands on College Career Choice space
    public void careerTwo ()
    {
        Main.gameBoard.careerDeck.returnCard(Main.gameBoard.newJob);
        Main.gameBoard.salaryDeck.returnCard(Main.gameBoard.newSalary);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " got a job");
        eventLabel2.setText(Main.gameBoard.newJob2.getCareer());
        eventLabel3.setText(Integer.toString(Main.gameBoard.newSalary2.getSalary()));
        eventLabel4.setText(Integer.toString(Main.gameBoard.newSalary2.getTaxDue()));

        Main.gameBoard.collegeCareer(Main.gameBoard.newJob2, Main.gameBoard.newSalary2);

        careerChoicePane.setVisible(false);

        playerDetails();
    }

    // button for Apartment, disables when selected by a player and enables when returned by a player
    public void houseOne ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Apartment")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        apartButton.setDisable(true);

        playerDetails();
    }

    // button for Condominium, disables when selected by a player and enables when returned by a player
    public void houseTwo ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Condominium")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        condoButton.setDisable(true);

        playerDetails();
    }

    // button for Cabin, disables when selected by a player and enables when returned by a player
    public void houseThree ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Cabin")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        cabinButton.setDisable(true);

        playerDetails();
    }

    // button for Cottage, disables when selected by a player and enables when returned by a player
    public void houseFour ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Cottage")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        cottageButton.setDisable(true);

        playerDetails();
    }

    // button for Bungalow, disables when selected by a player and enables when returned by a player
    public void houseFive ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Bungalow")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        bungalowButton.setDisable(true);

        playerDetails();
    }

    // button for Mansion, disables when selected by a player and enables when returned by a player
    public void houseSix ()
    {
        HouseCard hc = Main.gameBoard.houseDeck.drawCard(0);

        while (!hc.getName().equals("Mansion")) {
            Main.gameBoard.houseDeck.returnCard(hc);
            hc = Main.gameBoard.houseDeck.drawCard(0);
        }

        if (Main.gameBoard.players.get(Main.gameBoard.cur).getCash() < hc.getBuyPrice()) {
            Main.gameBoard.players.get(Main.gameBoard.cur).addLoan();

            eventLabel2.setText("and loaned cash");
        }

        else
            eventLabel2.setText("");

        MagentaSpace.buyAHouse(Main.gameBoard.players.get(Main.gameBoard.cur), hc);

        eventLabel1.setText("Player " + (Main.gameBoard.cur + 1) + " bought a new house");
        eventLabel3.setText(hc.getName());
        eventLabel4.setText("Player paid " + hc.getBuyPrice());

        buyHousePane.setVisible(false);
        mansionButton.setDisable(true);

        playerDetails();
    }

    // button for keeping the player's current house
    public void keepHouse ()
    {
        changeHousePane.setVisible(false);
    }

    // button for changing the player's current house
    public void buyHouse ()
    {
        HouseCard hc = Main.gameBoard.players.get(Main.gameBoard.cur).sellHouseCard();
        Main.gameBoard.houseDeck.returnCard(hc);  // returns sold house to the house deck
        String name = hc.getName();

        if (name.equals("Apartment"))
            apartButton.setDisable(false);
        else if (name.equals("Condominium"))
            condoButton.setDisable(false);
        else if (name.equals("Cabin"))
            cabinButton.setDisable(false);
        else if (name.equals("Cottage"))
            cottageButton.setDisable(false);
        else if (name.equals("Bungalow"))
            bungalowButton.setDisable(false);
        else
            mansionButton.setDisable(false);

        changeHousePane.setVisible(false);
        buyHousePane.setVisible(true);
    }

    // button in the board to load the end game screen
    public void displayResults () throws IOException
    {
        Stage primaryStage = Main.stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame.fxml"));
        Parent endGameRoot = loader.load();
        Scene endGameScene = new Scene (endGameRoot);

        primaryStage.setScene(endGameScene);
        primaryStage.show();
    }

    // button in the end game screen to display the actual results of the game
    public void paneDisplay ()
    {
        paneDisplayButton.setVisible(false);
        gameOverText.setVisible(true);

        firstPlaceText.setVisible(true);
        firstPlaceLabel.setVisible(true);
        firstPlaceLabel.setText(Main.gameBoard.finishOrder.get(0));

        secondPlaceText.setVisible(true);
        secondPlaceLabel.setVisible(true);
        secondPlaceLabel.setText(Main.gameBoard.finishOrder.get(1));

        if (Main.gameBoard.numPlayer == 3) {
            thirdPlaceText.setVisible(true);
            thirdPlaceLabel.setVisible(true);
            thirdPlaceLabel.setText(Main.gameBoard.finishOrder.get(2));
        }

        congratsText.setVisible(true);
        endGameExit.setVisible(true);
    }

    // updates player details being displayed
    public void playerDetails ()
    {
        String p1Job;
        if (Main.gameBoard.players.get(0).hasCareer())  // if player 1 has a job
            p1Job = Main.gameBoard.players.get(0).getCareer();
        else if (Main.gameBoard.players.get(0).getPosition() == 0)  // if player 1 is at start
            p1Job = "None";
        else if (Main.gameBoard.players.get(0).isRetired())  // if player 1 is retired
            p1Job = "Retired";
        else  // if player 1 is in college path
            p1Job = "College Student";

        while (Main.gameBoard.players.get(0).getCash() < 0 && !Main.gameBoard.players.get(0).isRetired())  // gives loan if negative cash
            Main.gameBoard.players.get(0).addLoan();

        String p1Cash = Integer.toString(Main.gameBoard.players.get(0).getCash());
        String p1Loan = Integer.toString(Main.gameBoard.players.get(0).getLoan());
        String p1Salary = Integer.toString(Main.gameBoard.players.get(0).getSalary());
        String p1TaxDue = Integer.toString(Main.gameBoard.players.get(0).getTaxDue());
        String p1Married = Boolean.toString(Main.gameBoard.players.get(0).isMarried());

        String p1House;
        if (Main.gameBoard.players.get(0).hasHouse())  // displays the house of player 1
            p1House = Main.gameBoard.players.get(0).getHouse();
        else  // displays "None" if player 1 doesn't have a house
            p1House = "None";

        String p1Children = Integer.toString(Main.gameBoard.players.get(0).getChild());

        // displays player 1's details
        Player1_Job.setText(p1Job);
        Player1_Cash.setText(p1Cash);
        Player1_Loan.setText(p1Loan);
        Player1_Salary.setText(p1Salary);
        Player1_TaxDue.setText(p1TaxDue);
        Player1_Married.setText(p1Married);
        Player1_House.setText(p1House);
        Player1_Children.setText(p1Children);

        String p2Job;
        if (Main.gameBoard.players.get(1).hasCareer())  // if player 2 has a job
            p2Job = Main.gameBoard.players.get(1).getCareer();
        else if (Main.gameBoard.players.get(1).getPosition() == 0)  // if player 2 is at start
            p2Job = "None";
        else if (Main.gameBoard.players.get(1).isRetired())  // if player 2 is retired
            p2Job = "Retired";
        else  // if player 2 is in college path
            p2Job = "College Student";

        while (Main.gameBoard.players.get(1).getCash() < 0 && !Main.gameBoard.players.get(1).isRetired())  // gives loan if negative cash
            Main.gameBoard.players.get(1).addLoan();

        String p2Cash = Integer.toString(Main.gameBoard.players.get(1).getCash());
        String p2Loan = Integer.toString(Main.gameBoard.players.get(1).getLoan());
        String p2Salary = Integer.toString(Main.gameBoard.players.get(1).getSalary());
        String p2TaxDue = Integer.toString(Main.gameBoard.players.get(1).getTaxDue());
        String p2Married = Boolean.toString(Main.gameBoard.players.get(1).isMarried());
        String p2House;
        if (Main.gameBoard.players.get(1).hasHouse())  // displays the house of player 2
            p2House = Main.gameBoard.players.get(1).getHouse();
        else  // displays "None" if player 2 doesn't have a house
            p2House = "None";
        String p2Children = Integer.toString(Main.gameBoard.players.get(1).getChild());

        // displays player 2's details
        Player2_Job.setText(p2Job);
        Player2_Cash.setText(p2Cash);
        Player2_Loan.setText(p2Loan);
        Player2_Salary.setText(p2Salary);
        Player2_TaxDue.setText(p2TaxDue);
        Player2_Married.setText(p2Married);
        Player2_House.setText(p2House);
        Player2_Children.setText(p2Children);

        if (Main.gameBoard.numPlayer == 3) {  // if 3-player game
            String p3Job;
            if (Main.gameBoard.players.get(2).hasCareer())  // if player 3 has a job
                p3Job = Main.gameBoard.players.get(2).getCareer();
            else if (Main.gameBoard.players.get(2).getPosition() == 0)  // if player 3 is at start
                p3Job = "None";
            else if (Main.gameBoard.players.get(2).isRetired())  // if player 3 is retired
                p3Job = "Retired";
            else  // if player 3 is in college path
                p3Job = "College Student";

            while (Main.gameBoard.players.get(2).getCash() < 0 && !Main.gameBoard.players.get(2).isRetired())  // gives loan if negative cash
                Main.gameBoard.players.get(2).addLoan();

            String p3Cash = Integer.toString(Main.gameBoard.players.get(2).getCash());
            String p3Loan = Integer.toString(Main.gameBoard.players.get(2).getLoan());
            String p3Salary = Integer.toString(Main.gameBoard.players.get(2).getSalary());
            String p3TaxDue = Integer.toString(Main.gameBoard.players.get(2).getTaxDue());
            String p3Married = Boolean.toString(Main.gameBoard.players.get(2).isMarried());
            String p3House;
            if (Main.gameBoard.players.get(2).hasHouse())  // displays the house of player 3
                p3House = Main.gameBoard.players.get(2).getHouse();
            else  // displays "None" if player 3 doesn't have a house
                p3House = "None";
            String p3Children = Integer.toString(Main.gameBoard.players.get(2).getChild());

            // displays player 3's details
            Player3_Job.setText(p3Job);
            Player3_Cash.setText(p3Cash);
            Player3_Loan.setText(p3Loan);
            Player3_Salary.setText(p3Salary);
            Player3_TaxDue.setText(p3TaxDue);
            Player3_Married.setText(p3Married);
            Player3_House.setText(p3House);
            Player3_Children.setText(p3Children);
        }
    }
}