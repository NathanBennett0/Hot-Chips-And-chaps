package nz.ac.vuw.ecs.swen225.gp22.Recorder;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import org.dom4j.Element;
import org.dom4j.tree.BaseElement;
import org.junit.jupiter.engine.extension.ExtensionRegistry;

/**
 * Handles the move for when a key is collected
 * 
 * @param key
 *  
 */
public record keyMove(String key, String color, int x, int y) implements Move {

    @Override
    public Element saveXML() {
        Element e = new BaseElement("key").addAttribute("key", key).addAttribute("x", String.valueOf(x)).addAttribute("y", String.valueOf(y));

        if(!color.equals("None")){ e.addAttribute("color", color); }
            
        return e;
        
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
