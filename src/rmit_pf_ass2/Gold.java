package rmit_pf_ass2;

import java.util.*;
/* From the assignment description
 * Gold is displayed as 'g',
 * The player can sense, but not see, what is in the immediate grid elements around the
 * player. A gold will be sensed as a faint glitter
 * Classes Gold, Pit, Wumpus and ClearGround are extensions of class GameItem.
 * The items should be positioned randomly over board.
 * There is at least one, and up to three pieces of gold (chosen randomly)
 * You need to show Gold when the game is being played
 * After randomly generate W, P, G in the beginning of the game, these objects are settled in position.
 */

public class Gold extends GameItem {
	
	public int goldScore;
	
	public Gold() {
		int goldScore = 0;	// initial goldscore is 0
	}

	public int[][] goldPosition() {
		/*
		 * using polymorphism and inheritance to define the position of Gold in
		 * setBoard()
		 */

		int[][] posGold = super.display(2);
		return posGold;
	}

	// this method will notify when the player has landed on gold
	public void sendOutcome() {
		System.out.println("Player landed in gold");
		increaseScore();
	}

	// increase the gold score
	
	public int increaseScore() {
		goldScore = goldScore + 1;
		System.out.println("Player landed in gold. Score is " + goldScore);
		
		return goldScore;
	}
}
