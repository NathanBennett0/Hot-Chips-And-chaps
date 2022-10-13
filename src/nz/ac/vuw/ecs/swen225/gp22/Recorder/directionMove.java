
package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

public class directionMove implements Move {
    public int keyCode = 0;
    App app;

    public directionMove(App a, int code) {
        keyCode = code;
        app = a;
    }

    public directionMove(int code) {
        keyCode = code;
        // use extra constructor for RecordLoad

    }
    /**
     * Saves move as an XML element
     * 
     * @return an XML element
     
    @Override
    public Element saveXML() {
        return new BaseElement("move").addAttribute("moveKeyCode", String.valueOf(keyCode)).addAttribute("app",
                String.valueOf(app));
    }
    
    */

    /**
     * Carries out the move for this move
     */
    @Override
    public void move() {
        Controller c = app.getGame().phase().controller();
        c.keyPressed(keyCode);
    }

    public int getKeyCode(){
        return keyCode;
    }

    @Override
    public void undo() {
        // not yet handled

    }

    public App getApp() {
        return this.app;
    }

}
