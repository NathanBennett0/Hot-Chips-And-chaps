package nz.ac.vuw.ecs.swen225.gp22.domain;

public class AliveState implements State{
    Location l;
    Maze m;
    public AliveState(Location l, Maze m){ //should it be chap hmmm
        this.l = l;
        this.m = m;
    }

    @Override
    public Location moveUp() {
        Location next = new Location(l.getX(), l.getY()-1);
        //move box 
        Tile t = m.getTile(next);
        if(t instanceof Box){ //if the next location is a box 
            Box b = (Box)t;
            //set location on maze
            Location newl = new Location(next.getX(), next.getY()-1);
            m.setBoxLocation(newl);
            b.setLocation(newl);
            m.removeTile(next); //box moves 
        }  //do this code here???
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l; // Return the same position and do not move to next
        }
        m.setChapLoc(next);
        l = next; // Move the location to the next
        return next; //then chap moves 
    }

    @Override
    public Location moveDown() {
        Location next = new Location(l.getX(), l.getY()+1);
        Tile t = m.getTile(next);
        if(t instanceof Box){  
            Box b = (Box)t;
            Location newl = new Location(next.getX(), next.getY()+1);
            m.setBoxLocation(newl);
            b.setLocation(newl);
            m.removeTile(next); 
        }
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }

    @Override
    public Location moveRight() {
        Location next = new Location(l.getX()+1, l.getY());
        Tile t = m.getTile(next);
        if(t instanceof Box){  
            Box b = (Box)t;
            Location newl = new Location(next.getX()+1, next.getY()+1);
            m.setBoxLocation(newl);
            b.setLocation(newl);
            m.removeTile(next); 
        }
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }

    @Override
    public Location moveLeft() {
        Location next = new Location(l.getX()-1, l.getY());
        Tile t = m.getTile(next);
        if(t instanceof Box){  
            Box b = (Box)t;
            Location newl = new Location(next.getX()-1, next.getY());
            m.setBoxLocation(newl);
            b.setLocation(newl);
            m.removeTile(next); 
        }
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }
}