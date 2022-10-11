package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Treasure extends Tile {
	public boolean cl = false; // always not collected when first instantiated
	public Img icon = Img.TreasureOne;

	public Treasure(Location l, boolean cl) {
		super(l);
		this.cl = cl;
		// TODO Auto-generated constructor stub
	}

	public boolean isCollected() { // collected or not collected
		return cl;
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		if(!isCollected()) {
			p.addToChest(this); //add to chaps chest 
            cl = true; //make that key collected 
            p.m.removeTile(l);//remove from maze 
			return true;
		}
		return false;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }
}
