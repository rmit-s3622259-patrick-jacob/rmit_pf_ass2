package rmit_pf_ass2;

/**
 * @author Patrick Jacob, Sofia McKerrow & George Cassar From the assignment
 *         description: The World class only instantiates an object myGame of
 *         class Game, and calls myGame.runGame()
 */

public class World {

	public static void main(String args[]) {

		Game myGame = new Game();
		myGame.runGame();
	}
}
