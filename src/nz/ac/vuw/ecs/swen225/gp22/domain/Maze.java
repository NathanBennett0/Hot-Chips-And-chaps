package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Maze {
	//fields
	public final Tile[][] grid = new Tile[16][16]; //16 set for now 
	public ArrayList<Tile> tiles; //varies depending on the level
	public int numItems; //needed?
	public Chap player;
	//level field 
	
	public Maze(ArrayList<Tile> t, int n) {
		this.tiles = t;
		this.numItems = n;
	}
	
	//getters and setters
	Tile[][] getGrid(){
		return grid;
	}
	
	//get tile image returns which image from directory  
	private BufferedImage getTileImage(Tile t) { 
		if(t instanceof Wall) {
			//image to be returned 
		} else if(t instanceof Key) {
			//image to be returned 
		} else if(t instanceof Locked) {
			//image to be returned 
		} else if(t instanceof InfoField) {
			//image to be returned 
		} else if(t instanceof Treasure) {
			//image to be returned 
		} else if(t instanceof ExitLock) {
			//image to be returned 
		} else if(t instanceof Exit) {
			//image to be returned 
		} else if(t instanceof Free){
			//image to be returned 
		} 
		return null;
	}
	
	//object interaction:
	//check if players action is allowed
	public boolean allowAction(Location nextLoc) { //state pattern yet to be implemented!
		//get location of the next tile 
		Tile next = grid[nextLoc.getX()][nextLoc.getY()];
		//check if the tile instance is pickable item like a key or treasure if it is add to chest
		if(next instanceof Key) {
			Key k = (Key)next;
			if(!k.isCollected()) {
				player.addToChest(k);
				return true;
			}
			return false;
		} else if(next instanceof Treasure) {
			Treasure t = (Treasure)next;
			if(!t.isCollected()) {
				player.addToChest(t);
				return true;
			}
			return false;
		}
		//check if the tile instance is a Locked and if so then look through the chest for the key if found then unlock
		if(next instanceof Locked) {
			for(Tile t : player.getChest()) {
				if(t instanceof Key) {
					Key k = (Key)t;
					Locked loc = (Locked)next;
					if(k.getColor() == loc.getColor()) {
						return true;
					}
					return false;
				}
			}
			return false;
		}
		//check if the tile instance is a ExitLock and if so then check if the chest is full
		if(next instanceof ExitLock) {
			if(player.getChest().size() == numItems) {
				return true;
			}
			return false;
		}
		//check if the tile instance is a wall and if so return false instantly 
		if(next instanceof Wall) {
			return false;
		}
		//still need exit and infofeild
		if(next instanceof Exit) {
			//move to next level
		}
		if(next instanceof InfoField) {
			//print message to UI 
		}
		return false;
	}
	
}
