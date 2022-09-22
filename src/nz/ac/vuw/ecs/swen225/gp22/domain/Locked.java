package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Locked extends Tile {
	public boolean locked = true;
	public Key.Color col;
	public Img icon;
	
	public Locked(Location l, Key.Color col) {
		super(l);
		this.col = col;
		this.icon = col.getLockedIcon();
		// TODO Auto-generated constructor stub
	}

	public void unlock() {
		System.out.println("Door unlocked");
		locked = false;
	}

	public boolean isLocked() {
		System.out.println("Door Locked");
		return locked;
	}

	// getters and setters
	public Key.Color getColor() {
		return col;
	}

	@Override
	public boolean CanWalkOn(Chap p) {
		for(Tile t : p.getChest()){
			if(t instanceof Key) {
				p.addToChest(this);
				return true;
			}
		}
		return false;
	}
}
