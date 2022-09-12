package nz.ac.vuw.ecs.swen225.gp22.domain;

public class InfoField extends Tile{
	public String m;
	
	public InfoField(boolean c, Location l, String m) {
		super(c, l);
		this.m = m;
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
	public String getMessage() {
		return m;
	}

}
