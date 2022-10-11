package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Free extends Tile {
	public Img icon = Img.Free;

	public Free(Location l) {
		super(l);
	} // empty tile

	@Override
	public boolean CanWalkOn(Chap p) {
		return true;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Free";
	}
}
