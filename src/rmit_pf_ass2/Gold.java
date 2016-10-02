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

<<<<<<< HEAD
=======

<<<<<<< HEAD
public class Gold extends GameItem{
/*	
	int randRow;
	int randColumn;
	do {
	  randRow = random.nextInt(ROWS);
	  randColumn = random.nextInt(COLUMNS);
	} while (grid[randRow][randColumn] != null);
	grid[randRow][randColumn] = "X";
*/
	//public static void main (String args[]){
=======
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
public class Gold extends GameItem {

	public int goldScore = 0;
<<<<<<< HEAD

=======
	
	/*
	 * int randRow; int randColumn; do { randRow = random.nextInt(ROWS);
	 * randColumn = random.nextInt(COLUMNS); } while (grid[randRow][randColumn]
	 * != null); grid[randRow][randColumn] = "X";
	 */
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
	public int[][] getGoldposition() {
		int ag = 0; // position gold as array horizontal

		Random amountGoldGenerator = new Random();
<<<<<<< HEAD
		// int Low = 1;
		// int High = 3;
		// ag = amountGoldGenerator.nextInt(High-Low) + Low;
		ag = amountGoldGenerator.nextInt(3);
=======
<<<<<<< HEAD
	//	int Low = 1;
	//	int High = 3;
	//	ag = amountGoldGenerator.nextInt(High-Low) + Low;
		ag = amountGoldGenerator.nextInt(3); 
		
	int[][] pg = new int[ag][2];
	int i;
	int j;
	int row;
	int col;
	//Random Generator to get a random number between 0 and 3 using the Random Method
	//do {
	for(i=0;i<ag;i++){
		 for(j=0;j<2;j++){
			  Random GoldGenerator = new Random();
		      pg[i][j] = GoldGenerator.nextInt(3);
		  //    System.out.print(pg[i][j]);
		      
	//	for (i=0; i < ag; i++ ){
		//	for (int j=0; j<2; j++) {
		//	Random GoldGenerator = new Random();
		//	row = GoldGenerator.nextInt(3); 	
		//	col = GoldGenerator.nextInt(3); 
		//	pg[i][j] = [row][col];
=======
		ag = amountGoldGenerator.nextInt(2);
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git

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
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2
			}
		}
<<<<<<< HEAD
		System.out.println(ag);

		for (int r = 0; r < pg.length; r++) {
			System.out.println();
			for (int c = 0; c < pg.length; c++) {
				System.out.print(pg[r][c]);
			}
			// while ();

		}
=======
<<<<<<< HEAD
		System.out.println(ag);
		
		for (int r = 0; r < pg.length; r++) {
			System.out.println();
			   for (int c = 0; c < pg.length; c++) {
				   System.out.print(pg[r][c]);
				   }
	//while ();
		return pg;
		}
=======
		// }
		// while ();
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
		return pg;
	}

	// this method when invoked will notify the player has landed on gold

	public void sendOutcome() {
		System.out.println("Player landed in gold");
		increaseScore();
	}
<<<<<<< HEAD

	public void increaseScore() {
		goldScore = goldScore++;
		System.out.println("Player landed in gold. Score is" + goldScore);
=======
	
	public void increaseScore(){
		goldScore= goldScore++;
		System.out.println("Player landed in gold. Score is" +goldScore);
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
	}
}
