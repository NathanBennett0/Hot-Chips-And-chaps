package nz.ac.vuw.ecs.swen225.gp22.Recorder;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.io.InputStream;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

//create map for move and time at the same time
//create your own timer in the App so that each time a move is done, you can add that move and time into the map
//Once the whole level is finished and you have that array, you can then load the level

/**
 * TO DO: Find how to store the levels as well.
 */

public class RecordLoad {

    // private final double time;
    private final static LinkedHashMap<Double, String> recordMap = new LinkedHashMap<Double, String>(); // LinkedHashMap
                                                                                                        // that stores
                                                                                                        // the time and
                                                                                                        // move
    private static double count = 0; // counts level within program

    /**
     * Constructor for loadRecord
     * 
     * @param time that the move was made
     * @param move the type of move done
     */
    public RecordLoad(Double time, String move) throws TransformerException, ParserConfigurationException, IOException {
        recordMap.put(time, move);

        // for testing
    
        if (recordMap.containsKey(0.0)) {
            loadDocRecord();
        }
        // find out when button is clicked then...
        // if(endGame = endofGame){
        // loadDocRecord();
        // }
    }

    public static void main(String[] agrs) {
        try{
        RecordLoad l = new RecordLoad(0.007, "up");
        RecordLoad x = new RecordLoad(0.001, "down");
        RecordLoad n = new RecordLoad(0.008, "down");
        RecordLoad a = new RecordLoad(0.0, "left");
    }catch(TransformerException , ParserConfigurationException, IOException exception){

    }

    }

    // if constructor shouldn't be used:

    /**
     * public loadRecord(){
     * 
     * }
     * 
     * public void setMap(Double time, String move){
     * sortedMap.put(time,move);
     * }
     * @throws IOException
     */
    // public void loadDocRecord() throws ParserConfigurationException,
    // TransformerException {
    public void loadDocRecord() throws TransformerException, ParserConfigurationException, IOException {
        // loads the xml file specified then creates a document object

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // sets root element as the level
        if (count > 1) {
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Level Two");
            doc.appendChild(rootElement);
        }
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Level One");
        doc.appendChild(rootElement);

        Element recElems = doc.createElement("record elements"); // the overall element
        rootElement.appendChild(recElems); // appends it into the new document

        // below code inspired by
        // https://www.tutorialspoint.com/java_xml/java_dom_create_document.htm
        // essentially iterates over moves and times (the key and value) in the map and
        // appends it to the new element
        for (Map.Entry<Double, String> entry : recordMap.entrySet()) {

            Element recordElements = doc.createElement("Move + Time"); // header for all moves and time in the doc

            Attr attrMove = doc.createAttribute("move"); // create new attribute for the move

            attrMove.setValue(entry.getValue()); // set the move of the attribute

            recordElements.setAttributeNode(attrMove); // add the attribute to the element

            Attr attrTime = doc.createAttribute("time"); // create new attribute for that time

            attrTime.setValue(entry.getKey() + ""); // set the time for that attribute

            recordElements.setAttributeNode(attrTime); // set the new attribute in the element

            recElems.appendChild(recordElements); // add element to the overall "moves" header
        }

        // if(gameState = endOfGame){

        // write dom document to a file
        try (FileOutputStream output = new FileOutputStream("C::\\gameRecord.xml")) {
            writeXml(doc, output);
        } catch (TransformerException e) {
            System.out.println("File load failed");
            e.printStackTrace();
        }
        // }
    }

    /**
     * Writes document to the output stream
     * 
     * @param d      the document created in loadRecord of the moves and its
     *               corresponding times
     * @param output the file OutPutStream
     * @throws TransformerException
     */
    private static void writeXml(Document d, OutputStream output) throws TransformerException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            System.out.println("File load failed");
            e.printStackTrace();
        }
    }
}
