package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// import org.dom4j.Document;
// import org.dom4j.DocumentHelper;
// import org.dom4j.Element;
// import org.dom4j.io.OutputFormat;
// import org.dom4j.io.XMLWriter;

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

import nz.ac.vuw.ecs.swen225.gp22.domain.Actor;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key;
import nz.ac.vuw.ecs.swen225.gp22.domain.Locked;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class Recorder {
	private final List<directionMove> movesList;
	private final int level;
	
	/**
	 * Constructing a new recorder
	 */
	public Recorder(int L){
		level = L;
		movesList = new ArrayList<>();
	}

	/**
	 * Save a move to an XML file format
	 */
	public void saveMove(){
		try{
		DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
		Document doc = docbuilder.newDocument();

		   // root element for game
		   Element root = doc.createElement("Game");

			//create moves attribute

			//append levels to this one
			Element LevelMoves = doc.createElement("Level Moves");
			LevelMoves.setAttribute("Level", String.valueOf(level));

			for(directionMove m: movesList){

				//save move keyCode
				Element move = doc.createElement("move");
				Attr moveAttr = doc.createAttribute("moveKeyCode");
				moveAttr.setValue(String.valueOf(m.getKeyCode()));
				
				//save app keycode
				Attr app = doc.createAttribute("App");
				app.setValue(String.valueOf(m.getApp()));

				LevelMoves.appendChild(move);
			}

			//appending the level values
			root.appendChild(LevelMoves);
			doc.appendChild(root);
		   String fileLevel =  "Recorder" + String.valueOf(level);
		   String fName = "src/nz/ac/vuw/ecs/swen225/gp22/Recorder/" + fileLevel + ".xml";

		    TransformerFactory transformerfactory = TransformerFactory.newInstance();
            Transformer transformer = transformerfactory.newTransformer();
            DOMSource domsource = new DOMSource(doc);
            StreamResult streamresult = new StreamResult(new File(fName));
            transformer.transform(domsource, streamresult);

            // testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(domsource, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

	}
		public List<directionMove> getMove(){
			return movesList;
		}

		public void addMove(directionMove m){
			movesList.add(m);
		}
	
}
