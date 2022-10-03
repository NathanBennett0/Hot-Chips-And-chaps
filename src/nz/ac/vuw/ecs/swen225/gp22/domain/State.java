package nz.ac.vuw.ecs.swen225.gp22.domain;

public interface State {
    public Location moveUp();
    public Location moveDown();
    public Location moveRight();
    public Location moveLeft();
}