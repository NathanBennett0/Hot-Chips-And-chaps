package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Free;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;
import nz.ac.vuw.ecs.swen225.gp22.domain.Water;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.ExitLock;
import nz.ac.vuw.ecs.swen225.gp22.domain.Actor;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Exit;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * @author Nathan Bennett
 * bennetnath1
 * 300580123 
 */

/**
 * Filereader
 * Reads a file and uses the data to generate a level object
 */
public class Filereader {

    /**
     * Filereader
     */
    public Filereader() {}

    /**
     * Reads a given file and returns a level object
     * with all of the data given by the file
     * @param filename
     * @return Level
     */
    public Level loadLevel(String filename) {
        List<Tile> alltiles = new ArrayList<Tile>();
        List<Locked> lockedtiles = new ArrayList<Locked>();
        List<Key> keytiles = new ArrayList<Key>();
        Chap chap = new Chap(new Location(0,0), null);
        InfoField info = new InfoField(new Location(0,0), "");
        int time = 0;
        int levelnum = 0;
        Actor actor = new Actor(new Location(0,0), null);
        try {

            InputStream inputstream = getClass().getResourceAsStream(filename);
            if(inputstream == null) { return null; }

            // loads the xml file specified then create a document object
            DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
            Document doc = docbuilder.parse(inputstream);

            // this is the root node, which is the level in our xml files
            Element root = doc.getDocumentElement();
            root.normalize();
            levelnum = Integer.valueOf(root.getAttribute("name"));

            // gathers all of the regular tiles into a list
            NodeList tilelist = doc.getElementsByTagName("Tile");
            if(tilelist != null) { 
                for(int n = 0; n < tilelist.getLength(); n++) {
                    // gets the individual tile
                    Node tile = tilelist.item(n);
                    if(tile.getNodeType() == Node.ELEMENT_NODE) {
                        // finds attributes and uses them to create a new tile then add to list
                        Element T = (Element)tile;
                        alltiles.add(makeTile(T));          
                    }
                }
            }

            // locked door tiles
            NodeList lockedtilelist = doc.getElementsByTagName("LockedTile");
            if(lockedtilelist != null) { 
                for(int n = 0; n < lockedtilelist.getLength(); n++) {
                    // gets the individual tile
                    Node tile = lockedtilelist.item(n);
                    if(tile.getNodeType() == Node.ELEMENT_NODE) {
                        // finds attributes and uses them to create a new tile then add to list
                        Element T = (Element)tile;
                        lockedtiles.add(makeLockedTile(T));
                    }
                }
            }

            // key tiles
            NodeList keytilelist = doc.getElementsByTagName("KeyTile");
            if(keytilelist != null) { 
                for(int n = 0; n < keytilelist.getLength(); n++) {
                    // gets the individual tile
                    Node tile = keytilelist.item(n);
                    if(tile.getNodeType() == Node.ELEMENT_NODE) {
                        // finds attributes and uses them to create a new tile then add to list
                        Element T = (Element)tile;
                        keytiles.add(makeKeyTile(T));
                    }
                }
            }

            // reads the infotile from file
            NodeList InfoNodes = doc.getElementsByTagName("InfoTile");
            if(InfoNodes != null) {
                Node InfoNode = InfoNodes.item(0);
                Element I = (Element)InfoNode;
                // creates new infotile
                info = makeInfoFieldTile(I);
            }
            
            // reads the chap from file
            NodeList ChapNodes = doc.getElementsByTagName("Chap");
            if(ChapNodes != null) { 
                Node ChapNode = ChapNodes.item(0);
                Element C = (Element)ChapNode;
                // creates new chap
                chap = new Chap(new Location(Integer.parseInt(C.getAttribute("x")),Integer.parseInt(C.getAttribute("y"))), null);
            }

            // reads the keys the chap has possession of
            NodeList chapKeyTileList = doc.getElementsByTagName("ChapKeyTile");
            if(chapKeyTileList != null) { 
                for(int n = 0; n < chapKeyTileList.getLength(); n++) {
                    // gets the individual tile
                    Node tile = chapKeyTileList.item(n);
                    if(tile.getNodeType() == Node.ELEMENT_NODE) {
                        Element T = (Element)tile;
                        // adds tile(key) to chest
                        chap.addToChest((makeKeyTile(T)));
                    }
                }
            }

            // reads the treasure the chap has possession of
            NodeList chapTreasureTileList = doc.getElementsByTagName("ChapTreasureTile");
            if(chapTreasureTileList != null) { 
                for(int n = 0; n < chapTreasureTileList.getLength(); n++) {
                    // gets the individual tile
                    Node tile = chapTreasureTileList.item(n);
                    if(tile.getNodeType() == Node.ELEMENT_NODE) {
                        Element T = (Element)tile;
                        // adds tile(key) to chest
                        chap.addToChest((makeTile(T)));
                    }
                }
            }

            // reads the chap from file
            NodeList TimeNodes = doc.getElementsByTagName("Time");
            if(TimeNodes != null) { 
                Node TimeNode = TimeNodes.item(0);
                Element timeE = (Element)TimeNode;
                // creates new time
                time = Integer.parseInt(timeE.getAttribute("timeleft"));
            }

            // reads the chap from file
            if(levelnum > 1) {
                NodeList ActorNodes = doc.getElementsByTagName("Actor");
                if(ActorNodes != null) {
                    Node ActorNode = ActorNodes.item(0);
                    Element A = (Element)ActorNode;
                    // creates new actor
                    actor = new Actor(new Location(Integer.parseInt(A.getAttribute("x")), Integer.parseInt(A.getAttribute("y"))), chap);
                    System.out.println("actor x " + actor.getLocation().getX());
                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Level(alltiles, lockedtiles, keytiles, chap, info, time, levelnum, actor);
    }

    /**
     * Makes a regular tile
     * @param T
     * @return Tile
     */
    public Tile makeTile(Element T) {
        String type = T.getAttribute("type");
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        return newTile(type,Integer.parseInt(x), Integer.parseInt(y));
    }

    /**
     * Makes a key tile
     * @param T
     * @return Key
     */
    public Key makeKeyTile(Element T) {
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        String colour = T.getAttribute("colour");
        return new Key(new Location(Integer.parseInt(x),Integer.parseInt(y)), getcolour(colour), false);
    }

    /**
     * Makes a locked tile
     * @param T
     * @return Locked
     */
    public Locked makeLockedTile(Element T) {
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        String colour = T.getAttribute("colour");
        return new Locked(new Location(Integer.parseInt(x),Integer.parseInt(y)), getcolour(colour));
    }

    /**
     * Makes the InfoField
     * @param T
     * @return InfoField
     */
    public InfoField makeInfoFieldTile(Element T) {
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        String text = T.getAttribute("text");
        return new InfoField(new Location(Integer.parseInt(x),Integer.parseInt(y)), text);
    }

    /**
     * Finds the tile type then creates it
     * @param name
     * @param x
     * @param y
     * @return Tile
     */
    public Tile newTile(String name, int x, int y) {
        switch (name) {
            case "Free":
                return new Free(new Location(x,y));
            case "Wall":
                return new Wall(new Location(x,y));
            case "Treasure":
                return new Treasure(new Location(x,y), false);
            case "ExitLock":
                return new ExitLock(new Location(x,y));
            case "Exit":
                return new Exit(new Location(x,y));
            case "Water":
                return new Water(new Location(x,y));
        }
        return new Tile(new Location(0,0));
    }

    /**
     * Returns the corresponding color enum 
     * @param colour
     * @return Key.Color
     */
    public Key.Color getcolour(String colour) {
        switch (colour) {
            case "GREEN":
                return Key.Color.GREEN;
            case "BLUE":
                return Key.Color.BLUE;
            case "YELLOW":
                return Key.Color.YELLOW;
            case "ORANGE":
                return Key.Color.ORANGE;
        }
        return Key.Color.GREEN;
    }
}
