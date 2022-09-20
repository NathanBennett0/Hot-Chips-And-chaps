package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Treasure extends Tile {
	public boolean cl = false; // always not collected when first instantiated
	public Img icon = Img.TreasureOne;

	public Treasure(boolean c, Location l, boolean cl) {
		super(c, l);
		this.cl = cl;
		// TODO Auto-generated constructor stub
	}

	public boolean isCollected() { // collected or not collected
		return c;
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		if(isCollected()) {
			p.addToChest(this);
			return true;
		}
		return false;
	}
}
