package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Free extends Tile {
	public Img icon = Img.Free;

	public Free(Location l) {
		super(l);
	} // empty tile

	/*
	 * Checks if chap can walk on this tile 
	 * @param Chap 
	 * @return boolean 
	 */
	@Override
	public boolean CanWalkOn(Chap p) {
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
		return "Free";
	}
}
