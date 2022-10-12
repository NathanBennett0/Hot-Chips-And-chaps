package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Key extends Tile {
	public Key.Color col;
	public Img icon;

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

	@Override
	public boolean CanWalkOn(Chap p) {
		try {p.addToChest(this);} catch (IOException e1) {} //add to chaps chest 
		try {p.m.removeTile(l);} catch (IOException e) {} //this is determining that the key is collected 
		//System.out.println("key added to chest");
		//remove from maze 
		return true;
	}

	// getters and setters
	public Color getColor() {
		return col;
	}
	
	@Override
	public Img getImg() {
    	return icon;
    }

	public String toString(){
		return "Key "+this.col;
	}
}
