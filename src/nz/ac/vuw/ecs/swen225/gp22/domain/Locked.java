package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.SoundEffects;

public class Locked extends Tile {
	private boolean locked = true;
	public Key.Color col;
	public Img icon;
	private SoundEffects sound = new SoundEffects();
	
	public Locked(Location l, Key.Color col) {
		super(l);
		this.col = col;
		this.icon = col.getLockedIcon();
		// TODO Auto-generated constructor stub
	}

	/*
	 * unlocks the door by changing the field 
	 */
	public void unlock() {
		//sound.playUnlockMusic();
		locked = false;
		System.out.println("Door unlocked");
	}

	/* 
	 * returns if the door is locked or not 
	 */
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
				Key k = (Key)t;
				if(k.getColor().equals(this.col)){
					try {p.m.removeTile(l);} catch (IOException e) {}
					p.removeKey(k);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Locked "+this.col;
	}
}
