package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.SwingUtilities;

/**
 * Main class for Chap's Challenge
 * 
 * @author Naomi Parte
 * 
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Main.java: main() called.");
		Runnable startGame = ()->{App.getInstance();};
		SwingUtilities.invokeLater(startGame);
	}

}