package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Name: Felix Ng
 * Student ID: 300570943
 */
public class Actor {
    //explodes: kills chap (calls change state)
    //eats: kills chap (calls change state)
    //robs: takes things from chaps chest (chest must be public to be robbed)
    public Chap c; //needs to have knowledge of chap to do shit to him
    public Actor(Chap c){
        this.c = c;
    }
    public void killChap(){
        c.changeState(new DeadState());
    }
    public void robChap(){
    }

}
