package rmit_pf_ass2;

public class Wumpus extends GameItem {

	/**
	 * @author Patrick Jacob, Sofia McKerrow & George Cassar
	 * @return using polymorphism and inheritance to define the position of
	 *         Wumpus in setBoard()
	 */
	public int[][] wumpusPosition() {

		int[][] posWumpus = super.display(2);
		return posWumpus;
	}
}
