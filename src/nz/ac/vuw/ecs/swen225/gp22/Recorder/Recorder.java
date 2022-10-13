package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
* @author Christine Jayme
 * Student ID: 300580764
 */
public class Recorder {

	/**
	 * List of moves
	 */
	private final List<directionMove> movesList;

	/**
	 * Keeps track of the level
	 */
	private int level = 0;

	/**
	 * Constructing a new recorder
	 * 
	 * @param L level
	 */
	public Recorder(int L) {
		level = L;
		movesList = new ArrayList<>();
	}

	/**
	 * Constructor for App
	 */
	public Recorder() {
		movesList = new ArrayList<>();
	}

	/**
	 * Save a move to an XML file format
	 */
	public void saveMove() {
		try {

			DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
			Document doc = docbuilder.newDocument();

			// root element for game
			Element root = doc.createElement("Game");

			// sets a level element
			Element LevelMoves = doc.createElement("LevelMoves");
			LevelMoves.setAttribute("Level", String.valueOf(level));

			root.appendChild(LevelMoves);

			for (directionMove m : movesList) {

				// save the move keyCode
				Element move = doc.createElement("move");
				Attr moveAttr = doc.createAttribute("moveKeyCode");
				moveAttr.setValue(String.valueOf(m.getKeyCode()));
				move.setAttributeNode(moveAttr);

				// saving the app to the XML
				Attr app = doc.createAttribute("App");
				app.setValue(String.valueOf(m.getApp()));

				LevelMoves.appendChild(move);
			}

			// appending the all elements the document
			doc.appendChild(root);

			// creating the XML file
			String fileLevel = "Recorder" + String.valueOf(level);
			String fName = "src/nz/ac/vuw/ecs/swen225/gp22/Recorder/" + fileLevel + ".xml";

			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			DOMSource domsource = new DOMSource(doc);
			StreamResult streamresult = new StreamResult(new File(fName));
			transformer.transform(domsource, streamresult);

		} catch (Exception e) {
			System.out.println("failed save move");
			e.printStackTrace();
		}

	}

	/**
	 * Adds move to the arrayList
	 * 
	 * @param m
	 */
	public void addMove(directionMove m) {
		movesList.add(m);
	}

	/**
	 * Gets the list of moves
	 * 
	 * @return movesList
	 */
	public List<directionMove> getMove() {
		return movesList;
	}
}
