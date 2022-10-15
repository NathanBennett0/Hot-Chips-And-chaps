package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.image.BufferedImage;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public abstract class Tile implements Entity{
    public Location l;
    private Img icon = Img.Free; // default tile is free

    public Tile(Location l) {
        this.l = l;
    }
    
    
    /*
     * get Location of the tile 
     * @return Location 
     */
    @Override
    public Location getLocation() {
        return l;
    }

    /*
     * defualt implementation 
     * @return boolean 
     */
    @Override
    public boolean CanWalkOn(Chap p) { //everything needs to implement it
        return false;
    }
    
    /*
     * defualt image 
     * @return Img 
     */
    public Img getImg() {
    	return icon;
    }
    
    
}
