package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;

public class AliveState implements State{
    public Location l;
    public Maze m;
    public AliveState(Location l, Maze m){ 
        this.l = l;
        this.m = m;
    }

    /*
     * Move up implementation for when chap is alive 
     * @return Location 
     */
    @Override
    public Location moveUp() {
        Location next = new Location(l.getX(), l.getY()-1);
        //do this code here???
        if(!m.allowAction(next)){
            //System.out.println("Chap cannot move here!");//pre condition check
            return l; // Return the same position and do not move to next
        }
        m.setChapLoc(next);
        l = next; // Move the location to the next
        return next; //then chap moves 
    }

    /*
     * move down implemnentation for when chap is alive 
     * @return Location 
     */
    @Override
    public Location moveDown() {
        Location next = new Location(l.getX(), l.getY()+1);
        if(!m.allowAction(next)){
            //System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }

    /*
     * move right implemnentation for when chap is alive 
     * @return Location 
     */
    @Override
    public Location moveRight() {
        Location next = new Location(l.getX()+1, l.getY());
//        Tile t = m.getTile(next);
//        if(t instanceof Box){  
//            Box b = (Box)t;
//            Location newl = new Location(next.getX()+1, next.getY()+1);
//            try {
//                m.setBoxLocation(newl);
//                b.setLocation(newl);
//                m.removeTile(next); 
//            } catch (IOException e) {} 
//        }
        if(!m.allowAction(next)){
            //System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }

    /*
     * move left implemnentation for when chap is alive 
     * @return Location 
     */
    @Override
    public Location moveLeft() {
        Location next = new Location(l.getX()-1, l.getY());
        if(!m.allowAction(next)){
            //System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }

    public String toString(){
        return "Alive";
    }
}