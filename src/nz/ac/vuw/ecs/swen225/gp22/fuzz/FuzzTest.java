package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import javax.swing.SwingUtilities;
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

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Game;
import nz.ac.vuw.ecs.swen225.gp22.app.KeyStroke;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;

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
	private HashMap<String, Integer>[][] strategy = makeStrategyBoard();
	private int currentX, currentY;
	private int prevX, prevY;
	private String prevDirection = "";

	
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
		
		
		long timer = System.currentTimeMillis() + 60 * 1000; // Timer to stop while loop
		app.levelOne(); // This will change fuzzStarted to true and load L1
		assert app.fuzzStarted() == true;
		

		while(app.fuzzStarted()) {
			
			pause(app,95);
			Controller c = app.getGame().phase().controller();
			Maze m = app.getGame().phase().maze();
			assert c != null && m != null;
			
			
			// Use intelligence to find the next direction
			int direction = pickDirection();
			c.keyPressed(direction);
			
			
			// Check the location of the chap
			updateLocation(m);
			System.out.println("Prev Location: " + prevX + "," + prevY);
			System.out.println("Curr Location: " + currentX + "," + currentY);
			
			
			
			// Use intelligence so the wall does not get tried again
			if(prevX == currentX && prevY == currentY) {
				int wallX = findWallX(prevDirection); // Find x of the next tile over (where we wanted to go)
				int wallY = findWallY(prevDirection); // Find y of the next tile over (where we wanted to go)
				
				if(m.getGrid()[wallX][wallY] instanceof Wall) {
					System.out.println("WE JUST HIT A WALL at " + wallX + "," + wallY);
					HashMap<String, Integer> position = strategy[currentX][currentY];
					position.put(prevDirection, 1000); // So we do not try it again
				}
			}
			
			
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
	public HashMap<String, Integer>[][] makeStrategyBoard() {
		
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
	 * Locates the X coordinate of a wall
	 */
	public int findWallX(String dir) {
		switch(dir) {
			case "u": return currentX;
			case "d": return currentX;
			case "l": return currentX - 1;
			case "r": return currentX + 1;
			default: return 0; // Should not be called
		}
	}
	
	
	/**
	 * Locates the Y coordinate of a wall
	 */
	public int findWallY(String dir) {
		switch(dir) {
			case "u": return currentY - 1;
			case "d": return currentY + 1;
			case "l": return currentY;
			case "r": return currentY;
			default: return 0; // Should not be called
		}
	}
	
	
	/**
	 * The chap should not move back into the spot it just came from
	 * @return ArrayList of possible directions the chap can move
	 */
	public ArrayList<String> possibleDirections(){
		ArrayList<String> directions = new ArrayList<>();
		directions.add("u");
		directions.add("d");
		directions.add("l");
		directions.add("r");
		if(prevDirection.equals("u")) directions.remove("d");
		if(prevDirection.equals("d")) directions.remove("u");
		if(prevDirection.equals("l")) directions.remove("r");
		if(prevDirection.equals("r")) directions.remove("l");
		return directions;
	}
	
	
	
	/**
	* Beginning intelligence
	* THINGS I WANT TO DO
	* IF THEY HAVE ALL BEEN EXPLORED, GO WhERE least explored!!!
	* DO NOT MOVE BACK INTO OLD PLACE!!!!!
	*/
	public int pickDirection() {
		
		// Grab the current position inside the strategy board
		HashMap<String, Integer> currentPos = strategy[currentX][currentY];
		
		
		// All the directions that I can explore (not backwards)
		ArrayList<String> directions = possibleDirections();
		Collections.shuffle(directions); // List of directions is random
		String direction = directions.get(0); // Grab a random direction
	
    	
	    // Otherwise go where we have least explored
	    // Randomly iterating (Map iterator is still somewhat ordered, so used shuffle instead)
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
	    
	    
	    // Bump up that direction's exploration and go that direction
	    currentPos.put(dirExplored, currentPos.get(dirExplored) + 1);
	    strategy[currentX][currentY] = currentPos;
		prevX = currentX;
		prevY = currentY;
		prevDirection = dirExplored;
		return generateKeycode(dirExplored);
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
		    	else return KeyEvent.VK_RIGHT;
		    default:
		    	System.out.println("Random number failed");
		    	return 0; // Should never reach this point
		}
	}

}
