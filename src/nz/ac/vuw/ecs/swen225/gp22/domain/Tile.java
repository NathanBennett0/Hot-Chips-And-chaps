package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Tile implements Entity{ 
    public boolean c; //can walk on 
    public Location l;

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
