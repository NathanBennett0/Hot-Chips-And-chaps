package nz.ac.vuw.ecs.swen225.gp22.domain;

public class AliveState implements State{
    Location l;
    Maze m;
    public AliveState(Location l, Maze m){ //should it be chap hmmm
        this.l = l;
        this.m = m;
    }

    @Override
    public void moveUp() {
        Location templ = new Location(l.getX(), l.getY()-1);
        if(!m.allowAction(l)){
            throw new IllegalArgumentException("Chap cannot move here!"); //pre condition check
        }
        l = templ;
    }

    @Override
    public void moveDown() {
        Location templ = new Location(l.getX(), l.getY()+1);
        if(!m.allowAction(l)){
            throw new IllegalArgumentException("Chap cannot move here!"); //pre condition check
        }
        l = templ;
    }

    @Override
    public void moveRight() {
        Location templ = new Location(l.getX()+1, l.getY());
        if(!m.allowAction(l)){
            throw new IllegalArgumentException("Chap cannot move here!"); //pre condition check
        }
        l = templ;
    }

    @Override
    public void moveLeft() {
        Location templ = new Location(l.getX()-1, l.getY());
        if(!m.allowAction(l)){
            throw new IllegalArgumentException("Chap cannot move here!"); //pre condition check
        }
        l = templ;
    }
}
