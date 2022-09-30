package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Game;
import nz.ac.vuw.ecs.swen225.gp22.app.KeyStroke;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;

/**
 * Class that runs the two fuzz tests for the Chips&Chaps game. Generates random
 * inputs and logs any crashes onto git.
 * 
 * @author anniecho 300575457
 * 
 */
public class FuzzTest {

	/**
	 * Test for level one
	 */
	@Test
	public void FuzzTest1() {
		
		System.out.println("FuzzTest 1 is called.");
		//Main.main(null);
		App app = new App();
		
		while(App.initializeDone == false) System.out.print(""); // While the main menu is booting, wait
		System.out.println("Initializing of window done. Timed fuzz test begins now.");
		
		assertTimeout(Duration.ofSeconds(60), () -> {
			// Timer to stop while loop
			long timer = System.currentTimeMillis() + 60 * 1000; 
			
			while(true) {
				// Constantly try to start the code below
				System.out.print("");
			    while(App.fuzzStarted) {
			    	// Begins once the start button is pressed
			    	int direction = getRandomDirection();
			    	KeyStroke.keyPressed(direction);
			        if(System.currentTimeMillis() > timer) break; // Breaks test loop
			    }
			    if(System.currentTimeMillis() > timer) {
			    	App.fuzzStarted = false;
			    	App.initializeDone = false;
			    	System.out.println("fuzzStarted = " + App.fuzzStarted + "    initializeDone = " + App.initializeDone);
			    	break; // Breaks game loop
			    }
			}
		});
	}
	


	
	
	/**
	 * Method that gets a random direction for Chap to move
	 * @return Keystroke constant, each representing a direction
	 */
	public int getRandomDirection() {
		Random r = new Random();
		int random = 1 + r.nextInt(4); // Generates a random number 1-4
		switch(random) {
			case 1:
		    	//System.out.println("Fuzz.java: random direction is UP.");
		    	return KeyEvent.VK_W; // Keystroke constant that represents W
		    case 2:
		    	//System.out.println("Fuzz.java: random direction is DOWN.");
		    	return KeyEvent.VK_S; // Keystroke constant that represents S
		    case 3:
		    	//System.out.println("Fuzz.java: random direction is LEFT.");
		    	return KeyEvent.VK_A; // Keystroke constant that represents A
		    case 4:
		    	//System.out.println("Fuzz.java: random direction is RIGHT.");
		    	return KeyEvent.VK_D; // Keystroke constant that represents D
		    default:
		    	System.out.println("Random number failed");
		    	return 0; // Should never reach this point
		}
	}
		


	/**
	 * Test for level two
	 */
	@Test
	public static void FuzzTest2() {}

}
