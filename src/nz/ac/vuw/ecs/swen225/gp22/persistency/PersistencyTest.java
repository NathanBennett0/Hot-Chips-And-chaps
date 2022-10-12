package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.InputStream;
import java.nio.file.Path;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.*;

import static org.junit.Assert.*;  
import org.w3c.dom.Document;

import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Location;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;

public class PersistencyTest {
    

    /* HOW TO TEST
         * READER
         * Make new filereader with one of each tile type
         * Check that the result of the file looks as it should with this test
         * 
         * Call each Element method individually and test they look correct
         * 
         * WRITER
         * Make a few Tile objects and load them into file
         * Can test the exact syntax to see that file looks as it should
    */

    
    //Path path1;
    File file1;

    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /* 
     * executed before every test: creates the temporary file 
    */
    @Before
    public void setUp() {
        try {
            file1 = folder.newFile("testfile1.xml");
        }
        catch( IOException ioe ) {
            System.err.println(ioe);
        }
    }

    /**
     *  .
     */
    @Test
    public void persistencyTest() {

        //write out data to the test files
        try {
            FileWriter fw1 = new FileWriter( file1 );
            BufferedWriter bw1 = new BufferedWriter( fw1 );
            bw1.write("<Level name=\"1\">");
            bw1.write("<Tilelist>");
            bw1.write("<Tile type=\"Wall\" x=\"5\" y=\"3\"/>");
            bw1.write("</Tilelist>");
            bw1.write("<Chap x=\"10\" y=\"10\"/>");
            bw1.write("<Time timeleft=\"90000\"/>");
            bw1.write("</Level>");
            bw1.close();

        }
        catch( IOException ioe ) {
            System.err.println(ioe);
        }
        Filereader fr = new Filereader();
        System.out.println();
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
        level.removeTreasureTile((Treasure)level.getTiles().get(1));
        level.removeLockedTile((Locked)level.getLockedTiles().get(0));
        level.removeKeyTile((Key) level.getKeyTiles().get(0));
        level.addTileToInventory(new Treasure(new Location(9,3), false));

        // tiles
        assertTrue(level.getAllTiles().get(0) instanceof Wall);

        // chap tests
        assertEquals(level.getChap().toString(), "Chap");

        // actor tests
        assertFalse(level.getActor().c == null);

        Filewriter fw = new Filewriter(level, level.getTime());

    }
}