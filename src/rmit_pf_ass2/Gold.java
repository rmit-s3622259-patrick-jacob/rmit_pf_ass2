package rmit_pf_ass2;

import java.util.*;

public class Gold extends GameItem {
	 
	public int goldScore;
	 
	public Gold() {
		int goldScore = 0;	// initial goldscore is 0
	}

	public int[][] goldPosition() {
		/* using polymorphism and inheritance to define the position of Gold in
		 * setBoard() */

		int[][] posGold = super.display(1);	// get one array line (element) for one coordinate
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
