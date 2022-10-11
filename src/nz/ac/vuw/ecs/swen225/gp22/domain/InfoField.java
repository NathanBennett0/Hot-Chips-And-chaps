package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class InfoField extends Tile {
	private String m;
	public Img icon = Img.Info;

	public InfoField(Location l, String m) {
		super(l);
		this.m = m;
	}

	// getters and setters
	public String getMessage() {
		return m;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "InfoField";
	}

}
