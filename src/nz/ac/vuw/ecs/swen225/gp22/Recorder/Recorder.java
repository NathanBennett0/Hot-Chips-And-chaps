package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Recorder {
	private final List<Move> movesList;
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
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("Game").addAttribute("level", String.valueOf(level));
		movesList.forEach(move -> root.add(move.saveXML()));

		String fName = System.getProperty("user.dir") + "/recorder/files" + LocalDateTime.now().toString() + ".chipvschap.xml";
		try(FileWriter write = new FileWriter(fName, StandardCharsets.UTF_8)){
			(new XMLWriter(write, OutputFormat.createPrettyPrint())).write(doc);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("saving file not successful");
		}
	}

		public void addMove(Move m){
			movesList.add(m);
		}
	
}
