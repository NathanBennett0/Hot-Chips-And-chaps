package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.io.IOException;
import java.util.ArrayList;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.SoundEffects;

public class Chap extends Tile{ // player which is not a core tile
    public ArrayList<Tile> chest;
    public boolean won = false;
    public Location l;
    public Maze m;
    public State s;//have state object, check if state object is dead or alive
    public Img icon = Img.Chap;
    private SoundEffects sound = new SoundEffects();

    public Chap(Location l, Maze m) {
        super(l);
        this.l = l;
        this.chest = new ArrayList<Tile>();
        this.s = new AliveState(l, m);
    }

    // getters and setters
    /*
     * returns the image of Chap
     */
    public Img getImg() {
        return icon;
    }

    /*
     * returns Chaps chest 
     */
    public ArrayList<Tile> getChest() {
        return chest;
    }

    /*
     * returns chaps location 
     */
    public Location getLocation() {
        return l;
    }

    /*
     * sets chaps location 
     */
    public void setLocation(Location l) {
        this.l = l;
    }

    /*
     * removes the given key from the chest 
     */
    public void removeKey(Key k){
        if(chest.contains(k)) {
            chest.remove(k);
            k.toString();
        }
    }

    /*
     * returns if chap has won or not 
     */
    public boolean won() {
        return this.won;
    }

    /*
     * make chap win 
     */
    public void setWin() {
        this.won = true;
    }

    /*
     * sets chaps knowledge of the maze 
     */
    public void setMaze(Maze maze) {
        m = maze;
        this.s = new AliveState(l, m);
        System.out.println("Set maze");
    }

    //move methods (changes the location fields)
    /*
     * Moves chaps location up 
     */
    public void moveUp() {
        l = s.moveUp();
        System.out.println("Chap moving up");
    }

    /*
     * moves chaps location down 
     */
    public void moveDown() {
        l = s.moveDown();
        System.out.println("Chap moving down");
    }

    /*
     * moves chaps location right 
     */
    public void moveRight() {
        l = s.moveRight();
        System.out.println("Chap moving right");
    }

    /*
     * moves chaps location left 
     */
    public void moveLeft() {
        l = s.moveLeft();
        System.out.println("Chap moving left");
    }

    /*
     * changes chaps current state 
     */
    public void changeState(State newSt){
        this.s = newSt;
        System.out.println("Chap changing state");
    }

    /*
     * adds an item to chaps chest 
     */
    public void addToChest(Tile t) throws IOException{
        if(!(t instanceof Treasure)&&!(t instanceof Key)){ 
            throw new IOException("Chap cannot pick this up"); //pre
        }
        chest.add(t);
        //sound.playCollectMusic();
        assert(chest.contains(t)); //post 
        System.out.println("Added to chest: "+chest);
    }

    /*
     * removes the first item in chaps chest, this is used by the actor when he robs chap. 
     */
    public void removeFromChest() {
        chest.remove(0);
    }

    /*
     * Stringifies Chap 
     */
    public String toString(){
		return "Chap";
	}

}
