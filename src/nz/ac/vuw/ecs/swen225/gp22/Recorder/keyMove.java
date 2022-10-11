package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import javax.lang.model.element.Element;

/**
 * Handles the move for when a key is collected
 * 
 * @param key
 *  
 */
public record keyMove(String key, String color, int x, int y) implements Move {

    @Override
    public Element saveXML() {
        return null;
      //  Element e = new 
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
}
