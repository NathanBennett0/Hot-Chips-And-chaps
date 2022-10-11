package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

public record doorMove(String door, String color, int x, int y) implements Move{

    @Override
    public Element saveXML() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void undo() {
        Element d = new BaseElement("door").addAttribute("door", door).addAttribute("x", String.valueOf(x)).addAttribute("y", String.valueOf(y));

        if(!color.equals("none")){
            d.addAttribute("color", color);

        }
        
    }

}
