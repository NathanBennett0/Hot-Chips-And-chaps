package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Random;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

/**
 * Name: Felix Ng
 * Student ID: 300570943
 */
public class Actor extends Tile{
    //explodes: kills chap (calls change state)
    //eats: kills chap (calls change state)
    //robs: takes things from chaps chest (chest must be public to be robbed)
    public Chap c; 
    public Location l;
    public Img icon = Img.LawnMowerRight;
    public Actor(Location l, Chap c){
    	super(l);
        this.c = c;
        this.l = l;
        System.out.println(this.toString() + " constructor called");
    }
    /*
     * Kills chap 
     */
    public void eatChap(){
        c.changeState(new DeadState());
    }
    /*
     * removes most recent thing in chaps chest 
     */
    public void robChap(){
        c.removeFromChest(); //removes first index in the chest
    }
    /**
     * if chap is within a certain radius then kill him
     */
    public void explode() {
        int x1 = this.l.getX();
        int y1 = this.l.getY();
        int x2 = c.getLocation().getX();
        int y2 = c.getLocation().getY();
        double dist = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)); //euclidean distance
        if(dist == 1) {
            eatChap(); //same as killing chap
        }
    }
    /**
     * method should be called in every ping
     * uses Math random to pick a random direction in left, right, top, bottom and move there
     */
    public void moveRandomly() {
        explode();
        int newDirX = (int) ((Math.random() * (1 - 4)) + 1); //4 directions
        int newDirY = (int) ((Math.random() * (1 - 4)) + 1); //might be able to move diagonally
        int currX = this.l.getX();
        int currY = this.l.getY();
        //int bool = (int) ((Math.random() * (1 - 2)) + 1); //number 1 or 2
        Random rd = new Random(); 
        boolean bool = rd.nextBoolean();
        if(bool) {
            if((c.m.getGrid()[currX+newDirX][currY+newDirY] instanceof Free)){
                Location old = l;
                this.l = new Location(currX+newDirX, currY+newDirY);
                c.m.setActorLocation(l, old);
            } else {
                moveRandomly();
            }
        } else {
            if((c.m.getGrid()[currX-newDirX][currY-newDirY] instanceof Free)){
                Location old = l;
                this.l = new Location(currX-newDirX, currY-newDirY);
                c.m.setActorLocation(l, old);
            } else {
                moveRandomly();
            }
        }
    }

    /*
	 * gets the color of the key 
	 * @return Color 
	 */
    @Override
	public Img getImg() {
    	return icon;
    }

    /*
	 * return image of this tile 
	 * @return Img 
	 */
    public String toString(){
		return "Actor"; 
	}

}
