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
	
	public int goldScore = 0;
		public int[][] getGoldposition() {
		int ag = 0; // position gold as array horizontal

		Random amountGoldGenerator = new Random();
	//	int Low = 1;
	//	int High = 3;
	//	ag = amountGoldGenerator.nextInt(High-Low) + Low;
		ag = amountGoldGenerator.nextInt(3); 
		
	int[][] pg = new int[ag][2];
	int i;
	int j;
	int row;
	int col;
		// Random Generator to get a random number between 0 and 3 using the
		// Random Method
		// do {
		for (i = 0; i < ag; i++) {
			for (j = 0; j < 2; j++) {
				Random GoldGenerator = new Random();
				pg[i][j] = GoldGenerator.nextInt(3);
			}
		}
		System.out.println(ag);
		
		for (int r = 0; r < pg.length; r++) {
			System.out.println();
			   for (int c = 0; c < pg.length; c++) {
				   System.out.print(pg[r][c]);
				   }
	//while ();
		
	}
		return pg;
 }

	// this method when invoked will notify the player has landed on gold

	public void sendOutcome() {
		System.out.println("Player landed in gold");
		increaseScore();
	}
	
	public void increaseScore(){
		goldScore= goldScore++;
		System.out.println("Player landed in gold. Score is" +goldScore);
	}
}
