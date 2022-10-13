package nz.ac.vuw.ecs.swen225.gp22.Recorder;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nz.ac.vuw.ecs.swen225.gp22.app.App;


//create map for move and time at the same time
//create your own timer in the App so that each time a move is done, you can add that move and time into the map
//Once the whole level is finished and you have that array, you can then load the level

/**
 * TO DO: Find how to store the levels as well.
 */

public class RecordLoad {
    Document doc;
    List<directionMove> recordLoadMoves = new ArrayList<directionMove>();
    Element root;
    /** 
     * Loads XML file into document and store it
     */
    public RecordLoad(File file){

        try {
            //loading xml file and parsing it
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();    //an instance of builder to parse the specified xml file   
            DocumentBuilder db = dbf.newDocumentBuilder();  
            doc = db.parse(file);  

            //getting root node which is "Game"
            Element r = doc.getDocumentElement();
            root = r;
            doc.getDocumentElement().normalize();  

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
            NodeList nodeList = doc.getElementsByTagName("move"); //nodelist from xml
            
            //testing what the root element is
            System.out.println("Root element: " + doc.getNodeName());  
            System.out.println("nodeList" + nodeList);

            //iterates through the nodeList and turns them into moveobjects
            for (int i = 0; i < nodeList.getLength(); i++){  

                Node currMove = nodeList.item(i);
                System.out.println("currMove in RecordLoad" + currMove);
                //gets attribute values
                int code = Integer.parseInt(currMove.getAttributes().getNamedItem("moveKeyCode").getNodeValue());
                directionMove dirMove = new directionMove(code);
                //adds moves into the getMoves
                System.out.println("recordLoadMoves is being added to");
                recordLoadMoves.add(dirMove);
              
        }
     } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e){
            System.out.println("failed parsing file");
        }  catch(SAXException s){
            System.out.println("failed parsing file");
        }
    }

    /**
     * Gets list of moves loaded from the XML file
     * @return list of directionMoves
     */
    public List<directionMove> getMoves(){ 
        return recordLoadMoves;
     
    }

     /** 
      *    Gets the level of the file
    
      */

    public int level(){
            root.normalize(); 
            NodeList nodeList = doc.getElementsByTagName("LevelMoves"); //nodelist from xml
            //iterates through the nodeList and turns them into moveobjects
            int level =0;
            for (int i = 0; i < nodeList.getLength(); i++){  
                Node currMove = nodeList.item(i);
                //gets attribute values
                level = Integer.parseInt(currMove.getAttributes().getNamedItem("Level").getNodeValue());
        }
        return level;
    }

}
