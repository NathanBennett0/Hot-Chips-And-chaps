package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

//Chap now extends Tile Ella 
public class Chap extends Tile{ // player which is not a core tile
	public ArrayList<Tile> chest;
	public boolean won = false;
	public Location l;
	public Maze m;
	public State s;//have state object, check if state object is dead or alive
	
	public Chap(Location l, Maze m) {
		this.l = l;
		this.chest = new ArrayList<Tile>();
		this.s = new AliveState(l, m);
	}

	// getters and setters
	public ArrayList<Tile> getChest() {
		return chest;
	}

	public Location getLocation() {
		return l;
	}

	public void setLocation(Location l) {
		this.l = l;
	}

	// winning methods
	public boolean won() {
		return this.won;
	}

	public void setWin() {
		this.won = true;
	}
	
	//move methods (changes the location fields)
	public void moveUp() {
		System.out.println("Chap moving up");
		s.moveUp();
	}

	public void moveDown() {
		System.out.println("Chap moving down");
		s.moveDown();
	}

	public void moveRight() {
		System.out.println("Chap moving right");
		s.moveRight();
	}

	public void moveLeft() {
		System.out.println("Chap moving left");
		s.moveLeft();
	}

	public void changeState(State newSt){
		System.out.println("Chap changing state");
		this.s = newSt;
	}

	// other methods
	public void addToChest(Tile t) {
		chest.add(t);
	}

	//Nathan told me to add this 
	public void setMaze(Maze maze) {
        m = maze;
    }
}
