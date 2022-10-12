package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class ExitLock extends Tile{
	public Img icon = Img.ExitLock;
	
	public ExitLock(Location l) {
		super(l);
	}

	/*
	 * Checks if chap can walk on this tile 
	 * @param Chap 
	 * @return boolean 
	 */
	@Override
	public boolean CanWalkOn(Chap p) {
		//return true;
		try {
			if(p.m.numOfTreasures()==0){ //assume that chap has them all 
				//System.out.println("Exitlock unlocked");
				//System.out.println(p.getLocation().getX()+" "+p.getLocation().getY());
				p.m.removeTile(l);
				return true;
			} 
		} catch (IOException e) {}
		return false; //nothing to do here 
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
		return "ExitLock";
	}
}
