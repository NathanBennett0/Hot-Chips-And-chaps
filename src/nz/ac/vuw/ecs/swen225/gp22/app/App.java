package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class App extends JFrame {

    //Initializing Variables
    Runnable newPanel = ()->{};

    App(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit on close
        initialize();
    }

    public void initialize(){
        //this.setSize(new Dimension(600,800));
        this.setPreferredSize(new Dimension(800,800));
        this.setVisible(true);
        this.setTitle("GAME TITLE"); //come back

        mainMenu();
        this.setJMenuBar(new MenuBar());
    }
    
    public void mainMenu(){
        newPanel.run();
        
        var p = new JPanel();
        var title = new JLabel("Game title!"); //come back
        var tutorial = new JButton("Tutorial");
        var start = new JButton("Start!");

        p.add(title);
        p.add(tutorial);
        p.add(start);

        tutorial.addActionListener((e)->{ tutorial();});
        start.addActionListener((e)->{ game();});

        add(BorderLayout.CENTER, p);
        newPanel = ()->{ remove(p);};

        pack();
    }

    public void tutorial(){
        newPanel.run();

        var p = new JPanel();
        var msg1=new JLabel("Some Text", SwingConstants.CENTER);
        var back = new JButton("Back");

        p.add(msg1);
        p.add(back);

        back.addActionListener((e)->{ mainMenu();});

        add(BorderLayout.CENTER, p);
        newPanel = ()->{ remove(p);};

        pack();
    }

    public void game(){
        System.out.println("Starting game...");
    }

}
