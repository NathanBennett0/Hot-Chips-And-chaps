package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

public class Chap { //player which is not a core tile
	public ArrayList<Tile> chest; 
	public boolean won = false;
	public Location l;
	public State s = new AliveState(l);//have state object, check if state object is dead or alive
	
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
	public void moveUp() {
		s.moveUp();
	}
	public void moveDown() {
		s.moveDown();
	}
	public void moveRight() {
		s.moveRight();
	}
	public void moveLeft() {
		s.moveLeft();
	}

	public void changeState(State newSt){
		this.s = newSt;
	}
	
	//other methods
	public void addToChest(Tile t) {
		chest.add(t);
	}
}
