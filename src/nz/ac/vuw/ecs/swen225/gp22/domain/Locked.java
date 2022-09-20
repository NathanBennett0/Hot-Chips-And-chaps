package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Locked extends Tile {
	public boolean locked = true;
	public Key.Color col;
	public Img icon;

	public Locked(boolean c, Location l, Key.Color col) {
		super(c, l);
		this.col = col;
		icon = col.getLockedIcon();
		// TODO Auto-generated constructor stub
	}

	public void unlock() {
		locked = false;
	}

	public boolean isLocked() {
		return locked;
	}

	// getters and setters
	public Key.Color getColor() {
		return col;
	}

}
