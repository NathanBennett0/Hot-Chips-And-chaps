package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Locked extends Tile{
	public boolean locked = true;
	public Key.Color col;
	
	public Locked(boolean c, Location l, Key.Color col) {
		super(c, l);
		this.col = col;
		// TODO Auto-generated constructor stub
	}
	
	public void unlock() {
		locked = false;
	}
	public boolean isLocked() {
		return locked;
	}
	//getters and setters 
	public Key.Color getColor(){
		return col;
	}

}
