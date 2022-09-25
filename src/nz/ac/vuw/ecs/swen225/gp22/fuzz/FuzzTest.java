package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import nz.ac.vuw.ecs.swen225.gp22.app.Game;

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
		for (int i = 0; i < 100000; i++) {
			myInts.add(i);
		}
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
		
		assertTimeout(Duration.ofSeconds(5), () -> {
			
			// Creates timer to stop while loop
			// Assert timeout will always wait for loop to execute
			long start = System.currentTimeMillis();
			long finish = start + 5 * 1000; 
			
			
			// Declare variables before the testing loop
			Random r = new Random();
			int random = 1 + r.nextInt(6);
			
			// Begin testing loop
		    while(true) {
		    	System.out.println(random); 
		        if(System.currentTimeMillis() > finish) {
		        	break; // Check if time has run out
		        }
		    }
		});
	}
			

	// Idea for fuzz testing
	//
	// Load a game level
	// Array of all of the possible moves
	// (Up, down, left, right) Keys and doors are used automatically
	// Randomly generate 1-4 to pick what way to move
	// Store in an Arraylist with count of which moves have been done
	// Randomly call a method inside the app module
	//
	// If chap is in current position
	// Store in a hashmap of (Position, Direction)
	// If one of the directions haven't been explored yet, then preferably pick that
	//
	// If error or exception is detected, then log to git
	// Time out

	// Assertions to make
	// If the next block is an entity, check that move was applied properly
	// e.g. did not move if the next block is wall, did not move if next block is a
	// locked door
	// did move if the door was locked (check that door is no longer present)
	// if the next block is a key, check that key is no longer present


	/**
	 * Test for level two
	 */
	@Test
	public static void FuzzTest2() {

	}

}
