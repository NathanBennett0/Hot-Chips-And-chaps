package gp22.fuzz;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.Test;

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
	public void test1() {

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

	}

	/**
	 * Test for level two
	 */
	@Test
	public static void test2() {

	}

}
