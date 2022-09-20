package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Tile implements Entity{ 
    public boolean c; //can walk on 
    public Location l;
    public Img icon = Img.Free; // Default icon is free tile

    public Tile(boolean c, Location l) {
        this.c = c;
        this.l = l;
    }
    //draw method?
    @Override
    public Location getLocation() {
        return l;
    }
	@Override
	public boolean CanWalkOn() {
		// TODO Auto-generated method stub
		return c;
	}
}
