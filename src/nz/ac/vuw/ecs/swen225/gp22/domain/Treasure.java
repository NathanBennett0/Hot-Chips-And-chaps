package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Treasure extends Tile {
	//always not collected when first instantiated, and when picked up is no longer on maze
	public Img icon = Img.TreasureOne;

	public Treasure(Location l, boolean cl) {
		super(l);
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		try {p.addToChest(this);} catch (IOException e1) {} //add to chaps chest 
		try {p.m.removeTile(l);} catch (IOException e) {}
		//remove from maze 
		return true;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Treasure";
	}
}
