package nz.ac.vuw.ecs.swen225.gp22.Recorder;
import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;
import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

public record directionMove(App app, int keyCode) implements Move{
    //public record directionMove(App app, String dir, int keyCode) implements Move{

    /**
     * Saves move as an XML element
     * 
     * @return an XML element
     */
    @Override
    public Element saveXML() {
        return new BaseElement("move").addAttribute("moveKeyCode", String.valueOf(keyCode));
    }

    /**
     * Carries out the move for this move
     */
    @Override
    public void move() {
       Controller c = app.getGame().phase().controller();
       c.keyPressed(keyCode);
    }

    @Override
    public void undo() {
        // not yet handled
        
    }
}
