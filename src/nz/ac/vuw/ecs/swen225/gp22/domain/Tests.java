
package nz.ac.vuw.ecs.swen225.gp22.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import java.awt.event.KeyEvent;
import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

public class Tests {
    
    @Test 
    public void testValid1(){
        App app = App.getInstance();
        app.phaseOne();
        Controller c = app.getGame().phase().controller();
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        //Level 1 complete 
    }
    
    @Test
    public void testValid2() {
        App app = App.getInstance();
        app.phaseOne();
        Controller c = app.getGame().phase().controller();
        Maze m = app.getGame().phase().maze();
        m.toString();
        //attempts to go through exit lock without all treasures
        //attemps to go through doors without key. 
        //attempts to get killed by actor
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_S);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_D);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_W);
        c.keyPressed(KeyEvent.VK_A);
        c.keyPressed(KeyEvent.VK_W);
        System.out.println(m.getChap().getLocation().toString());
        //awaiting to be used objects 
        Location loc = new Location(0,0);
        loc.toString();
      //Creating Actors
        Actor a = new Actor(loc,m.getChap());
        a.eatChap();
        a.moveRandomly();
        a.toString();
        a.explode();
        a.robChap();
        //info field 
        InfoField i = new InfoField(new Location(0,0),"String");
        i.getImg();
        i.getLocation();
        i.getMessage();
        i.toString();
        //chaps interaction with actor 
        m.getChap().setWin();
        m.getChap().won();
        m.getChap().changeState(new DeadState());
        //m.getChap().moveDown();
        //m.getChap().moveUp();
        //m.getChap().moveRight();
        //m.getChap().moveLeft();
        m.getLevel();
        m.getChap().setLocation(loc);
        m.getChap().getImg();
        //Creating Box and Water
        Box b = new Box(loc);
        b.getLocation();
        b.getImg();
        b.toString();
        b.setLocation(loc);
        Water w = new Water(loc);
        w.getLocation();
        w.getImg();
        w.toString();
        w.CanWalkOn(m.getChap());
        //Tile creation 
        Tile t = new Tile(loc, m);
        t.getImg();
        Locked l = new Locked(loc,Key.Color.BLUE);
        l.getColor();
        //creating a dangerous maze 
        try {Maze dm = new Maze(null, -2, -2);} catch (IOException e) {}
        
    }

}
