package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import static org.junit.Assert.fail;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;

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
	private HashMap<String, Integer>[][] strategyL1 = makeStrategyBoardL1();
	private int[][] strategyL2 = makeStrategyBoardL2();
	private int currentX, currentY;
	private int prevX, prevY;
	private String prevDirection = "";
	private Random r = new Random();

	
	/**
	 * Test for level one
	 */
	@Test
	public void FuzzTest1() {
		
		System.out.println("FuzzTest 1 is called.");
		
		// App is a singleton class so ensure that it is not null
        // Ensure that after initialisation that the strategy board is not null
		App app = App.getInstance();
		assert strategyL1 != null && app != null;
		
		
		while(!app.initializeDone()) pause(app,200); // While the main menu is booting, wait
		System.out.println("Initializing of window done. Timed fuzz test begins now.");
		long timer = System.currentTimeMillis() + 60 * 1000; // Timer to stop while loop
		app.phaseOne(); // This automatically loads L1 inside the app GUI
		assert app.fuzzStarted() == true;
		intelligence(app, timer);

	}
	
	
	/**
	 * Test for level two
	 */
	@Test
	public void FuzzTest2() { 
	    
	    System.out.println("FuzzTest 2 is called.");
        App app = App.getInstance();
        assert strategyL2 != null && app != null;
        
        
        while(!app.initializeDone()) pause(app,200);
        System.out.println("Initializing of window done. Timed fuzz test begins now.");
        long timer = System.currentTimeMillis() + 60 * 1000; 
        app.phaseTwo(); // This automatically loads L2
        assert app.fuzzStarted() == true;
        intelligence(app, timer);
	    
	}
	
	
	/*----- HELPER METHODS ------------------------------------------------------------- */
	
	
	/**
	 * Make HASHMAP strategy board
	 * Each tile represents a tile on the board
	 * The hashmap has 4 values, key as a direction, val as number of times explored
	 * u = up, d = down, l = left, r = right
	 */
	public HashMap<String, Integer>[][] makeStrategyBoardL1() {
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
     * Make 2D ARRAY strategy board
     * Each tile represents a tile on the board
     * Inside the tile has the number of times it has been explored
     */
    public int[][] makeStrategyBoardL2() {
        int[][] temp = new int[22][22];
        for(int i = 0; i < 22; i++) {
            for(int j = 0; j < 22; j++) {
                temp[i][j] = 0;
            }
        }
        return temp;    
    }
    
	
	/**
	 * Intelligence used to run the tests in L1
	 * @param app
	 * @param timer
	 */
	public void intelligence(App app, long timer) {
	    
	    while(app.fuzzStarted()) {
            
	        
            pause(app,10);
            Controller c = app.getPhase().controller();
            assert c != null;
            
            
            // Check the location of the chap
            currentX = app.updateLocationX();
            currentY = app.updateLocationY();
            assert currentX < app.getPhase().maze().getGrid().length && currentX >= 0;
            assert currentY < app.getPhase().maze().getGrid().length && currentY >= 0;
            System.out.println("Location of the chap: " + currentX + "," + currentY);
            // Ensure that the updated X and Y given to us on the map is a valid location of the board
            
            
            // Use intelligence to find the next direction depending on which level
            if(checkTimer(timer) || !app.runningGame) break; // Checks if the timer has exceeded
            if(app.getPhase().maze().getLevel().getLevel() == 1) c.keyPressed(pickDirectionL1());
            else c.keyPressed(pickDirectionL2());

 
           
            // Use intelligence so the wall does not get tried again
            if(prevX == currentX && prevY == currentY) {
                int wallX = findAdjX(prevDirection); // Find x of the next tile over (where we wanted to go)
                int wallY = findAdjY(prevDirection); // Find y of the next tile over (where we wanted to go)
                
                if(app.isWall(wallX, wallY)) {
                    if(app.getPhase().maze().getLevel().getLevel() == 1) {
                        HashMap<String, Integer> position = strategyL1[currentX][currentY];
                        position.put(prevDirection, 1000); // So we do not try it again
                    }
                    else strategyL2[wallX][wallY] = 1000; // So we do not try it again
                }
            }
            
            if(Math.abs(System.currentTimeMillis() - timer) < 1 * 1000) app.saveGame();
            if(checkTimer(timer) || !app.runningGame ||
               app.updateLocationX() == -1 || app.updateLocationX() == -1) break; // Checks if the timer has exceeded
        }
	}
	
	
	/**
	 * Locates the X coordinate of an adjacent coordinate
	 * @param dir the direction we are moving in
	 * @return the new X coordinate of the adjacent tile
	 */
	public int findAdjX(String dir) {
		switch(dir) {
			case "u": return currentX;
			case "d": return currentX;
			case "l": return currentX - 1;
			case "r": return currentX + 1;
			default: return 0; // Should not be called
		}
	}
	
	
    /**
     * Locates the Y coordinate of an adjacent coordinate
     * @param dir the direction we are moving in
     * @return the new Y coordinate of the adjacent tile
     */
	public int findAdjY(String dir) {
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
	* Picks a direction for the chap to travel in
	* Chap cannot move backwards
	* Chap also moves in the direction least explored
	*/
	public int pickDirectionL1() {
		
		// Grab the current position inside the strategy board
		HashMap<String, Integer> currentPos = strategyL1[currentX][currentY];
		assert currentPos != null;
		
		
		// All the directions that I can explore (not backwards)
		ArrayList<String> directions = possibleDirections();
		Collections.shuffle(directions); // List of directions is random
	
    	
	    // Otherwise go where we have least explored
	    // Randomly iterating (Map iterator is still somewhat ordered, so used shuffle instead)
	    int numExplored = Integer.MAX_VALUE;
	    String dirExplored = directions.get(0);
	    
	    for(int i = 0; i < directions.size(); i++) {
	    	if(currentPos.get(directions.get(i)) <= numExplored) {
	    		numExplored = currentPos.get(directions.get(i));
	    		dirExplored = directions.get(i);
	    	}
	    }
	    
	    // Bump up that direction's exploration and go that direction
	    currentPos.put(dirExplored, currentPos.get(dirExplored) + 1);
	    strategyL1[currentX][currentY] = currentPos;
		prevX = currentX;
		prevY = currentY;
		prevDirection = dirExplored;
		return generateKeycode(dirExplored);
	}
	
	
   /**
    * Picks a direction for the chap to travel in
    * Chap also moves to the adjacent tile least explored
    */
    public int pickDirectionL2() {
        System.out.println("pickDirection2");
        
        ArrayList<String> adjPos = new ArrayList<>(List.of("u","d","l","r"));
        Collections.shuffle(adjPos);

        int minExplored = Integer.MAX_VALUE;
        String dirExplored = "";
        int newX = 0;
        int newY = 0;
        
        
        // Find what adjacent tile has been explored the least
        for(int i = 0; i < adjPos.size(); i++) {
            newX = findAdjX(adjPos.get(i));
            newY = findAdjY(adjPos.get(i));
            System.out.println(newX + " " + newY);
            if(strategyL2[newX][newY] < minExplored) {
                minExplored = strategyL2[newX][newY];
                dirExplored = adjPos.get(i);
            }
        }
        assert minExplored != Integer.MAX_VALUE && !(dirExplored.equals(""));

        
        // Bump up that direction's exploration and go that direction
        strategyL2[newX][newY] = minExplored + 1;
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
	            System.out.println("Failure to pause");
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
	    assert dir.equals("u") || dir.equals("d") || dir.equals("l") || dir.equals("r");
		int random = r.nextInt(2); // Random number 0 or 1
		assert random == 0 || random == 1;
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
		    	fail("Direction does not exist");
		    	return 0;
		}
	}

}
