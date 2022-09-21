package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements ActionListener{
    final JMenuItem menuItem = new JMenuItem("Name");
    
    MenuBar(){
        JMenu menuBar = new JMenu("Test");
        JMenuItem t1 = new JMenuItem("test1");
        JMenuItem exit = new JMenuItem("exit");
        menuBar.add(t1);
        menuBar.add(exit);

        t1.addActionListener(e->System.out.println("test menu"));
        exit.addActionListener(e->System.exit(0));

        exit.setMnemonic(KeyEvent.VK_E);

        this.add(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
