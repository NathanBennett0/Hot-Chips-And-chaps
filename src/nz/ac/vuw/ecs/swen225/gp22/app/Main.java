package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.SwingUtilities;

/**
 * Main class for Chap's Challenge
 * 
 * @author Naomi Parte 300562058
 * 
 */
public class Main {
	/**
	 * Main method. Runs the game.
	 *
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(App::getInstance);
	}

}