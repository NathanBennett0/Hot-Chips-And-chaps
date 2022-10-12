package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Location {
	private int x;
	private int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// getters and setters
	/*
	 * get the x coordinate of this location 
	 * @return int 
	 */
	public int getX() {
		return this.x;
	}

	/*
	 * get the y coordinate of this location 
	 * @return int 
	 */
	public int getY() {
		return this.y;
	}

	/*
	 * @return String 
	 */
	public String toString(){
		return "("+x+", "+y+")";
	}
}
