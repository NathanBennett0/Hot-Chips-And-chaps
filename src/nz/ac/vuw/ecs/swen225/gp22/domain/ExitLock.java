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

	@Override
	public boolean CanWalkOn(Chap p) {
		if(p.m.numOfTreasures() == 0){
			return true;
		} else {
			return false;
		}
	}

}
