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
		board = new String[4][4];
		int ppr = 0; // initial player row
		int ppc = 0; // initial player column
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
			if (endGame == false) { // if game hasn't been ended invoke senseNearby method
				myGame.senseNearby(); // sense what game items are near player's position
			}

		} while (endGame == false);

	}

	// method to display menu
	private int menu() { 

		System.out.println();
		int move;
		Scanner input = new Scanner(System.in);

		boolean validInput = false;

		do { // do while loop until there is a valid input

			// Prompt for player to enter in their choice of move
			System.out.println("=====Wumpus====");
			System.out.println("1. Move player left");
			System.out.println("2. Move player right");
			System.out.println("3. Move player up");
			System.out.println("4. Move player down");
			System.out.println("5. Quit");
			move = input.nextInt();
			if (move >= 1 && move <= 5) { // check that player has input a valid entry
				validInput = true;
			} 
			else { // inform the user of wrong entry and to let player input another entry
				System.out.println("Wrong entry: please enter a number between 1 or 5");
				move = input.nextInt();
				if (move < 1 && move > 5) {
					validInput = false;
				}
			}

		} while (!validInput);

		System.out.println("Your entry: " + move);
		return move;
	} // end menu method

	// move player's position on board
	private boolean movePlayer() {	

		String h = "|";
		board[ppr][ppc] = "." + h; // remove * from previous player's position, mark as clear ground

		int move = menu();	// get player's move choice

		if (move == 1) { // move player left
			if (ppc == 1 || ppc == 2 || ppc == 3) { // player column = 1, 2 or 3
				ppc = ppc - 1;
			} else if (ppc == 0) { // player column = 0, move player position to other side of board
				ppc = ppc + 3;
			}
		}
		if (move == 2) { // move player right
			if (ppc == 0 || ppc == 1 || ppc == 2) { // player column = 0, 1, or 2
				ppc = ppc + 1;
			} else if (ppc == 3) { // player column = 3, move player position to other side of board
				ppc = ppc - 3;
			}
		}
		if (move == 3) { // move player up
			if (ppr == 1 || ppr == 2 || ppr == 3) { // player row = 1, 2 or 3
				ppr = ppr - 1;
			} else if (ppr == 0) { // player row = 0, move player position to other side of board
				ppr = ppr + 3;
			}
		}
		if (move == 4) { // move player down
			if (ppr == 0 || ppr == 1 || ppr == 2) { // player row = 0, 1 or 2
				ppr = ppr + 1;
			} else if (ppr == 3) { // player row = 3, move player position to other side of board
				ppr = ppr - 3;
			}
		}
		if (move == 5) { // user quits
			System.out.println("Game over");
			endGame = true;
		}

		// set position of where player will move to
		futureMove = board[ppr][ppc];

		// check what item's are at player's future position
		getMyGround();
		
		// * marks player's current position
		board[ppr][ppc] = "*" + h; 

		return endGame;
	} // end movePlayer method

	// method to set item positions on board
	private String[][] setBoard() {

		System.out.println();
		String v = "_";
		String h = "|";

		int cgh = 0; // position ClearGround horizontal
		int cgv = 0; // position ClearGround vertical

		int pwh = 0; // position Wumpus horizontal
		int pwv = 0; // position Wumpus vertical

		int pph = 0; // position Pits horizontal
		int ppv = 0; // position Pits vertical

		int pgh = 0; // position Gold horizontal
		int pgv = 0; // position Gold vertical

		board[ppr][ppc] = "*" + h; // * marks initial player position

		// get position of clearground through ClearGround class
		// do
		{
			ClearGround posCG = new ClearGround();
			int[][] clearground = posCG.display(2);
			cgh = clearground[0][0];
			cgv = clearground[1][0];

			if (board[cgh][cgv] == null) {
				board[cgh][cgv] = "." + h;
				// break;
				// } else {
				// } continue;
			}
			while (board[cgh][cgv] == null)
				;

		}

		// get position of Wumpus through Wumpus class
		// do{
		{
			Wumpus posWumpus = new Wumpus();
			int[][] wumpus = posWumpus.display(2);
			pwh = wumpus[0][0];
			pwv = wumpus[1][0];

			if (board[pwh][pwv] == null) {
				board[pwh][pwv] = "W" + h;
				// break;
				// } else {
				// continue;
			}
			while (board[pwh][pwv] == null)
				;
		}

		// number of gold that shall be generated between 1-3
		int ag = 0;
		Random amountGoldGenerator = new Random();
		ag = amountGoldGenerator.nextInt(4) + 1;

		// Random times of loop as generated above and writing into the array
		{
			for (int i = 0; i < ag; i++) {
				Gold posGold = new Gold();
				int[][] Gold = posGold.display(2);

				pgh = Gold[0][0]; // setting horizontal positons into int pgh
				pgv = Gold[1][0]; // setting vertical positons into int pgv

				if (board[pgh][pgv] == null) {
					board[pgh][pgv] = "g" + h; // writing Gold into positions
												// from int pgh, int pgv
				}
				while (board[pgh][pgv] == null)
					;
			}

			// loop to get 3 Pit Positions

			for (int i = 0; i < 3; i++) {
				{
					Pit posPit = new Pit();
					int[][] Pit = posPit.display(3);
					pph = Pit[0][0]; // setting horizontal positions into int
										// pph
					ppv = Pit[1][0]; // setting vertical positions into int ppv
					if (board[pph][ppv] == null) {
						board[pph][ppv] = "p" + h; // writing Pit into positions
													// from int pph, int ppv
					}
					while (board[pph][ppv] == null)
						;
				}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null) {
					board[i][j] = v + h;
				} else {
					// board[i][j] = v;
					// String v = "_";
					continue;
					// System.out.print(board[i][j]);
				}
				if (j < 0) {
					board[i][j] = h;
					// System.out.print(h);
				} else {
					continue;
					// System.out.println();
				}
			}
		}

		return board;

	} // end setBoard method

	// printing of board after all positions are set
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
		boolean isGold = false;
		boolean isPit = false;
		boolean isWumpus = false;

		if (futureMove.equals("g|")) {// check if there is gold nearby
			isGold = true;

			myGold.increaseScore(); // increase gold score by 1
		}
		if (futureMove.equals("p|")) { // check if there is a pit nearby
			isPit = true;
			endGame = true; // game has ended
			System.out.println("Player has landed in a pit\n GAME OVER");
		}

		if (futureMove.equals("W|")) {// check if there is a Wumpus nearby
			isWumpus = true;
			endGame = true; // game has ended
			System.out.println("Player has been eaten by the Wumpus\n GAME OVER");
		}

		return endGame;

	}

	// check what items are near the player's position
	private void senseNearby() {
		boolean goldNearby = false;
		boolean pitNearby = false;
		boolean wumpusNearby = false;

		if (ppr == 0) { // if player position row = 0
			if (ppc == 0) // if player position column = 0
				for (int i = 0; i < 2; i++)
					for (int j = 0; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = 0; i < 2; i++)
					for (int j = -1; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = 0; i < 2; i++)
					for (int j = -1; j < 1; j++) {
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
				for (int i = -1; i < 2; i++)
					for (int j = 0; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = -1; i < 2; i++)
					for (int j = -1; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = -1; i < 2; i++)
					for (int j = -1; j < 1; j++) {
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
				for (int i = -1; i < 1; i++)
					for (int j = 0; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 1 || ppc == 2) // if player position column = 1 or 2
				for (int i = -1; i < 1; i++)
					for (int j = -1; j < 2; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
			if (ppc == 3) // if player position column = 3
				for (int i = -1; i < 1; i++)
					for (int j = -1; j < 1; j++) {
						if (board[ppr + i][ppc + j].equals("g|")) // check if there is gold nearby
							goldNearby = true;
						if (board[ppr + i][ppc + j].equals("p|")) // check if there is a pit nearby
							pitNearby = true;
						if (board[ppr + i][ppc + j].equals("W|")) // check if there is a Wumpus nearby
							wumpusNearby = true;
					}
		}

		if (goldNearby == true) // if there is gold near the player's position
			System.out.println("There is a faint glitter nearby");
		if (pitNearby == true) // if there is a pit near the player's position
			System.out.println("There is a breeze nearby");
		if (wumpusNearby == true) // if there is a Wumpus near the player's position
			System.out.println("There is a vile smell nearby");

	} // end senseNearby method
}
