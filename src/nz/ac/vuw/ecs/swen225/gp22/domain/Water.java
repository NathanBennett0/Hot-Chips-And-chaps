package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Water extends Tile{
    public Img icon = Img.Free;

    public Water(Location l) {
        super(l);
        //TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean CanWalkOn(Chap p) {
		p.changeState(new DeadState()); //kills chap 
        return true; 
	}

    @Override
	public Img getImg() {
    	return icon;
    }

}
