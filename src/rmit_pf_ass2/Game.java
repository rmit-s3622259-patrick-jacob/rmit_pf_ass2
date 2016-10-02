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

	public void runGame() {

		Game setboard = new Game();
		setboard.setBoard();

		Game boardDisplay = new Game();
		boardDisplay.display();

		Game userMenu = new Game();
		userMenu.menu();
		// initialize instance variables
	}

	private int menu() {

		System.out.println();
		int move = 5;
		Scanner input = new Scanner(System.in);

		boolean validInput = false;

		do { // Do while loop until valid input is achieved
			validInput = true;
			try {
				// Prompt for User to enter in their choice of move
				System.out.println("=====Wumpus====");
				System.out.println("1. Move player left");
				System.out.println("2. Move player right");
				System.out.println("3. Move player up");
				System.out.println("4. Move player down");
				System.out.println("5. Quit");
				move = input.nextInt();
				if (move >= 1 && move <= 5) { /*
												 * here is to validate that User
												 * entry is between 1 - 5
												 */
					validInput = true;
				} else { // inform the user of wrong entry and to let user
							// repeat entry
					System.out.println("Wrong entry: please enter a number between 1 or 5");
					move = input.nextInt();
					if (move < 1 && move > 5) {
						validInput = false;
					}
				}
			} catch (Exception e) { /*
									 * 'catch' user when entry is not intenger
									 * and to let user repeat entry
									 */
				validInput = false;
				move = input.nextInt();
			}
		} while (!validInput);

		System.out.println("Your entry: " + move);
		return (move);
	}

	private String[][] setBoard() {

		board = new String[4][4];

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
		

	//	get position of Wumpus position through Wumpus class
		//do
		{
		ClearGround posCG = new ClearGround();
		int [][] clearground = posCG.display(2);
		cgh = clearground [0][0];
		cgv = clearground [1][0];
		
	if (board[cgh][cgv] == null){
		board[cgh][cgv] = "*" + h;
	//	break;
	//	} else {
	//	} continue;
		} while (board[cgh][cgv] == null);}
		
		//	get position of Wumpus position through Wumpus class
//		do{
		{
		Wumpus posWumpus = new Wumpus();
		int [][] wumpus = posWumpus.display(2);
		pwh = wumpus [0][0];
		pwv = wumpus [1][0];
		
		if (board[pwh][pwv] == null) {
			board[pwh][pwv] = "W" + h;
//			break;
//		} else {
//			continue;
		} while (board[pwh][pwv] == null); }
		
		
		//amount of gold that shall be generated between 1-3
		int ag = 0;
		Random amountGoldGenerator = new Random();
		ag = amountGoldGenerator.nextInt(4)+1; 
		
	//	Random times of loop as generated above and writing into the array
		{
		for (int i = 0; i < ag; i++){	
		Gold posGold = new Gold();
		int [][] Gold = posGold.display(2);	
		
		 	pgh = Gold [0][0];  //setting horizontal positons into int pgh
		 	pgv = Gold [1][0]; 	//setting vertical positons into int pgv
		 	
		if (board[pgh][pgv] == null) {
		board[pgh][pgv] = "g" + h; //writing Gold into positions from int pgh, int pgv
		} while (board[pgh][pgv] == null);
		}
		
		//loop to get 3 Pit Positions 
	
		for (int i = 0; i < 3; i++){	
		{
		Pit posPit = new Pit();
		int [][] Pit = posPit.display(3);	
		 	pph = Pit [0][0]; //setting horizontal positions into int pph
		 	ppv = Pit [1][0]; //setting vertical positions into int ppv
		if (board[pph][ppv] == null) {
			board[pph][ppv] = "p" + h; //writing Pit into positions from int pph, int ppv
		} while (board[pph][ppv] == null);
		}}}
		
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
	
		}

	private void display() {

		// printing of board after all postions are set...
		Game setboard = new Game();
		String[][] board = setboard.setBoard();

		for (int r = 0; r < board.length; r++) {
			System.out.println();
			for (int c = 0; c < board.length; c++) {
				System.out.print(board[r][c]);
			}
		}
	}
}
