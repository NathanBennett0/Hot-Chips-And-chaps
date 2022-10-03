package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
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

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filereader;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filewriter;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.ScoreBoardPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.StartPanel;
import java.awt.Color;

public class App extends JFrame {
	
    //Initializing Variables
	public final static int WIDTH = 900;
	public final static int HEIGHT = 680;
	Game game;
    Runnable newPanel = ()->{};
    
    
    private boolean stopTimer = true;
    private boolean running = false;
    
    //Boolean variables for fuzz testing
    private boolean fuzzStarted = false;
    private boolean initializeDone = false;
    Runnable restart = ()->{ stopTimer = true;};

    public App(){
    	System.out.println("App.java: App constructor called.");
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit on close
        initialize();
        this.addWindowListener(new WindowAdapter(){  //check
            public void windowClosed(WindowEvent e){restart.run();}
        });
    }

    /**
     * Initializing the game variables
     */
    public void initialize(){
    	System.out.println("App.java: initialize() called.");
       
    	//this.setSize(new Dimension(600,800));
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("GAME TITLE"); //come back

        menuBar(); //adding MenuBar
        mainMenu();
        initializeDone = true; // For fuzz testing
    }
    
    /*-----	GUI	-------------------------------------------------------------*/
    
    public void mainMenu(){
    	System.out.println("App.java: mainMenu() called.");
        newPanel.run();
        restart.run();
        var p = new JPanel();
        StartPanel sp = new StartPanel();
        
        var tutorial = new JButton("Tutorial");
        tutorial.setBounds(400, 550, 100, 30);
        var play = new JButton("Play!");
        play.setBounds(400, 580, 100, 30);

        tutorial.addActionListener((e)->{ tutorial();});
        play.addActionListener((e)->{ levelOne();});
        

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(tutorial);
        p.add(play);
        p.add(sp);
        
        newPanel = ()->{ remove(p);};
        pack();
    }

    public void tutorial(){
    	System.out.println("App.java: tutorial() called.");
        newPanel.run();
        restart.run();
        
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
    
    public void menuBar() {
    	System.out.println("App.java: menuBar() called.");
    	//DECLARE
    	JMenuBar mb=new JMenuBar();
    	JMenuItem home=new JMenuItem("Home");
    	JMenu start=new JMenu("Go to");
    	JMenuItem lvl1=new JMenuItem("Level 1");
    	JMenuItem lvl2=new JMenuItem("Level 2");
    	JMenuItem load=new JMenuItem("Load Game");
    	JMenuItem pause=new JMenuItem("Pause");
    	JMenuItem save=new JMenuItem("Save");  
    	JMenuItem exit=new JMenuItem("Exit");  
    	
    	home.setPreferredSize(new Dimension(180, home.getPreferredSize().height));
    	start.setPreferredSize(new Dimension(180, start.getPreferredSize().height));
    	pause.setPreferredSize(new Dimension(180, pause.getPreferredSize().height));
    	save.setPreferredSize(new Dimension(180, save.getPreferredSize().height));
    	exit.setPreferredSize(new Dimension(180, exit.getPreferredSize().height));
    	lvl1.setPreferredSize(new Dimension(180, lvl1.getPreferredSize().height));
    	
    	//FUNCTIONS
    	home.addActionListener((e)->mainMenu()); //connect to the main menu pane - pop up window asking what to do
    	pause.addActionListener((e)->System.out.println("Pause")); //pop up window and pause timer and disable every action (change focus)
        lvl1.addActionListener((e)->levelOne());
        lvl2.addActionListener((e)->levelTwo());
        exit.addActionListener((e)->System.exit(0));
    	
    	//ADD TO GUI
    	mb.add(home);
    	start.add(lvl1);
    	start.add(lvl2);
    	start.add(load);
    	mb.add(start);
    	mb.add(pause);
    	mb.add(save);
    	mb.add(exit);
    	this.setJMenuBar(mb);
    }
    
    /*---- GAME MECHANICS ---------------------------------------------------*/
    
    void setPhase(Phase phase){
    	System.out.println("App.java: setPhase() called.");
        newPanel.run();
        stopTimer = false;
        game = new Game(phase, this);
        game.addKeyListener(phase.controller());
        game.setFocusable(true);
       
        ActionListener countDown = new ActionListener(){
        	int timeLeft = phase.level()==2?180000:90000; //level 2: 3 mins, level 1: 1.5 mins
        	
		    public void actionPerformed(ActionEvent e){
		        timeLeft -= 250;
		        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
		        game.tLeft.setText(df.format(timeLeft));
		        game.repaint();
		        if(timeLeft<=0 || stopTimer){
		        	((Timer)e.getSource()).stop();
		        }
		    }
		};
		
		Timer timer=new Timer(250, countDown);
        getContentPane().add(BorderLayout.CENTER, game);
                
        newPanel = ()->{ remove(game);};

        pack();
        timer.start();
        fuzzStarted = true; // For fuzz testing
    }
    
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     * 
     * @param levelname
     * @param num - level
     */
    public void levelOne() {
        System.out.println("loading lvl 1");
        Level lvl = new Filereader().loadLevel("level1.xml");
        Maze m = new Maze(lvl);
        lvl.getChap().setMaze(m); 
        // now have the maze object
        setPhase(new Phase(m, new Controller(this, lvl.getChap()), 1));
    }
    
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     * 
     * @param levelname
     * @param num - level
     */
    public void levelTwo() {
        System.out.println("loading lvl 2");
        Level lvl = new Filereader().loadLevel("level2.xml");
        Maze m = new Maze(lvl);
        lvl.getChap().setMaze(m); 
        // now have the maze object
        setPhase(new Phase(m, new Controller(this, lvl.getChap()), 2));
    }

    /**
     * Saves game into an xml file
     */
    public void saveGame() {
    	String levelname = game.getLevel()==2?"level2":"level1";
    	Level lvl = new Filereader().loadLevel(levelname);
        Filewriter fw = new Filewriter(lvl);
        fw.saveToXML("src/nz/ac/vuw/ecs/swen225/gp22/persistency/" + levelname + ".xml");
    }
    
    public Game getGame() {
    	return this.game;
    }
    
    
    
    /*--- FOR TESTING -------------------------------------------------------*/
    
    // GETTERS AND SETTERS FOR FUZZ BOOLEANS
    public boolean fuzzStarted() { return fuzzStarted; }
    public boolean initializeDone() { return initializeDone; }
    public void setFuzzStarted(boolean b) { this.fuzzStarted = b; }
    public void setInitializeDone(boolean b) { this.initializeDone = b; }



}
