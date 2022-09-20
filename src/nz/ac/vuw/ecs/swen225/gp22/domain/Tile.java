package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Tile implements Entity{
    public Location l;

    public Tile(Location l) {
        this.l = l;
    }
    //draw method?
    @Override
    public Location getLocation() {
        return l;
    }

    @Override
    public boolean CanWalkOn(Chap p) { //everything needs to implement it
        return false;
    }
}
