package rmit_pf_ass2;

/* From the assignment description:
 * You should write a Game class, which has a 2D array of size 4 x 4 of GameItem called 
 * board for implementing the Wumpus game described above.
 * Classes Gold, Pit, Wumpus and ClearGround are extensions of class GameItem. 
 * GameItem should provide a public method, display(), for use when displaying the 
 * board and a private instance variable for specifying the displayed character. Gold is 
 * displayed as 'g', a Pit as 'p', the Wumpus as 'W', clear ground as '.' . GameItem 
 * provides a constructor GameItem(char c) for specifying the displayed character.
 * It should not be possible to instantiate an object of GameItem.
 * GameItem display() method is used to display each game item.
 */

import java.util.Random;

<<<<<<< HEAD
public abstract class GameItem {

	private String[][] board;
	// static String X = "X";
	// static String O = "O";

	public GameItem() {
		// initialize instance variables
		board = new String[4][4];
	}

	public void display() {

		// need to get gold position and read out of the array
		// Gold positionGold = new Gold();
		// int [][] pg = positionGold.nextInt[][]();
		// if (pg[1])

		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					System.out.print("_");
				} else {
					System.out.print(board[i][j]);
				}
				if (j < 3) {
					System.out.print("|");
				} else {
					System.out.println();
				}
			}
=======
public class GameItem {
	
	/*private String[][] board; 
 //   static String X = "X";
 //   static String O = "O";    
    
    public GameItem()
    {
        // initialize instance variables
        board = new String[4][4]; 
  }
    
    public void setBoard(){
		  GameItem test = new GameItem();
			test.display();
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
		}
<<<<<<< HEAD

	}
=======
    
public void display() {
 
//need to get gold position and read out of the array
//Gold positionGold = new Gold(); 
//int [][] pg = positionGold.nextInt[][](); 
//if (pg[1])
	
System.out.println();
for (int i = 0; i < board.length; i++) {
 for (int j = 0; j < board[i].length; j++) {
     if (board[i][j] == null) {
         System.out.print("_");
     } else {
         System.out.print(board[i][j]);
     }
     if (j < 3) {
         System.out.print("|");
     } else {
         System.out.println();
     } 
  }
}


}*/
>>>>>>> branch 'master' of https://github.com/rmit-s3622259-patrick-jacob/rmit_pf_ass2.git
}
