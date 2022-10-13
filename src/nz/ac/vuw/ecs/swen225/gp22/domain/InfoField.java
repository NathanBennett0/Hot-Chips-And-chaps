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
	public boolean CanWalkOn(Chap p) {
		p.setOnInfo(); //true 
		return true;
	}
	
	/*
	 * return image of this tile 
	 * @return Img 
	 */
	@Override
	public Img getImg() {
    	return icon;
    }

	/*
	 * String representation of This tile 
	 * @return String 
	 */
	public String toString(){
		return "InfoField";
	}

}
