package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class ExitLock extends Tile{
	public ExitLock(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }
}
