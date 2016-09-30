package rmit_pf_ass2;

import java.util.*;

public class Game {
	
	private char[][] player;

	public void runGame() {
		Game myGame = new Game();
		myGame.menu();
	}
	
	private void setBoard() {
		Game test = new Game();
		test.display();
	}
	
	private void display() {
		
	}
	
	private void senseNearby() {
		
	}
	
	private int menu() {

		int move = 0;
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
									 * 'catch' user when entry is not integer
									 * and to let user repeat entry
									 */
				validInput = false;
				move = input.nextInt();
			}
		} while (!validInput);

		System.out.println("Your entry: " + move);
		return (move);
		
	}

}
