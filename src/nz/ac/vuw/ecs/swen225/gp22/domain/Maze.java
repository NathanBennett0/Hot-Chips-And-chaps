package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze { 
	//fields
	private final Tile[][] grid; //= new Tile[22][22];
	public Level lv; 
	public Chap player;
	public int numItems;
	
	public Maze(Level lv, int xx, int yy) throws IOException{
		if(lv == null || xx <= 0 || yy <= 0){
			throw new IOException("Maze cannot be created"); //pre 
		}
		this.lv = lv;
		player = lv.getChap();
		numItems =lv.getInventory().size();
		grid = new Tile[xx][yy];
		for(int x = 0; x < xx; x++) {
    		for(int y = 0; y < yy; y++) {
    			grid[x][y] = new Free(new Location(x,y));
    		}
    	}
		for(Tile t : lv.getAllTiles()) {
			int x = t.getLocation().getX();
			int y = t.getLocation().getY();
			grid[x][y] = t;
		}
		setChapLoc(lv.getChap().getLocation());
		//grid[lv.getChap().getLocation().getX()][lv.getChap().getLocation().getX()] = lv.getChap();
	}
    
	/*
	 * removes a tile on the grid at the given location
	 */
    public void removeTile(Location l) throws IOException {
		if(grid[l.getX()][l.getY()] == null){
			throw new IOException("Cannot remove null tile"); //pre 
		}
        if(grid[l.getX()][l.getY()] instanceof Key) {
            lv.removeKeyTile((Key)grid[l.getX()][l.getY()]);
        } else if(grid[l.getX()][l.getY()] instanceof Locked) {
            lv.removeLockedTile((Locked)grid[l.getX()][l.getY()]);
        } else if(grid[l.getX()][l.getY()] instanceof Treasure) {
            lv.removeTreasureTile((Treasure)grid[l.getX()][l.getY()]);
        }
        grid[l.getX()][l.getY()] = new Free(l);
		safeGrid(); //post 
    }

	/**
	 * helper method for post and pre condition checks on the grid to prevent null errors 
	 */
	public void safeGrid() throws IOException { //helper
		//System.out.println("maze filled out");
		//check for nulls in 2d array
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if(grid[row][col] == null){
					throw new IOException("found null"); 
				}
			}
		}
	}

	/*
	 * checks if the players next action is allowed
	 */
	public boolean allowAction(Location nextLoc) { //dynamic 
		//get location of the next tile
		Tile next = grid[nextLoc.getX()][nextLoc.getY()];
		System.out.println("This shitstick: "+next.toString()+", "+next.CanWalkOn(player));
		return next.CanWalkOn(player);
	}

	/*
	 * Counts the current number of treasures on the grid to allow for exit lock to be unlocked 
	 */
	public int numOfTreasures() throws IOException { 
		safeGrid(); //pre 
		int count = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if(grid[row][col] instanceof Treasure){
					count = count + 1;
				}
			}
		}
		return count;
	}

	//getters and setters
	/*
	 * returns grid 
	 */
	public Tile[][] getGrid() {
		return grid;
	}
	/*
	 * returns the tile at the given location 
	 */
	public Tile getTile(Location l){
		return grid[l.getX()][l.getY()];
	}
	/*
	 * set new location for a box when chap moves it 
	 */
//	public void setBoxLocation(Location l) throws IOException{
//		if(!(grid[l.getX()][l.getY()] instanceof Free)){
//			throw new IOException("Box cannot be Moved"); //pre
//		}
//		grid[l.getX()][l.getX()] = new Box(l);
//		assert(grid[l.getX()][l.getX()] instanceof Box);
//	}
	
	/*
	 * remove old location of chap then makes a new one for him to be in
	 */
    public void setChapLoc(Location l) throws IOException {
		if(!(grid[l.getX()][l.getY()] instanceof Free)){
			throw new IOException("Chap cannot be relocated"); //pre
		}
        grid[player.getLocation().getX()][player.getLocation().getY()] = new Free(player.getLocation()); 
        grid[l.getX()][l.getY()] = player;
		//assert((grid[player.getLocation().getX()][player.getLocation().getY()] instanceof Free)&&(grid[l.getX()][l.getY()]  instanceof Chap)); //post
    }

	/*
	 * returns level the game is in 
	 */
	public Level getLevel(){
		return this.lv;
	}

	/*
	 * returns chap 
	 */
	public Chap getChap(){
		return this.player;
	}

	/*
	 * Shows layout of grid
	 */
	@Override
	public String toString() { 
	    String r = "";
	    for (int row = 0; row < grid.length; row++) {
	        r += row + "|";
            for (int col = 0; col < grid[row].length; col++) {
                Tile t = grid[row][col];
                if(t != null) {
                    r += t + "|";
                } else {
                    r += "_|";
                }
            }
            r += "\n";
	    }
	    return r + "  a b c d e f g h";
	}

}
