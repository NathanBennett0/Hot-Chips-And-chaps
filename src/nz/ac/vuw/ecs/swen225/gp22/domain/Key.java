package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Key extends Tile {
	public Color col;
	public boolean cl = false;
	public Img icon;
	@Override
	public boolean CanWalkOn(Chap p) {
		if(!isCollected()) {
			System.out.println("key added to chest");
			p.addToChest(this); //add to chaps chest 
            cl = true; //make that key collected 
            p.m.removeTile(l);//remove from maze 
			return true;
		}
		return false;
	}

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
	
	@Override
	public Img getImg() {
    	return icon;
    }
}
