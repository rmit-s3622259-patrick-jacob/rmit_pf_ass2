package rmit_pf_ass2;

/* From the assignment description:
 * You should write a Game class, which has a 2D array of size 4 x 4 of GameItem called 
 * board for implementing the Wumpus game described above.
 * Game should provide: 
 * • a private method setBoard(), which instantiates objects on the board
 * • a private method display() which will display the board.
 * • a private method senseNearby(), which displays text about what the
 * player can sense from the board elements immediately surrounding the player.
 * • a private method menu() which will provide a menu asking the user to
 * make a choice from the following and obtain the user input.
 * a public method runGame() that will display the board, print out what 
 * the player can sense, present the menu and process the user's decision 
 * according to the game play described above.
 * Game display() method is used to loop through GameItem array to display the board.
 */
import java.util.*;
import java.util.Random;

public class Game {

	private String[][] board;
	private int ppr; // position player row
	private int ppc; // position player column
	private boolean endGame; // variable to check if game has ended
	private String futureMove; // position where player will move to
	private Gold myGold; // gold object

	public Game() {
		board = new String[4][4]; // board is a 4x4 array of String variables
		boolean endGame = false; // endGame initially set to game has not ended
		futureMove = null; // player has not initially moved to another position
		myGold = new Gold(); // create new Gold object
	}

	// method to run game
	public void runGame() {

		Game myGame = new Game();
		myGame.setBoard(); // set board at start of game

		do { // continue playing game until game ends
			myGame.display(); // display board
			endGame = myGame.movePlayer(); // move player's position
			if (endGame == false) { // if game hasn't been ended invoke
									// senseNearby method
				myGame.senseNearby(); // sense what game items are near player's
										// position
			}
		} while (endGame == false);
	}

	// method to display menu
	private int menu() {

		System.out.println();
		int move = 0; // player move choice is initially set to 0
		boolean validInput = false;

		do { // do while loop until there is a valid input
			try {
				Scanner input = new Scanner(System.in);

				// Prompt for player to enter in their choice of move
				System.out.println();
				System.out.println("=====Wumpus====");
				System.out.println("1. Move player left");
				System.out.println("2. Move player right");
				System.out.println("3. Move player up");
				System.out.println("4. Move player down");
				System.out.println("5. Quit");
				move = input.nextInt(); // User entry where player shall move
				if (move >= 1 && move <= 5) { // check that player has input a valid entry
					validInput = true;
				} else { /* inform the user of wrong Number entry and to let player
							input another entry */
					System.out.println("Wrong entry: please try again");
					validInput = false;
					continue;
				}
			} catch (Exception e) { //To catch entry that isn't a number
				System.out.println("Wrong entry: please try again");
				validInput = false;
			}

		} while (!validInput); //leave loop once entry is valid between 1 - 5

		switch (move) { //Display player's chosen move 
		case 1: // Player moves left
			System.out.println("Player moves left (Entry: " + move + ")");
			break;
		case 2: // Player moves right
			System.out.println("Player moves right (Entry: " + move + ")");
			break;
		case 3: // Player moves up
			System.out.println("Player moves up (Entry: " + move + ")");
			break;
		case 4: // Player moves down
			System.out.println("Player moves down (Entry: " + move + ")");
			break;
		case 5: // Player quits
			System.out.println("Player decided to quit (Entry: " + move + ")");
			break;
		default:
			System.out.println("Wrong Entry");
		}

		return move;
	} // end menu method

	// move player's position on board
	private boolean movePlayer() {
		
		String h = "|";	// Characters for generating the "walls" for cave grid horizontally
		
		
		board[ppr][ppc] = "." + h; // remove * from previous player's position,
									// mark as clear ground
		
		int move = menu(); // get player's move choice

		if (move == 1) { // move player left
			if (ppc == 1 || ppc == 2 || ppc == 3) { // player column = 1, 2 or 3
				ppc = ppc - 1;
			} else if (ppc == 0) { // player column = 0, move player position to
									// other side of board
				ppc = ppc + 3;
			}
		}
		if (move == 2) { // move player right
			if (ppc == 0 || ppc == 1 || ppc == 2) { // player column = 0, 1, or 2
				ppc = ppc + 1;
			} else if (ppc == 3) { // player column = 3, move player position to
									// other side of board
				ppc = ppc - 3;
			}
		}
		if (move == 3) { // move player up
			if (ppr == 1 || ppr == 2 || ppr == 3) { // player row = 1, 2 or 3
				ppr = ppr - 1;
			} else if (ppr == 0) { // player row = 0, move player position to
									// other side of board
				ppr = ppr + 3;
			}
		}
		if (move == 4) { // move player down
			if (ppr == 0 || ppr == 1 || ppr == 2) { // player row = 0, 1 or 2
				ppr = ppr + 1;
			} else if (ppr == 3) { // player row = 3, move player position to
									// other side of board
				ppr = ppr - 3;
			}
		}
		if (move == 5) { // user quits
			System.out.println("Thank you for playing the Wumpus Game \n Have a nice day");
			endGame = true;
		}

		// set position of where player will move to
		futureMove = board[ppr][ppc];

		// check what item's are at player's future position
		getMyGround();
		
		// * marks player's current position
		board[ppr][ppc] = "*" + h;
		
		// check if there is any gold left on the board
		if (endGame == false)
			checkGoldLeft();
				
		return endGame;
	} // end movePlayer method

	// method to set game items on the board and generate Board
	private String[][] setBoard() {

		System.out.println();
		String v = "_"; //Characters for generating the "walls" for cave grid vertically
		String h = "|"; //Characters for generating the "walls" for cave grid horizontally

		int cgh = 0; // position ClearGround horizontal
		int cgv = 0; // position ClearGround vertical

		int pwh = 0; // position Wumpus horizontal
		int pwv = 0; // position Wumpus vertical

		int pph = 0; // position Pits horizontal
		int ppv = 0; // position Pits vertical

		int pgh = 0; // position Gold horizontal
		int pgv = 0; // position Gold vertical
		
		
		
		// get position of Wumpus through Wumpus class
		do { /*Do while to ensure that if random generated position is already
			occupied a new position is generated*/
			Wumpus posWumpus = new Wumpus(); 
			int[][] wumpus = posWumpus.display(1); //instantiate Wumpus position
			pwh = wumpus[0][0]; // write position x axis of board array into variable pwh
			pwv = wumpus[1][0]; // write position y axis of board array into variable pwv

			if (board[pwh][pwv] == null) { //check if board position is already occupied
				board[pwh][pwv] = "W" + h; //if not - write the Wumpus into it
			}

		} while (!board[pwh][pwv].equals("W|")); 

		//random number between 1-3 of gold for the board that shall be generated
		int ag = 0; // amount of gold
		Random amountGoldGenerator = new Random();
		ag = amountGoldGenerator.nextInt(3) + 1; //3 + 1 to ensure that if 0 then at least 1 Gold 
		
		boolean testGold = true; /*variable to prevent that loop ends if 
									twice the same position of Gold is generated and the loop ends 
									prematurely */
		
		//loop goes as many times as generated in ag and writes Gold into the board array
		for (int i = 0; i < ag; i++) { 
			do {/*Do while loop to ensure that if random generated position is already
				occupied a new position is generated*/
				Gold posGold = new Gold();
				int[][] Gold = posGold.display(1); //instantiate a Gold position

				pgh = Gold[0][0]; // setting horizontal positions into int pgh
				pgv = Gold[1][0]; // setting vertical positions into int pgv

				if (board[pgh][pgv] == null) {
					board[pgh][pgv] = "g" + h; /* writing Gold into positions
												 from int pgh, int pgv */
					testGold = true;
				/*here the control structure that ensures if 
					gold or another item is already in the newly generated position 
					the loop will continue */	
				} else if (board[pgh][pgv].equals("g|")) {
					testGold = false;
				} else {
					testGold = false;
				}
			} while (testGold == false);
		}

		// loop to get 3 Pit Positions
		boolean testPits = true; /*variable to prevent that loop ends if 
		twice the same position of pit is generated and the loop ends 
		prematurely */

		for (int i = 0; i < 3; i++) {
			do { /*Do while to ensure that if random generated position is already
				occupied a new position is generated*/
				Pit posPit = new Pit();
				int[][] Pit = posPit.display(3); //instantiate pits position
				pph = Pit[0][0]; // setting horizontal positions into int pph
				ppv = Pit[1][0]; // setting vertical positions into int ppv
				if (board[pph][ppv] == null) {
					board[pph][ppv] = "p" + h; // writing Pit into positions
												// from int pph, int ppv
					testPits = true;
					/*here the control structure that ensures if 
					pit or another item is already in the newly generated position 
					the loop will continue (to get a new position*/	
				} else if (board[pph][ppv].equals("p|")) {
					testPits = false;
				} else {
					testPits = false;
				}
			} while (testPits == false);
		}
		
		// get position of clearGround through ClearGround class
		do { /*Do while to ensure that if random generated position is already
						occupied a new position is generated*/
			ClearGround posCG = new ClearGround(); 
			int[][] clearground = posCG.display(1); //instantiate ClearGround position
			cgh = clearground[0][0]; // write position x axis of board array into variable cgh
			cgv = clearground[1][0]; // write position y axis of board array into variable cgv

			if (board[cgh][cgv] == null) { //check if board position is already occupied
				board[cgh][cgv] = "." + h; //if not - write the ClearGround into it
				}
			} while (!board[cgh][cgv].equals(".|"));
				
		
		//loop to set in the left over grid positions the "walls" _ or | of the cave
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null) {
					board[i][j] = v + h;
				} else {
					continue;
				}
				if (j < 0) {
					board[i][j] = h;
				} else {
					continue;
				}
			}
		}
		
		// set initial player position in a random generated position
				
		board[cgh][cgv] = board[ppr][ppc]; // place initial player position over a clearGround position
		board[ppr][ppc] = "*" + h; // * marks initial player position
				
		return board;

	} // end setBoard method

	// printing of board after all item positions and "walls" are set
	private void display() {

		for (int r = 0; r < board.length; r++) {
			System.out.println();
			for (int c = 0; c < board.length; c++) {
				System.out.print(board[r][c]);
			}
		}

	} // end display method

	// check what items are at position player will move to
	private boolean getMyGround() {
		boolean isGold = false;	// variable to check if there is gold at a position
		boolean isPit = false; // variable to check if there is a pit at a position
		boolean isWumpus = false; // // variable to check if there is a Wumpus at a position
		
		if (futureMove.equals("g|")) {// check if there is gold nearby
			isGold = true;
			myGold.increaseScore(); // increase gold score by 1
		}
		
		if (futureMove.equals("p|")) { // check if there is a pit nearby
			isPit = true;
			endGame = true; // game has ended
			System.out.println(); // print to next line after previous line
			System.out.println("Player has landed in a pit\n GAME OVER");
		}

		if (futureMove.equals("W|")) {// check if there is a Wumpus nearby
			isWumpus = true;
			endGame = true; // game has ended
			System.out.println(); // print to next line after previous line
			System.out.println("Player has been eaten by the Wumpus\n GAME OVER");
		}

		return endGame;
			
	} // end getMyGround method

	private boolean checkGoldLeft(){ // check if there is any gold left on the board
		int countGold = 0; // count how many gold are left
		
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].equals("g|")) // if there is gold on the board countGold increases
					countGold++;
			}
		if (countGold == 0) { // no gold left so game ends
			endGame = true; // game has ended
			display(); // display final board
			System.out.println(); // print to next line after board is displayed
			System.out.println();
			System.out.println("Player found all the gold\n Player wins the game");
		} else // there is gold left so game continues
			endGame = false;
		
		return endGame;
	} // end checkGoldLeft method
	
	
	// check what items are near the player's position
	private void senseNearby() {
		boolean goldNearby = false;
		boolean pitNearby = false;
		boolean wumpusNearby = false;

		if (ppr == 0) { // if player position row = 0
			if (ppc == 0) // if player position column = 0
				for (int i = 0; i < 2; i++) // check rows around player's position
					for (int j = 0; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = 0; i < 2; i++) // check rows around player's position
					for (int j = -1; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = 0; i < 2; i++) // check rows around player's position
					for (int j = -1; j < 1; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
		}

		if (ppr == 1 || ppr == 2) { // if player position row = 1 or 2
			if (ppc == 0) // if player position column = 0
				for (int i = -1; i < 2; i++) // check rows around player's position
					for (int j = 0; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = -1; i < 2; i++) // check rows around player's position
					for (int j = -1; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = -1; i < 2; i++) // check rows around player's position
					for (int j = -1; j < 1; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
		}

		if (ppr == 3) { // if player position row = 3
			if (ppc == 0) // if player position column = 0
				for (int i = -1; i < 1; i++) // check rows around player's position
					for (int j = 0; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = -1; i < 1; i++) // check rows around player's position
					for (int j = -1; j < 2; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = -1; i < 1; i++) // check rows around player's position
					for (int j = -1; j < 1; j++) { // check columns around player's position
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
		}

		if (goldNearby == true){ // if there is gold near the player's position
			System.out.println(); // print blank line after previous line
			System.out.println("There is a faint glitter nearby");
		}
		if (pitNearby == true){ // if there is a pit near the player's position
			System.out.println(); // print blank line after previous line
			System.out.println("There is a breeze nearby");
		}
			
		if (wumpusNearby == true){ // if there is a Wumpus near the player's position
			System.out.println(); // print blank line after previous line
			System.out.println("There is a vile smell nearby");
		}

	} // end senseNearby method
}
