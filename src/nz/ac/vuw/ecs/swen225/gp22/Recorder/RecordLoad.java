package nz.ac.vuw.ecs.swen225.gp22.Recorder;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


//create map for move and time at the same time
//create your own timer in the App so that each time a move is done, you can add that move and time into the map
//Once the whole level is finished and you have that array, you can then load the level

/**
 * TO DO: Find how to store the levels as well.
 */

public class RecordLoad {
    Document doc;
    /** 
     * Loads XML file into document and store it
     */
    public RecordLoad(File file){
        try{
            doc = new SAXReader().read(file);
        } catch(DocumentException dException){
            throw new IllegalArgumentException("Record of file is unsuccessful");
        }
    }

    /**
     * Moves taken from the XML
     */
    public List<Move> getMoves(){
        List<Element> moves = doc.getRootElement().elements();
        return moves.stream().map(this::loadMove).toList();
    }

    /**
     * Turns XML element into a move
     */
    private Move loadMove(Element e){
        switch(e.getName()){
            case "move" -> {
                String dir = e.attributeValue("direction");
                int x = Integer.parseInt(e.attributeValue("x"));
                int y = Integer.parseInt(e.attributeValue("y"));

            }
        }

        return null;
    }

     /**
     * Gets the level of the file
     */
    public int level(){
        return Integer.parseInt(doc.getRootElement().attributeValue("level"));
    }

}
