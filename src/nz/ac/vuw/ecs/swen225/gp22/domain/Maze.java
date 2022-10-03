package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze { //SUS Testing Git Shit
	//fields
	public final Tile[][] grid = new Tile[16][16];
	public Level lv; //need to give me information using getters Nathan
	public Chap player;
	public int numItems;
	//public List<Tile> tiles = lv.getTiles(); //varies depending on the level
	
	/**
	 * lockloc is the location of the ExitLock
	 */
	private Location lockLoc;
	
	public Maze(Level lv) {
		this.lv = lv;
		player = lv.getChap();
		numItems =lv.getInventory().size();
		for(int x = 0; x < 16; x++) {
    		for(int y = 0; y < 16; y++) {
    			grid[x][y] = new Free(new Location(x,y));
    		}
    	}
		for(Tile t : lv.getAllTiles()) {
			int x = t.getLocation().getX();
			int y = t.getLocation().getY();
			grid[x+7][y+7] = t;
		}
		grid[lv.getChap().getLocation().getX()][lv.getChap().getLocation().getX()] = lv.getChap();
	}

	// getters and setters
	public Tile[][] getGrid() {
		return grid;
	}
	
	//remove old location, make new one. 
    public void setChapLoc(Location l) {
        grid[player.getLocation().getX()][player.getLocation().getY()] = new Free(player.getLocation()); 
        grid[l.getX()][l.getY()] = player;
    }
    
    public void removeTile(Location l) {
        grid[l.getX()][l.getY()] = new Free(l);
        
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
		System.out.println("maze filled out");
		//check for nulls in 2d array
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if(grid[row][col] == null){
					throw new IOException("found null"); //pre
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
		assert lockLoc != null; //post
	}

	//check if players action is allowed
	public boolean allowAction(Location nextLoc) { //Dyyynamic Dispatch
		//get location of the next tile
		System.out.println("next action being checked");
		Tile next = grid[nextLoc.getX()][nextLoc.getY()];
		return next.CanWalkOn(player);
	}

}
