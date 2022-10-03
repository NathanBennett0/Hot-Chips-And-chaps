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
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l; // Return the same position and do not move to next
        }
        m.setChapLoc(next);
        l = next; // Move the location to the next
        return next;
    }

    @Override
    public Location moveDown() {
        Location next = new Location(l.getX(), l.getY()+1);
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
        if(!m.allowAction(next)){
            System.out.println("Chap cannot move here!");//pre condition check
            return l;
        }
        m.setChapLoc(next);
        l = next;
        return next;
    }
}