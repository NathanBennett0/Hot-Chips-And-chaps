package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Free;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;
import nz.ac.vuw.ecs.swen225.gp22.domain.Treasure;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.ExitLock;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.Exit;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Location;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Filereader {

    public static void main(String[] agrs) {
        Filereader fr = new Filereader();
        Level level1 = fr.loadLevel("level1.xml");
        
    }

    public Filereader() {
    }

    public Level loadLevel(String filename) {
        ArrayList<Tile> alltiles = new ArrayList<Tile>();
        Chap chap = new Chap(new Location(0,0));
        try {
            InputStream inputstream = getClass().getResourceAsStream(filename);

            // loads the xml file specified then create a document object
            DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
            Document doc = docbuilder.parse(inputstream);

            // this is the root node, which is the level in our xml files
            Element root = doc.getDocumentElement();
            root.normalize();

            // used for testing
            /*
            String level = root.getAttribute("name");
            System.out.println("Root " + root.getNodeName());
            System.out.println("level is " + level);
            */

            // gathers all of the regular tiles into a list
            NodeList tilelist = doc.getElementsByTagName("Tile");
            for(int n = 0; n < tilelist.getLength(); n++) {
                // gets the individual tile
                Node tile = tilelist.item(n);
                if(tile.getNodeType() == Node.ELEMENT_NODE) {
                    // finds attributes and uses them to create a new tile then add to list
                    Element T = (Element)tile;
                    alltiles.add(makeTile(T));          
                }
            }
            // coloured tiles keys, doors
            NodeList colouredtilelist = doc.getElementsByTagName("ColouredTile");
            for(int n = 0; n < colouredtilelist.getLength(); n++) {
                // gets the individual tile
                Node tile = colouredtilelist.item(n);
                if(tile.getNodeType() == Node.ELEMENT_NODE) {
                    // finds attributes and uses them to create a new tile then add to list
                    Element T = (Element)tile;
                    alltiles.add(makeColouredTile(T));
                }
            }
            
            // reads the chap from file
            NodeList ChapNodes = doc.getElementsByTagName("Chap");
            Node ChapNode = ChapNodes.item(0);
            Element C = (Element)ChapNode;
            // creates new chap
            chap = new Chap(new Location(Integer.parseInt(C.getAttribute("x")),Integer.parseInt(C.getAttribute("y"))));

            // reads the keys the chap has possession of
            NodeList ChapTileList = doc.getElementsByTagName("ChapTile");
            for(int n = 0; n < ChapTileList.getLength(); n++) {
                // gets the individual tile
                Node tile = ChapTileList.item(n);
                if(tile.getNodeType() == Node.ELEMENT_NODE) {
                    Element T = (Element)tile;
                    // adds tile(key) to chest
                    chap.addToChest((makeColouredTile(T)));
                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Level(alltiles, chap);
    }

    public Tile makeTile(Element T) {
        String type = T.getAttribute("type");
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        return newTile(type,Integer.parseInt(x), Integer.parseInt(y));
    }

    public Tile makeColouredTile(Element T) {
        String type = T.getAttribute("type");
        String x = T.getAttribute("x");
        String y = T.getAttribute("y");
        String colour = T.getAttribute("colour");
        return newTile(type,Integer.parseInt(x), Integer.parseInt(y), colour);
    }

    public Tile newTile(String name, int x, int y) {
        switch (name) {
            case "Free":
                return new Free(true, new Location(x,y));
            case "Wall":
                return new Wall(false, new Location(x,y));
            case "Treasure":
                //return new Treasure(true, new Location(x,y));
            case "InfoField":
                //return new InfoField(true, new Location(x,y));
            case "ExitLock":
                return new ExitLock(true, new Location(x,y));
            case "Exit":
                return new Exit(false, new Location(x,y));
        }
        return new Tile(true, new Location(0,0));
    }

    public Tile newTile(String name, int x, int y, String colour) {
        switch (name) { 
            case "Key":
                return new Key(true, new Location(x,y), getcolour(colour), false);
            case "Locked":
                return new Locked(false, new Location(x,y), getcolour(colour));
        }
        return new Tile(true, new Location(0,0));
    }

    public Key.Color getcolour(String colour) {
        switch (colour) {
            case "Green":
                return Key.Color.GREEN;
            case "Blue":
                return Key.Color.BLUE;
            case "Yellow":
                return Key.Color.YELLOW;
            case "Orange":
                return Key.Color.ORANGE;
        }
        return Key.Color.GREEN;
    }
}
