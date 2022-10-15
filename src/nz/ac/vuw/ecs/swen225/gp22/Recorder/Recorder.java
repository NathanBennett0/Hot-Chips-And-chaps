package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import java.io.File;
import java.util.ArrayList;

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
 *         Student ID: 300580764
 */

/**
 * Recorder class primarily adds all the moves made in the game and saves
 * all the moves inside an XML file once the game is over.
 */
public class Recorder {

	/**
	 * List of moves
	 */

	public ArrayList<directionMove> RecorderList;

	/**
	 * Keeps track of the level
	 */
	private int level = 0;

	/**
	 * Constructor for a new recorder
	 * 
	 * @param L level
	 */
	public Recorder(int L) {
		level = L;
		RecorderList = new ArrayList<directionMove>();
	}

	/**
	 * Constructor for a new recording - to be used by App
	 */
	public Recorder() {
		RecorderList = new ArrayList<directionMove>();
	}

	/**
	 * Saves all moves to an XML file format
	 */
	public void saveMove() {

		try {

			DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
			Document doc = docbuilder.newDocument();

			// root element for game
			Element root = doc.createElement("Game");

			// sets a level as the next element
			Element LevelMoves = doc.createElement("LevelMoves");
			LevelMoves.setAttribute("Level", String.valueOf(level));
			root.appendChild(LevelMoves);

			// adding all moves from the arrayList into the XML file
			for (int i = 0; i < RecorderList.size(); i++) {
				// save the move keyCode
				Element move = doc.createElement("move");
				Attr moveAttr = doc.createAttribute("moveKeyCode");
				moveAttr.setValue(String.valueOf(RecorderList.get(i).getKeyCode()));
				move.setAttributeNode(moveAttr);

				LevelMoves.appendChild(move);
			}

			// appending the all elements to the document
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
	 * Adds move to the ArrayList
	 * 
	 * @param m
	 */
	public void addMove(directionMove m) {
		// For testing: System.out.println(m.getKeyCode());
		if (m != null) {
			RecorderList.add(m);
		}
	}

	/**
	 * Gets the list of moves being saved to the XML file
	 * 
	 * @return RecorderList
	 */
	public ArrayList<directionMove> getMove() {
		return RecorderList;
	}
}
