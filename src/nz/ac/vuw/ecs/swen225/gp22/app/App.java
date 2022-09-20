package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.ScoreBoardPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.StartPanel;
import java.awt.Color;

public class App extends JFrame {

    //Initializing Variables
    Runnable newPanel = ()->{};
    public final static int WIDTH = 900;
    public final static int HEIGHT = 680;
    private boolean stopTimer = true;

    App(){
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit on close
        initialize();
    }

    public void initialize(){
        //this.setSize(new Dimension(600,800));
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("GAME TITLE"); //come back

        menuBar(); //adding MenuBar
        mainMenu();
    }
    
    public void mainMenu(){
        newPanel.run();
        var p = new JPanel();
        
        var tutorial = new JButton("Tutorial");
        tutorial.setBounds(400, 550, 100, 30);
        var start = new JButton("Start!");
        start.setBounds(400, 580, 100, 30);

        // Added by Ella -> sets the starting image
        // Need to play around to find out how to layer the buttons on top of the image :)
        // Also I need to find out how to make it not pixelated 
        var backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0, WIDTH, HEIGHT);
        ImageIcon icon = new ImageIcon(Img.StartOne.image);
        backgroundImage.setIcon(icon);

        tutorial.addActionListener((e)->{ tutorial();});
        start.addActionListener((e)->{ game();});

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(tutorial);
        p.add(start);
        p.add(backgroundImage);
        newPanel = ()->{ remove(p);};
        //setPreferredSize(new Dimension(900, 750));
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

        getContentPane().add(BorderLayout.CENTER, p);
        newPanel = ()->{ remove(p);};

        pack();
    }

    public void game(){
        System.out.println("Starting game...");
        newPanel.run();
        stopTimer = false;
        JPanel p = new Game();
        
        //Timer for level 1 - 10 minutes
        ActionListener countdown = new ActionListener() {
        	int sec = 600;
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(sec + "");
				if((sec--) == 0) {
					((Timer)e.getSource()).stop();
				}
				
			}
        };
        Timer timer = new Timer(1000, countdown); //1000 delay = 1s
        
        getContentPane().add(BorderLayout.CENTER, p);
                
        newPanel = ()->{ remove(p);};

        pack();
        timer.start();

    }
    
    public void menuBar() {
    	JMenuBar mb=new JMenuBar();
    	mb.setBackground(new Color(135, 206, 235));
    	JMenuItem home=new JMenuItem("Home");
    	home.setHorizontalAlignment(SwingConstants.CENTER);
    	JMenuItem exit=new JMenuItem("Exit");  
    	home.addActionListener((e)->mainMenu()); //connect to the main menu pane
    	exit.addActionListener((e)->System.exit(0));
    	
    	mb.add(home);
    	JMenu start=new JMenu("Start");
    	start.setHorizontalAlignment(SwingConstants.LEFT);
    	JMenuItem lvl1=new JMenuItem("Level 1");
    	JMenuItem lvl2=new JMenuItem("Level 2");
    	
    	start.add(lvl1);
    	start.add(lvl2);
    	mb.add(start);
    	JMenuItem pause=new JMenuItem("Pause");
    	mb.add(pause);
    	mb.add(exit);
    	this.setJMenuBar(mb);
    }
}
