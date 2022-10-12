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

public class PersistencyTest {
    
    public static void main(String args[]) {
        // loads the xml file specified then create a document object
        PersistencyTest pt = new PersistencyTest();
    }

    public PersistencyTest() {
    }
    

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
            file1 = folder.newFile( "testfile1.txt" );
        }
        catch( IOException ioe ) {
            System.err.println( 
                "error creating temporary test file in " +
                this.getClass().getSimpleName() );
        }
    }

    /**
     *  .
     */
    @Test
    public void test2TempFiles() {

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
            System.err.println( 
                "error creating temporary test file in " +
                this.getClass().getSimpleName() );
        }

        assertTrue( file1.exists() );
        assertTrue( file1.isFile() );
    

    }
        
        
        /*
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
                   

            //System.out.println(bw1);
            bw1.close();


        } catch(Exception e) {
            e.printStackTrace();
        }

        assertTrue( file1.exists() );

        /*

        try {
        InputStream inputstream = getClass().getResourceAsStream("level1.xml");
        DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
        Document doc = docbuilder.parse(inputstream);
        //assertEquals("1", doc.getLength());
        System.out.println(doc.getBaseURI());
        } catch (Exception e){
            e.printStackTrace();
        }
        */

}
