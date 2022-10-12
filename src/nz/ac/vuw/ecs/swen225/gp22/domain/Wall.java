package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Wall extends Tile {
	public Img icon = Img.Wall;

	public Wall(Location l) {
		super(l);
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Wall";
	}

}
