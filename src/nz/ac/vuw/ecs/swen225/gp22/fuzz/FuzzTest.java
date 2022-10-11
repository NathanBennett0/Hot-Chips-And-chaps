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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

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
	private HashMap<String, Integer>[][] strategyNew = makeStrategyBoardNew();
	
	
	
	
	private int currentX, currentY;
	private int prevX, prevY;
	private String prevDirection = "";

	
	/**
	 * Test for level one
	 */
	@Test
	public void FuzzTest1() {
		
		System.out.println("FuzzTest 1 is called.");
		makeStrategyBoardNew();
		App app = new App();
		
		while(!app.initializeDone()) System.out.print(""); // While the main menu is booting, wait
		System.out.println("Initializing of window done. Timed fuzz test begins now.");
		
		
		long timer = System.currentTimeMillis() + 60 * 1000; // Timer to stop while loop
		app.levelOne(); // This will change fuzzStarted to true and load L1
		assert app.fuzzStarted() == true;
		

		while(app.fuzzStarted()) {
			
			// Get the values needed to find next direction
			pause(app,1000);
			
			Controller c = app.getGame().phase().controller();
			Maze m = app.getGame().phase().maze();
			assert c != null && m != null;
			
			
			// Use intelligence to find the next direction
			int direction = pickDirection();
			c.keyPressed(direction);
			
			
			// Check the location of the chap
			updateLocation(m);
			if(prevX == currentX && prevY == currentY) {
				// We just tried to walk into a wall
				HashMap<String, Integer> currentPos = strategyNew[currentX][currentY];
				currentPos.put(prevDirection, 1000);
			}
			System.out.println("Prev Location: " + prevX + "," + prevY);
			System.out.println("Location: " + currentX + "," + currentY);
			//assert grid[currentX][currentY] instanceof Chap;
			
			
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
	 * Make HASHMAP strategy board
	 * Each tile represents a tile on the board
	 * The hashmap has 4 values, key as a direction, val as number of times explored
	 * u = up, d = down, l = left, r = right
	 */
	public HashMap<String, Integer>[][] makeStrategyBoardNew() {
		
		HashMap<String,Integer>[][] temp = new HashMap[22][22];
		
		for(int i = 0; i < 22; i++) {
			for(int j = 0; j < 22; j++) {
				temp[i][j] = new HashMap<String,Integer>();
				temp[i][j].put("u", 0);
				temp[i][j].put("d", 0);
				temp[i][j].put("l", 0);
				temp[i][j].put("r", 0);
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
	* THINGS I WANT TO DO
	* GO WHERE HAS NOT BEEN EXPLORED
	* IF THEY HAVE ALL BEEN EXPLORED, GO WhERE least explored!!!
	* DO NOT MOVE BACK INTO OLD PLACE!!!!!
	*/
	public int pickDirection() {
		
		// Grab the current direction inside the strategy board
		HashMap<String, Integer> currentPos = strategyNew[currentX][currentY];
		
		// All the directions that I c
		ArrayList<String> directions = new ArrayList<>();
		directions.add("u");
		directions.add("d");
		directions.add("l");
		directions.add("r");
		
		// Keep trying to find a direction until found
		while (true) {
			
			Random r = new Random();
			int random = 1 + r.nextInt(6); // Every one in six moves pick something completely random
			if(random == 3) {
				System.out.println("PICKED A RANDOM DIRECTION!");
				return getRandomDirection();
			}
			
			Collections.shuffle(directions); // List of directions is random
			String direction = directions.get(0); // Grab a random direction
			
			
			// Otherwise, check that we do not go backwards
			// If we do restart the loop and try another direction
	    	if(direction.equals("u") && prevDirection.equals("d")) continue;
	    	if(direction.equals("d") && prevDirection.equals("u")) continue;
	    	if(direction.equals("l") && prevDirection.equals("r")) continue;
	    	if(direction.equals("r") && prevDirection.equals("l")) continue;

		   
	    	
		    // Otherwise go where we have least explored
		    // Randomly iterating (Map iterator is still somewhat ordered)
	
		    int numExplored = Integer.MAX_VALUE;
		    String dirExplored = direction;
		    
		    for(int i = 0; i < directions.size(); i++) {
		    	System.out.println("Direction: " + directions.get(i) + "   Times: " + currentPos.get(directions.get(i)));
		    	if(currentPos.get(directions.get(i)) <= numExplored) {
		    		numExplored = currentPos.get(directions.get(i));
		    		dirExplored = directions.get(i);
		    	}
		    }
		    
		    
		    System.out.println("Picked: " + dirExplored);
		    System.out.println();
		    System.out.println();
		    
		    
		    // Bump up its exploration and go that direction
		    currentPos.put(dirExplored, currentPos.get(dirExplored) + 1);
		    strategyNew[currentX][currentY] = currentPos;
		    prevX = currentX;
		    prevY = currentY;
			prevDirection = dirExplored;
			return generateKeycode(dirExplored);
		}
		
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
	public int generateKeycode(String dir) {
		Random r = new Random();
		int random = r.nextInt(2); // Random number 0 or 1
		switch(dir) {
			case "u":
				if(random == 0) return KeyEvent.VK_W;
				else return KeyEvent.VK_UP;
		    case "d":
		    	if(random == 0) return KeyEvent.VK_S; 
		    	else return KeyEvent.VK_DOWN;
		    case "l":
		    	if(random == 0) return KeyEvent.VK_A;
		    	else return KeyEvent.VK_LEFT;
		    case "r":
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
		HashMap<String, Integer> currentPos = strategyNew[currentX][currentY];
		switch(random) {
			case 1:
				currentPos.put("u", currentPos.get("u") + 1);
				prevDirection = "u";
			    prevX = currentX;
			    prevY = currentY;
				strategyNew[currentX][currentY] = currentPos;
		    	return KeyEvent.VK_W; // Constant representing W
		    case 2:
		    	currentPos.put("d", currentPos.get("d") + 1);
		    	prevDirection = "d";
			    prevX = currentX;
			    prevY = currentY;
		    	strategyNew[currentX][currentY] = currentPos;
		    	return KeyEvent.VK_S; // Constant representing S
		    case 3:
		    	currentPos.put("l", currentPos.get("l") + 1);
		    	prevDirection = "l";
			    prevX = currentX;
			    prevY = currentY;
		    	strategyNew[currentX][currentY] = currentPos;
		    	return KeyEvent.VK_A; // Constant representing A
		    case 4:
		    	currentPos.put("r", currentPos.get("r") + 1);
		    	prevDirection = "r";
			    prevX = currentX;
			    prevY = currentY;
		    	strategyNew[currentX][currentY] = currentPos;
		    	return KeyEvent.VK_D; // Constant representing D
		    default:
		    	System.out.println("Random number failed");
		    	return 0; // Should never reach this point
		}
	}

}
