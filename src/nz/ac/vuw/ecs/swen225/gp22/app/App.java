package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.renderer.GamePanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.ScoreBoardPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.StartPanel;

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
    }
    
    public void mainMenu(){
        newPanel.run();
        var p = new JPanel();
        p.setBounds(0, 0, 900, 700);
        var tutorial = new JButton("Tutorial");
        var start = new JButton("Start!");

        // Added by Ella -> sets the starting image
        // Need to play around to find out how to layer the buttons on top of the image :)
        // Also I need to find out how to make it not pixelated 

        tutorial.addActionListener((e)->{ tutorial();});
        start.addActionListener((e)->{ game();});

        add(BorderLayout.CENTER, p);
        p.add(tutorial);
        p.add(start);
        p.add(GamePanel.getStartPanel());
        newPanel = ()->{ remove(p);};
        setPreferredSize(new Dimension(900, 750));
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
        newPanel.run();

        JFrame frame  = new JFrame("Score Board Frame");
		frame.add(ScoreBoardPanel.getScoreBoard());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(900, 700));
		frame.setLayout(null);
		frame.setVisible(true);

        var p = new JPanel();
        var back = new JButton("Back");

        back.addActionListener((e)->{ mainMenu();});

        add(BorderLayout.CENTER, p);

        frame.add(back);
        //p.add(ScoreBoardPanel.getScoreBoard());

        newPanel = ()->{ remove(p);};

        pack();
    }

}
