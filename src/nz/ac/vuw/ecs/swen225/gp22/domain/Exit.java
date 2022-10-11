package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Exit extends Tile {
	public Img icon = Img.Exit;

	public Exit(Location l) {
		super(l);
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		//move to next level 
		return true; 
	}

	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Exit";
	}

}
