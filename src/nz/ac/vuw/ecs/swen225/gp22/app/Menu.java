package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
    final JMenuItem menuItem = new JMenuItem("Name");
    
    Menu(){
        JMenu menuBar = new JMenu("Test");
        menuBar.add(new JMenuItem("test1"));

        this.add(menuBar);

    }
}
