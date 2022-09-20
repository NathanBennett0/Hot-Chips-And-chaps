package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Key extends Tile {
	public Color col;
	public boolean cl = false;

	@Override
	public boolean CanWalkOn(Chap p) {
		if(isCollected()) {
			p.addToChest(this);
			return true;
		}
		return false;
	}

	public enum Color{
		RED, GREEN, BLUE
	}

	public Key(Location l, Color col, boolean cl) {
		super(l);
		this.col = col;
		this.cl = cl;
		this.icon = col.getKeyIcon();
		// TODO Auto-generated constructor stub
	}

	// getters and setters
	public Color getColor() {
		return col;
	}

	public boolean isCollected() { // collected or not
		return cl;
	}
}
