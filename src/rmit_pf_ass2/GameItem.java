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

public abstract class GameItem {

	// abstract method for getting the position of any of the elements
	// shall be used in the elements classes
	public int[][] display(int times) {
	
		int[][] position = new int[times][2];
		int i, j; 
		
		for(i=0; i < position.length; i++){
			 for(j=0; j<position[i].length; j++){
				  Random GoldGenerator = new Random();
			      position[i][j] = GoldGenerator.nextInt(3);
			 	}
			 }
		return position;
	}
}
