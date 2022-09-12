package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Key extends Tile{
	public Color col;
	public boolean cl = false;
	
	public enum Color{
		RED, GREEN, BLUE
	}

	public Key(boolean c, Location l, Color col, boolean cl) {
		super(c, l);
		this.col = col;
		this.cl = cl;
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
	public Color getColor() {
		return col;
	}
	public boolean isCollected() { //collected or not
		return cl;
	}
}
