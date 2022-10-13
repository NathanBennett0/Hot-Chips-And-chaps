package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

/*
 * @author Nathan Bennett
 * bennetnath1
 * 300580123 
 */

/**
 * Filewriter
 * Used to write a given level object to file
 */
public class Filewriter {
    Level level;
    int time;
    
    public Filewriter(Level level, int time) {
        this.level = level;
        this.time = time;
    }

    /**
     * Takes a level object and writes all of the data to a file
     * @param filename
     */
    public void saveToXML(String filename) {

        try {
            DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
            Document doc = docbuilder.newDocument();

            // root element for Level
            Element Level = doc.createElement("Level");
            Attr levelname = doc.createAttribute("name");
            levelname.setValue(String.valueOf(level.getLevel()));   
            Level.setAttributeNode(levelname);
            doc.appendChild(Level);

            filename = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/" + filename + ".xml";

            // chap
            Element TimeE = doc.createElement("Time");
            Attr timeleft = doc.createAttribute("timeleft");
            timeleft.setValue(String.valueOf(time));
            TimeE.setAttributeNode(timeleft);
            Level.appendChild(TimeE);

            Element Chap = doc.createElement("Chap");
            Chap = makeCharTile(Chap, doc, level.getChap());
            Level.appendChild(Chap);

            // chap inventory
            for(Tile T : level.getChap().getChest()) {
                // create tile
                if(T instanceof Key) {
                    Key K = (Key)T;
                    Element newTile = doc.createElement("ChapKeyTile");
                    newTile = makeTileElement(newTile, doc, K);
                    newTile = makeKeyTile(newTile, doc, K);
                    // add tile to Level
                    Chap.appendChild(newTile);
                } else {
                    Element newTile = doc.createElement("ChapTreasureTile");
                    newTile = makeTileElement(newTile, doc, T);
                    // add tile to Level
                    Chap.appendChild(newTile);
                }
                
            }

            // actor
            Element Actor = doc.createElement("Actor");
            Actor = makeCharTile(Actor, doc, level.getActor());
            Level.appendChild(Actor);

            // tilelist
            Element Tilelist = doc.createElement("Tilelist");
            Level.appendChild(Tilelist);

            // iterate through every tile
            for(Tile T : level.getTiles()) {
                // create tile
                Element newTile = doc.createElement("Tile");
                newTile = makeTileElement(newTile, doc, T);
                // add tile to Level
                Tilelist.appendChild(newTile);
            }

            // iterate through every locked tile
            for(Locked L : level.getLockedTiles()) {
                // create tile
                Element newTile = doc.createElement("LockedTile");
                newTile = makeTileElement(newTile, doc, L);
                newTile = makeLockedTile(newTile, doc, L);
                // add tile to Level
                Tilelist.appendChild(newTile);
            }

            // iterate through every key tile
            for(Key K : level.getKeyTiles()) {
                // create tile
                Element newTile = doc.createElement("KeyTile");
                newTile = makeTileElement(newTile, doc, K);
                newTile = makeKeyTile(newTile, doc, K);
                // add tile to Level
                Tilelist.appendChild(newTile);
            }

            // info tile
            Element infoFieldTile = doc.createElement("InfoTile");
            infoFieldTile = makeTileElement(infoFieldTile, doc, level.getInfoField());
            infoFieldTile = makeInfoFieldTile(infoFieldTile, doc, level.getInfoField());
            Tilelist.appendChild(infoFieldTile);

            // add everything to new xml file
            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            Transformer transformer = transformerfactory.newTransformer();
            DOMSource domsource = new DOMSource(doc);
            StreamResult streamresult = new StreamResult(new File(filename));
            transformer.transform(domsource, streamresult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the type, x, and y attributes to the element
     * @param newTile
     * @param doc
     * @param T
     * @return Element
     */
    public Element makeTileElement(Element newTile, Document doc, Tile T) {
        // tile type
        Attr type = doc.createAttribute("type");
        type.setValue(T.getClass().getSimpleName());   
        newTile.setAttributeNode(type);
        // tile x
        Attr x = doc.createAttribute("x");
        x.setValue(String.valueOf(T.getLocation().getX()));
        newTile.setAttributeNode(x);
        // tile y
        Attr y = doc.createAttribute("y");
        y.setValue(String.valueOf(T.getLocation().getY()));
        newTile.setAttributeNode(y);

        return newTile;
    }

    /**
     * Adds the colour attribute to the element
     * @param newTile
     * @param doc
     * @param K
     * @return Element
     */
    public Element makeKeyTile(Element newTile, Document doc, Key K) {
        Attr colour = doc.createAttribute("colour");
        colour.setValue(K.getColor().name());
        newTile.setAttributeNode(colour);
        return newTile;
    }

    /**
     * Adds the colour attribute to the element
     * @param newTile
     * @param doc
     * @param L
     * @return Element
     */
    public Element makeLockedTile(Element newTile, Document doc, Locked L) {
        Attr colour = doc.createAttribute("colour");
        colour.setValue(L.getColor().name());
        newTile.setAttributeNode(colour);
        return newTile;
    }

    /**
     * Adds the text attribute to the element
     * @param newTile
     * @param doc
     * @param I
     * @return Element
     */
    public Element makeInfoFieldTile(Element newTile, Document doc, InfoField I) {
        Attr message = doc.createAttribute("text");
        message.setValue(I.getMessage());
        newTile.setAttributeNode(message);
        return newTile;
    }

    /**
     * Makes the chap, or actor element
     * @param newTile
     * @param doc
     * @param C
     * @return Element
     */
    public Element makeCharTile(Element newTile, Document doc, Tile T) {
        Attr x = doc.createAttribute("x");
        x.setValue(String.valueOf(T.getLocation().getX()));
        newTile.setAttributeNode(x);
        // tile y
        Attr y = doc.createAttribute("y");
        y.setValue(String.valueOf(T.getLocation().getY()));
        newTile.setAttributeNode(y);
        return newTile;
    }

}
