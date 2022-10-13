package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.SoundEffects;

public class Locked extends Tile {
	private Key.Color col;
	public Img icon;
	private SoundEffects sound = new SoundEffects();
	
	public Locked(Location l, Key.Color col) {
		super(l);
		this.col = col;
		this.icon = col.getLockedIcon();
		// TODO Auto-generated constructor stub
	}

	// getters and setters
	/*
	 * Gets color of the door 
	 * @return Key.Color 
	 */
	public Key.Color getColor() {
		return col;
	}

	/*
	 * Checks if chap can walk on this tile 
	 * @param Chap 
	 * @return boolean 
	 */
	@Override
	public boolean CanWalkOn(Chap p) {
		for(Tile t : p.getChest()){
			if(t instanceof Key) {
				Key k = (Key)t;
				if(k.getColor().equals(this.col)){
					sound.playUnlockMusic();
					p.m.removeTile(l);
					p.removeKey(k);
					System.out.println("Collected: " + this.toString());
					return true;
				}
			}
		}
		return false;
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
		return "Locked "+this.col;
	}
}
