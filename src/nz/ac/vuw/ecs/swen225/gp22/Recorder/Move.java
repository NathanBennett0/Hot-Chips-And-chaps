package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import javax.lang.model.element.Element;

/**
 * Interface to be implemented for each Move
 */
public interface Move {
    /**
      * Save the move in an XML format
      */
    Element saveXML();
      
    /**
     * Performs the move.
     */
    void move();
    
    /**
     * Undoes the action.
     */

     void undo();
}
