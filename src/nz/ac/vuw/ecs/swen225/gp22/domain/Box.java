package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

import java.io.Serializable;

public class Box extends Tile { //test if its serializable first
    public Img icon = Img.Free;

    public Box(Location l) { //behaves like a wall tile 
        super(l);
    }
	
	@Override
	public Img getImg() {
    	return icon;
    }

    public void setLocation(Location l){
        this.l = l;
    }

    public String toString(){
		return "Box"; 
	}

}
