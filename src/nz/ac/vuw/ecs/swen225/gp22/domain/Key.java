package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Key extends Tile {
	public Key.Color col;
	public Img icon;

	/*
	 * Constants that represent our colors 
	 */
	public enum Color{
		YELLOW {Img getKeyIcon(){return Img.KeyYellow;}
			Img getLockedIcon() {return Img.LockedDoorYellow;}},
		ORANGE {Img getKeyIcon(){return Img.KeyOrange;}
			Img getLockedIcon() {return Img.LockedDoorOrange;}},
		GREEN  {Img getKeyIcon(){return Img.KeyGreen;}
			Img getLockedIcon() {return Img.LockedDoorGreen;}},
		BLUE   {Img getKeyIcon(){return Img.KeyBlue;}
			Img getLockedIcon() {return Img.LockedDoorBlue;}};
		abstract Img getKeyIcon();
		abstract Img getLockedIcon();
	}

	public Key(Location l, Color col, boolean cl) {
		super(l);
		this.col = col;
		this.icon = col.getKeyIcon();
		// TODO Auto-generated constructor stub
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
		//System.out.println("key added to chest");
		//remove from maze 
		return true;
	}

	// getters and setters
	/*
	 * gets the color of the key 
	 * @return Color 
	 */
	public Color getColor() {
		return col;
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
		return "Key "+this.col;
	}
}
