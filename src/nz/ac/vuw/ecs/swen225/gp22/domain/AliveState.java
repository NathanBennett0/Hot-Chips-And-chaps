package nz.ac.vuw.ecs.swen225.gp22.domain;

public class AliveState implements State{
    Location l;
    public AliveState(Location l){ //should it be chap hmmm
        this.l = l;
    }

    @Override
    public void moveUp() {
        l = new Location(l.getX(), l.getY()-1);
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