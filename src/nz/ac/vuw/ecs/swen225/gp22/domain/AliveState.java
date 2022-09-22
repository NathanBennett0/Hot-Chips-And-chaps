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
        l = new Location(l.getX(), l.getY()+1);
    }

    @Override
    public void moveRight() {
        l = new Location(l.getX()+1, l.getY());
    }

    @Override
    public void moveLeft() {
        l = new Location(l.getX()-1, l.getY());
    }
}
