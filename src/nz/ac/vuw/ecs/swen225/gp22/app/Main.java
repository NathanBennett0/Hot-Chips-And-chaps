package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.SwingUtilities;

/**
 * Main class for Chap's Challenge
 * 
 * @author Naomi Parte
 * 
 */
public class Main {
	/**
	 * Main method. Runs the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable startGame = ()->{App.getInstance();};
		SwingUtilities.invokeLater(startGame);
	}

}