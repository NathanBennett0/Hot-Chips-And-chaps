package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public class Tile implements Entity{
    public Location l;
    public Img icon = Img.Free; // default tile is free

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
