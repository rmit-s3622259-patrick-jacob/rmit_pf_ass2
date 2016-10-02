package rmit_pf_ass2;

public class Wumpus extends GameItem {

	public int[][] wumpusPosition() {
		/* using polymorphism and inhertance to 
		 * define the positon of Wumpus in setBoard() */
		int[][] posWumpus = super.display(2);
		return posWumpus;
	}
}
