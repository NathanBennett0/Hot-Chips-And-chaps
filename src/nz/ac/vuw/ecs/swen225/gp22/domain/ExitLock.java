package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class ExitLock extends Tile{
	public Img icon = Img.ExitLock;
	
	public ExitLock(Location l) {
		super(l);
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		//return true;
		try {
			if(p.m.numOfTreasures()==0){ //assume that chap has them all 
				System.out.println("Exitlock unlocked");
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {}
		return false;
	}

	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "ExitLock";
	}
}
