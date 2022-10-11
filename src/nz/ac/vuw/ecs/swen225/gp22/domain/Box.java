package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

import java.io.Serializable;

public class Box extends Tile implements Serializable{ //test if its serializable first
    public Img icon = Img.Free;

    public Box(Location l) { //behaves like a wall tile 
        super(l);
        //TODO Auto-generated constructor stub
    }
	
	@Override
	public Img getImg() {
    	return icon;
    }
    public void setLocation(Location l){
        this.l = l;
    }



}
