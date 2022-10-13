package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import org.dom4j.Element;

/**
 * Interface to be implemented for each Move
 */
public interface Move {
  /**
     * Performs the move.
     */
    void move();
  
    /**
     * Undoes the action.
     * void undo();
     */
}
