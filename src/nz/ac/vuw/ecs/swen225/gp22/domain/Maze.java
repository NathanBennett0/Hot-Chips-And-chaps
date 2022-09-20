package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
	//fields
	public final Tile[][] grid = new Tile[16][16]; //Nath i think its u who sets this shit up?
	public ArrayList<Tile> tiles; //varies depending on the level
	public int numItems;
	public Chap player;
	/**
	 * lockloc is the location of the ExitLock
	 */
	private Location lockLoc;
	
	public Maze(ArrayList<Tile> t, int n) {
		this.tiles = t;
		this.numItems = n;
	}

	// getters and setters
	Tile[][] getGrid() {
		return grid;
	}

	/**
	 *method that checks if all the keys collected then the exitLock is replaced with free tile
	 */
	public void allKeyCollected(){
		assert player.getChest().size() == numItems;
		assert lockLoc != null;
		//change the lock tile to being a free tile
		grid[lockLoc.getX()][lockLoc.getY()] = new Free(lockLoc);
		//observer pattern??? notify observers the Lock is open this is to help yall other peeps
	}

	/**
	 * Nathan needs to call this to set up the maze
	 */
	public void onMazeCreated() throws IOException {
		//check for nulls in 2d array
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if(grid[row][col] == null){
					throw new IOException("found null");
				}
			}
		}
		//loop through 2d array to find exitlock and then assign location to field
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if(grid[row][col] instanceof ExitLock){
					lockLoc = grid[row][col].getLocation();
				}
			}
		}
		//check if field location is not null
		assert lockLoc != null;
	}

	//check if players action is allowed
	public boolean allowAction(Location nextLoc) {
		//get location of the next tile 
		Tile next = grid[nextLoc.getX()][nextLoc.getY()];
		return next.CanWalkOn(player);
	}

}
