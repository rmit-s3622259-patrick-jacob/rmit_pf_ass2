package rmit_pf_ass2;

public class Pit extends GameItem {

	public int[][] pitPosition() {

		/*
		 * using polymorphism and inheritance to define the Position of first
		 * ClearGround in setBoard()
		 */
		int[][] posPit = super.display(3);
		return posPit;
	}

}
