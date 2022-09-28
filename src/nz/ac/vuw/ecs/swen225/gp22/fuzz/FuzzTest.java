package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Game;
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
	 * Temporary testing for asserting time out To be removed once implemented fuzz
	 * testing
	 */
	@Test
	public void AssertTimeoutTest() {
		ArrayList<Integer> myInts = new ArrayList<>();
		for (int i = 0; i < 100000; i++) myInts.add(i);
		assertTimeout(Duration.ofMillis(1), () -> {
			for (int i = 0; i < myInts.size(); i++) {
				System.out.println(myInts.get(i));
				// Must execute this code in the time frame provided
			}
		});
	}
	
	/**
	 * Test for level one
	 */
	@Test
	public void FuzzTest1() {
		
		assertTimeout(Duration.ofSeconds(10), () -> {
			long timer = System.currentTimeMillis() + 10 * 1000; // Timer to stop while loop
			Main.main(null); // Start the main before the testing loop
			
			boolean started = false;

			while(true) {
			    while(started) {	
			    	
			    	getRandomDirection();
			        if(System.currentTimeMillis() > timer) break; // Breaks test loop
			    }
			    if(System.currentTimeMillis() > timer) break; // Breaks game loop
			}
		});
	}
	
	
	/**
	 * Method that gets a random direction for Chap to move
	 * @return int 1-4, each representing a direction
	 */
	public int getRandomDirection() {
		Random r = new Random();
		int random = 1 + r.nextInt(4);
		switch(random) {
			case 1:
		    	System.out.println("Fuzz.java: random direction is UP.");
		    case 2:
		    	System.out.println("Fuzz.java: random direction is DOWN.");
		    case 3:
		    	System.out.println("Fuzz.java: random direction is LEFT.");
		    case 4:
		    	System.out.println("Fuzz.java: random direction is RIGHT.");
		}
		return random;
	}
		


	/**
	 * Test for level two
	 */
	@Test
	public static void FuzzTest2() {}

}
