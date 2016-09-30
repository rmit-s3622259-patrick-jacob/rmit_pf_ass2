package rmit_pf_ass2;

import java.util.*;

public class Gold extends GameItem {

	public int[][] getGoldPosition() {
		int ag = 0; // position gold as array horizontal

		Random amountGoldGenerator = new Random();
		ag = amountGoldGenerator.nextInt(2);

		int[][] pg = new int[ag][2];
		int i;
		// Random Generator to get a random number between 0 and 3 using the
		// Random Method
		// do {
		for (i = 0; i < ag; i++) {
			for (int j = 0; j < 2; j++) {
				Random GoldGenerator = new Random();
				pg[i][j] = GoldGenerator.nextInt(3);
			}
		}
		// }
		// while ();
		return pg;
	}
}
