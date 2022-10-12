package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Random;

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
    public Actor(Location l, Chap c){
    	super(l);
        this.c = c;
        this.l = l;
    }
    public void eatChap(){
        c.changeState(new DeadState());
    }
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
        if(dist < 10) {
            eatChap(); //same as killing chap
        }
    }
    /**
     * method should be called in every ping
     * uses Math random to pick a random direction in left, right, top, bottom and move there
     */
    public void moveRandomly() {
        int newDirX = (int) ((Math.random() * (1 - 4)) + 1); //4 directions
        int newDirY = (int) ((Math.random() * (1 - 4)) + 1); //might be able to move diagonally
        int currX = this.l.getX();
        int currY = this.l.getY();
        //int bool = (int) ((Math.random() * (1 - 2)) + 1); //number 1 or 2
        Random rd = new Random(); 
        boolean bool = rd.nextBoolean();
        if(bool) {
            this.l = new Location(currX+newDirX, currY+newDirY);
        } else {
            this.l = new Location(currX-newDirX, currY-newDirY);
        }
        //now refresh
    }

    public String toString(){
		return "Actor"; 
	}

}
