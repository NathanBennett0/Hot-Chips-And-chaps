package nz.ac.vuw.ecs.swen225.gp22.persistency;

import org.junit.Test;

import static org.junit.Assert.*;  

import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;

/*
 * @author Nathan Bennett
 * bennetnath1
 * 300580123 
 */
public class PersistencyTest {
    
    /**
     * READER
     * Makes new filereader with one of each tile type
     * Checks that the result of the file looks as it should with this test
     * Calls each Element method individually and tests they look correct
     * 
     * WRITER
     * Saves the loaded file back into the same file, keeping the same state
     *
     */
    @Test
    public void persistencyTest() {
        Filereader fr = new Filereader();
        Level level = fr.loadLevel("Testfile.xml");   
        
        //level variables
        assertEquals(level.getLevel(), 2);
        assertFalse(level.getTiles().isEmpty());
        assertFalse(level.getLockedTiles().isEmpty());
        assertFalse(level.getKeyTiles().isEmpty());
        assertTrue(level.getInfoField() instanceof InfoField);
        assertEquals(level.getTime(), 41250);
        assertEquals("[Key GREEN, Treasure]", level.getChap().getChest().toString());
        assertTrue(level.getChap().getChest().size() > 0);
        assertTrue(level.getAllTiles().get(0) instanceof Wall);
        
        assertEquals(level.getInventory().toString(), "[]");

        // chap tests
        assertEquals(level.getChap().toString(), "Chap");

        // actor tests
        assertFalse(level.getActor().c == null);

        Filewriter fw = new Filewriter(level, level.getTime());
        fw.saveToXML("Testfile");

    }
}
