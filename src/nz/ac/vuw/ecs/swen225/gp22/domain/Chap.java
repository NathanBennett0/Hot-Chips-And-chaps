package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

public class Chap { //player which is not a core tile
	public ArrayList<Tile> chest; 
	public boolean won = false;
	public Location l;
	
	public Chap(Location l) {
		this.l = l;
		this.chest = new ArrayList<Tile>();
	}
	
	//getters and setters 
	public ArrayList<Tile> getChest(){
		return chest;
	}
	public Location getLocation() {
		return l;
	}
	public void setLocation(Location l) {
		this.l = l;
	}
	
	//winning methods 
	public boolean won() {
		return this.won;
	}
	public void setWin() {
		this.won = true;
	}
	
	//move methods (changes the location fields)
	public void moveUp() { //when location is changed the next ping chap is redrawn in that new location
		l = new Location(l.getX(), l.getY()-1); //how to? Stuck! 
	}
	public void moveDown() {
		l = new Location(l.getX(), l.getY()+1);
	}
	public void moveRight() {
		l = new Location(l.getX()+1, l.getY());
	}
	public void moveLeft() {
		l = new Location(l.getX()-1, l.getY());
	}
	
	//other methods
	public void addToChest(Tile t) {
		chest.add(t);
	}
}
