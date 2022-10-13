package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Treasure extends Tile {
	//always not collected when first instantiated, and when picked up is no longer on maze
	public Img icon = Img.TreasureOne;

	public Treasure(Location l, boolean cl) {
		super(l);
	}

	/*
	 * Checks if chap can walk on this tile 
	 * @param Chap 
	 * @return boolean 
	 */
	@Override
	public boolean CanWalkOn(Chap p) {
		p.addToChest(this);
		p.m.removeTile(l);
		System.out.println(this.toString() + " collected");
		//remove from maze 
		return true;
	}
	
	/*
	 * gets the color of the key 
	 * @return Color 
	 */
	@Override
	public Img getImg() {
    	return icon;
    }

	/*
	 * return image of this tile 
	 * @return Img 
	 */
	public String toString(){
		return "Treasure";
	}
}
