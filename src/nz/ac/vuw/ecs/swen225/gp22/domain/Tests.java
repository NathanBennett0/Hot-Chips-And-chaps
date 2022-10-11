package nz.ac.vuw.ecs.swen225.gp22.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

public class Tests {
    @Test 
    public void testValid1() {
        Treasure t = new Treasure(new Location(0,0), true);
        assert(t.toString().equals("Treasure"));   
	}

    @Test 
    public void testValid2(){

    }
}
