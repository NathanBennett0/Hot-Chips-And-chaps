package nz.ac.vuw.ecs.swen225.gp22.domain;

public class DeadState implements State{
    /*
     * Moving up implementation for when chap is dead 
     */
    @Override
    public Location moveUp() {
        System.out.println(this.toString() + ". Can't move");
        return null;
    }

    /*
     * Moving down implementation for when chap is dead 
     */
    @Override
    public Location moveDown() {
        System.out.println(this.toString() + ". Can't move");
        return null;
    }

    /*
     * Moving right implementation for when chap is dead 
     */
    @Override
    public Location moveRight() {
        System.out.println(this.toString() + ". Can't move");
        return null;
    }

    /*
     * Moving left implementation for when chap is dead 
     */
    @Override
    public Location moveLeft() {
        System.out.println(this.toString() + ". Can't move");
        return null;
    }

    public String toString(){
        return "Dead";
    }
}