package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class Filewriter {
    Level level;
    public Filewriter(Level level) {
        this.level = level;
    }

    public void saveToXML(String filename) {

        try {
            DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
            Document doc = docbuilder.newDocument();

            // root element for tilelist
            Element Tilelist = doc.createElement("Tilelist");
            doc.appendChild(Tilelist);

            // iterate through every tile
            for(Tile T : level.getTiles()) {
                // create tile
                Element newTile = doc.createElement("Tile");
                newTile = makeTileElement(newTile, doc, T);
                // add tile to tilelist
                Tilelist.appendChild(newTile);
            }

            // iterate through every locked tile
            for(Locked L : level.getLockedTiles()) {
                // create tile
                Element newTile = doc.createElement("LockedTile");
                newTile = makeTileElement(newTile, doc, L);
                newTile = makeLockedTile(newTile, doc, L);
                // add tile to tilelist
                Tilelist.appendChild(newTile);
            }

            // iterate through every key tile
            for(Key K : level.getKeyTiles()) {
                // create tile
                Element newTile = doc.createElement("KeyTile");
                newTile = makeTileElement(newTile, doc, K);
                newTile = makeKeyTile(newTile, doc, K);
                // add tile to tilelist
                Tilelist.appendChild(newTile);
            }

            // add everything to new xml file
            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            Transformer transformer = transformerfactory.newTransformer();
            DOMSource domsource = new DOMSource(doc);
            StreamResult streamresult = new StreamResult(new File(filename));
            transformer.transform(domsource, streamresult);

            // testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(domsource, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public Element makeKeyTile(Element newTile, Document doc, Key K) {
        Attr colour = doc.createAttribute("colour");
        colour.setValue(K.getColor().name());
        newTile.setAttributeNode(colour);
        return newTile;
    }

    public Element makeLockedTile(Element newTile, Document doc, Locked L) {
        Attr colour = doc.createAttribute("colour");
        colour.setValue(L.getColor().name());
        newTile.setAttributeNode(colour);
        return newTile;
    }

}
