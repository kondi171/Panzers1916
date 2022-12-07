package dev.panzers1916;

/** Represents a main class
 * @author Konrad Nowak */

public class Launcher {
	/** main class which we set the instance of game object with default data
	 * <br> call a start method which we start the game
	 * @param args null */
	public static void main(String[] args){
		Game game = new Game("Panzers 1916", 1366,768);
		game.start();
	}
}


