package rmit_pf_ass2;

public class ClearGround extends GameItem {
	public int[][] clearGroundPosition() {

		/**
		 * @author Patrick Jacob, Sofia McKerrow & George Cassar using
		 *         polymorphism and inhertance to define the Position of first
		 *         ClearGround in setBoard()
		 */

		int[][] posClearGround = super.display(1);
		return posClearGround;
	}
}
