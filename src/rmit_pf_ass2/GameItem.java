package rmit_pf_ass2;

import java.util.Random;

public abstract class GameItem {

	// abstract method for getting the position of any of the elements
	// shall be used in the elements classes 
	public int[][] display(int times) {

		// setting the size of array on size specified from calling the method
		int[][] position = new int[2][times];
		int i, j;
		//loop to fill position array with random number that will be used as board positions
		for (i = 0; i < position.length; i++) {
			for (j = 0; j < position[i].length; j++) {
				Random randomGenerator = new Random(); 
				//randomly generated position number between 0 - 4
				position[i][j] = randomGenerator.nextInt(4);
		
			}
		}
		// returning the array position
		return position;
	} 
 
}
