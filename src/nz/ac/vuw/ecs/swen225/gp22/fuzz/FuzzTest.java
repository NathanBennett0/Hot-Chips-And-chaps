package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Game;
import nz.ac.vuw.ecs.swen225.gp22.app.KeyStroke;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

/**
 * Class that runs the two fuzz tests for the Chips&Chaps game. 
 * Generates random inputs.
 * Logs any crashes onto git.
 * 
 * @author anniecho 300575457
 * 
 */
public class FuzzTest {

	// Private variables for determining the next move
	private ArrayList<Integer>[][] strategy = makeStrategyBoard(); 
	private int currentX, currentY;

	
	/**
	 * Test for level one
	 */
	@Test
	public void FuzzTest1() {
		
		System.out.println("FuzzTest 1 is called.");
		makeStrategyBoard();
		App app = new App();
		
		while(!app.initializeDone()) System.out.print(""); // While the main menu is booting, wait
		System.out.println("Initializing of window done. Timed fuzz test begins now.");
		
		
		long timer = System.currentTimeMillis() + 10 * 1000; // Timer to stop while loop
		app.levelOne(); // This will change fuzzStarted to true and load L1
		

		while(app.fuzzStarted()) {
			
			// Get the values needed to find next direction
			pause(app,500);
			Controller c = app.getGame().phase().controller();
			Maze m = app.getGame().phase().maze();
			
			
			// Check the location of the chap
			updateLocation(m);
			Tile[][] grid = m.getGrid();
			System.out.println("Location: " + currentX + "," + currentY);
			assert grid[currentX][currentY] instanceof Chap;
			
			
			// Use intelligence to find the next direction
			int direction = pickDirection();
			
			
			// Execute the next direction
			c.keyPressed(direction);
			
			
			// Checks if the timer has exceeded
			if(checkTimer(timer)) break;
		}

	}
	
	
	/**
	 * Test for level two
	 */
	@Test
	public void FuzzTest2() { }
	
	
	/*----- HELPER METHODS ------------------------------------------------------------- */
	
	
	/**
	 * Make strategy board
	 * Each tile represents a tile on the board
	 * The arraylist has 4 indexes, each init 0
	 * 0 = up, 1 = down, 2 = left, 3 = right
	 */
	public ArrayList<Integer>[][] makeStrategyBoard() {
		ArrayList<Integer>[][] temp = new ArrayList[16][16];
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				temp[i][j] = new ArrayList<Integer>();
				for(int k = 0; k < 4; k++) {
					temp[i][j].add(0);
					// Stores the number of times a direction has been taken on that tile
				}
			}
		}
		return temp;	
	}
	
	
	/**
	 * Locates the chap on the maze and updates the current position
	 * @param m Maze object found from App.java
	 */
	public void updateLocation(Maze m) {
		currentX = m.player.l.getX();
		currentY = m.player.l.getY();
	}
	
	
	/**
	* Beginning intelligence
	*/
	public int pickDirection() {
		ArrayList<Integer> currentPos = strategy[currentX][currentY];
		int least = Collections.min(currentPos); // The least moves
		
		// The direction that has the lowest moves, direction is an index
		int direction = currentPos.indexOf(least);
		currentPos.set(direction, least + 1);
		return generateKeycode(direction);
	}
	
	
	/**
	 * Synchronizes the threads so the game can wait if when called
	 * @param a App instance running the game thread
	 * @param time Number of milliseconds to pause for
	 */
	public void pause(App a, int time) {
    	synchronized (a) {
	        try { 
	        	a.wait(time); 
	        } catch (InterruptedException e) { 
	        	e.printStackTrace(); 
	        }
    	}
	}	
	
	
	/**
	 * Checks the timer of the running test
	 * @param timer The end of the running test
	 * @return true if time is up, false otherwise
	 */
	public boolean checkTimer(long timer) {
		if(System.currentTimeMillis() > timer) return true;
		return false;
	}

	
	
	/**
	 * Method that generates the keycode based on the index of an Arraylist
	 * @return Keystroke constant, each representing a direction
	 * 0 = up, 1 = down, 2 = left, 3 = right
	 * Can return either WASD or arrow values
	 */
	public int generateKeycode(int index) {
		Random r = new Random();
		int random = r.nextInt(2); // Random number 0 or 1
		switch(index) {
			case 0:
				if(random == 0) return KeyEvent.VK_W;
				else return KeyEvent.VK_UP;
		    case 1:
		    	if(random == 0) return KeyEvent.VK_S; 
		    	else return KeyEvent.VK_DOWN;
		    case 2:
		    	if(random == 0) return KeyEvent.VK_A;
		    	else return KeyEvent.VK_LEFT;
		    case 3:
		    	if(random == 0) return KeyEvent.VK_D; 
		    	else return KeyEvent.VK_DOWN;
		    default:
		    	System.out.println("Random number failed");
		    	return 0; // Should never reach this point
		}
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
		    	return KeyEvent.VK_W; // Constant representing W
		    case 2:
		    	return KeyEvent.VK_S; // Constant representing S
		    case 3:
		    	return KeyEvent.VK_A; // Constant representing A
		    case 4:
		    	return KeyEvent.VK_D; // Constant representing D
		    default:
		    	System.out.println("Random number failed");
		    	return 0; // Should never reach this point
		}
	}

}

