package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Exit extends Tile {
	public Img icon = Img.Exit;

	public Exit(Location l) {
		super(l);
	}

	/*
	 * Checks if chap can walk on this tile 
	 * @param Chap 
	 * @return boolean 
	 */
	@Override
	public boolean CanWalkOn(Chap p) {
		//move to next level 
		p.setWin();
		try {p.m.removeTile(l);} catch (IOException e) {}
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
		return "Exit";
	}

}
