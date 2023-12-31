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
	public InfoField info;
	public Location infoLoc;
	
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
		lv.getAllTiles().stream().forEach(t -> {
			int x = t.getLocation().getX();
			int y = t.getLocation().getY();
			grid[x][y] = t;
		});
		this.info = lv.getInfoField();
		this.infoLoc = info.getLocation();
		setChapLoc(lv.getChap().getLocation());
		setActorLocation(lv.getActor().getLocation(), new Location(0,0));
		grid[lv.getInfoField().getLocation().getX()][lv.getInfoField().getLocation().getY()] = lv.getInfoField();
	}
    
	/*
	 * removes a tile on the grid at the given location
	 * @param Location 
	 */
    public void removeTile(Location l){ //setTile too?
		assert(grid[l.getX()][l.getY()] != null);
        if(grid[l.getX()][l.getY()] instanceof Key) {
            lv.removeKeyTile((Key)grid[l.getX()][l.getY()]);
        } else if(grid[l.getX()][l.getY()] instanceof Locked) {
            lv.removeLockedTile((Locked)grid[l.getX()][l.getY()]);
        } else if(grid[l.getX()][l.getY()] instanceof Treasure) {
            lv.removeTreasureTile((Treasure)grid[l.getX()][l.getY()]);
        } else if(grid[l.getX()][l.getY()] instanceof InfoField){
			grid[l.getX()][l.getY()] = info;
		}
        grid[l.getX()][l.getY()] = new Free(l);
		safeGrid(); //post 
    } 

	/**
	 * helper method for post and pre condition checks on the grid to prevent null errors 
	 */
	public void safeGrid(){ //helper
		//System.out.println("maze filled out");
		//check for nulls in 2d array
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				assert(grid[row][col] != null);
			}
		}
	}

	/*
	 * checks if the players next action is allowed
	 * @param Location 
	 * @return boolean 
	 */
	public boolean allowAction(Location nextLoc) { //dynamic 
		//get location of the next tile
		Tile next = grid[nextLoc.getX()][nextLoc.getY()];
		//System.out.println("This shitstick: "+next.toString()+", "+next.CanWalkOn(player));
		return next.CanWalkOn(player);
	}

	/*
	 * Counts the current number of treasures on the grid to allow for exit lock to be unlocked 
	 * @return int
	 */
	public int numOfTreasures() { 
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
	 * @return Tile[][]
	 */
	public Tile[][] getGrid() {
		return grid;
	}
	
	/*
	 * remove old location of chap then makes a new one for him to be in
	 * @param Location 
	 */
    public void setChapLoc(Location l) {
		assert((grid[l.getX()][l.getY()] instanceof Free) || (grid[l.getX()][l.getY()] instanceof InfoField) || (grid[l.getX()][l.getY()] instanceof Water));
        if(grid[l.getX()][l.getY()] instanceof Water){
            this.player.changeState(new DeadState());
        }
        grid[player.getLocation().getX()][player.getLocation().getY()] = new Free(player.getLocation()); 
		if(player.getLocation().getX() == infoLoc.getX() && player.getLocation().getY() == infoLoc.getY()) {
            grid[infoLoc.getX()][infoLoc.getY()] = info;
        }
        grid[l.getX()][l.getY()] = player;
		//assert((grid[player.getLocation().getX()][player.getLocation().getY()] instanceof Free)&&(grid[l.getX()][l.getY()]  instanceof Chap)); //post
    }

	/*
	 * sets the actors new location 
	 * @param Location 
	 * @param Location 
	 */
	public void setActorLocation(Location l, Location old) {
		grid[old.getX()][old.getY()] = new Free(old);
		grid[l.getX()][l.getY()] = lv.getActor();
	}

	/*
	 * returns level the game is in 
	 * @return 
	 */
	public Level getLevel(){
		return this.lv;
	}

	/*
	 * returns chap 
	 * @return Chap
	 */
	public Chap getChap(){
		return this.player;
	}

	/*
	 * returns the infofield of the maze
	 * return InfoField 
	 */
	public InfoField getInfoField(){
        return this.info;
    }

}

