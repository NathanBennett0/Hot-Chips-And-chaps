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
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.domain.DeadState;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filereader;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filewriter;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;
import nz.ac.vuw.ecs.swen225.gp22.renderer.EndPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.ScoreBoardPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.SoundEffects;
import nz.ac.vuw.ecs.swen225.gp22.renderer.StartPanel;
import java.awt.Color;

public class App extends JFrame {
	
    //Initializing Constants
	public final static int WIDTH = 900;
	public final static int HEIGHT = 680;
    public final static int TIMELIMIT_ONE = 60000; //60000 = 1min
    public final static int TIMELIMIT_TWO = 120000; //180000 = 2min

    //Game variables
	private Game game;
    private Timer timer;
    private JPanel currentPanel;
    private int timeLeft;  //timeleft
    private Controller mainController = new Controller(this);
    private SoundEffects sound = new SoundEffects();
    private Controller gameController;
    private int status = 0; //0 = menu, -1 = endgame

    //Menu Items - do these need to stay here? //TODO
    private JMenuBar mb=new JMenuBar();
    private JMenuItem home=new JMenuItem("Home");
    private JMenu start=new JMenu("Go to");
    private JMenuItem lvl1=new JMenuItem("Level 1");
    private JMenuItem lvl2=new JMenuItem("Level 2");
    private JMenuItem load=new JMenuItem("Load Game");
    private JMenuItem pause=new JMenuItem("Pause");
    private JMenuItem save=new JMenuItem("Save");  
    private JMenuItem exit=new JMenuItem("Exit");  

    //Boolean variables for fuzz testing
    private boolean fuzzStarted = false;
    private boolean initializeDone = false;

    private boolean stopTimer = true;
    private boolean pauseTimer = false;

    Runnable restart = ()->{ stopTimer = true;};
    Runnable newPanel = ()->{};

    //TODO: possibly run panels
    Runnable pauseGame = ()->{ 
        pauseTimer = true; 
        pause.setText("Resume");
        mainController.clearKeyBind();
        mainController.setPauseKey();
        changeKeyListener(mainController);
    };  
    Runnable resumeGame = ()->{ 
        pauseTimer = false; 
        pause.setText("Pause");
        gameController.setChapKey();
        changeKeyListener(gameController);
    };

    public App(){
    	System.out.println("App.java: App constructor called.");
        //assert SwingUtilities.isEventDispatchThread();
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
        status = 0;

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
        
        currentPanel = p;
        mainController.clearKeyBind();
        changeKeyListener(mainController);
        newPanel = ()->{ 
            remove(p); 
            sound.stopStart(); 
        };
        pack();
        sound.playStart();
        currentPanel.requestFocus(); //TODO add these in new panel runnable
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
        newPanel = ()->{
            remove(p);
            sound.stopStart();
        };
        currentPanel = p;
        pack();
    }
    
    public void menuBar() {
    	System.out.println("App.java: menuBar() called.");
    	JFileChooser loadsave = new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/");

    	home.setPreferredSize(new Dimension(180, home.getPreferredSize().height));
    	start.setPreferredSize(new Dimension(180, start.getPreferredSize().height));
    	pause.setPreferredSize(new Dimension(180, pause.getPreferredSize().height));
    	save.setPreferredSize(new Dimension(180, save.getPreferredSize().height));
    	exit.setPreferredSize(new Dimension(180, exit.getPreferredSize().height));
    	lvl1.setPreferredSize(new Dimension(180, lvl1.getPreferredSize().height));
    	
    	//FUNCTIONS
    	home.addActionListener((e)->mainMenu()); //connect to the main menu pane - pop up window asking what to do
    	pause.addActionListener((e)->{
            if(!fuzzStarted)return;
            if(pauseTimer){resumeGame.run();}
            else{pauseGame.run();}
        }); //pop up window and pause timer and disable every action (change focus)
        lvl1.addActionListener((e)->levelOne());
        lvl2.addActionListener((e)->levelTwo());
        save.addActionListener((e)->saveGame());
        load.addActionListener((e)->loadSavedGame(loadsave));
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
    
    void setPhase(Phase phase, int time){
    	System.out.println("App.java: setPhase() called.");
        newPanel.run();
        stopTimer = false;
        game = new Game(phase, this);
        
        game.setFocusable(true);
        timeLeft = time;
       
        ActionListener countDown = new ActionListener(){
		    public void actionPerformed(ActionEvent e){
                SimpleDateFormat df=new SimpleDateFormat("mm:ss");

                if(!pauseTimer) timeLeft -= 250;

		        game.tLeft.setText(df.format(timeLeft));
                game.itemLeft.setText(Integer.toString(phase.maze().numOfTreasures()));
		        game.repaint();

		        if(timeLeft<=0 || stopTimer){
		        	((Timer)e.getSource()).stop();
                    endPhase(phase);
		        }
		    }
		};
		
		timer=new Timer(250, countDown);
        getContentPane().add(BorderLayout.CENTER, game);
        
        currentPanel = game;
        changeKeyListener(phase.controller());
        
        newPanel = ()->{
            remove(game);
            timer.stop();
            sound.stopGameMusic();
        };
        pack();
        sound.playGameMusic();
        currentPanel.requestFocus();
        timer.start();
        fuzzStarted = true; // For fuzz testing
    }

    void endPhase(Phase phase){
        newPanel.run();
        restart.run();
        status = -1;

        phase.level().getChap().changeState(new DeadState()); //dead chap

        var p = new JPanel();
        EndPanel ep = new EndPanel();

        var play = new JButton("Restart");
        play.setBounds(400, 580, 100, 30);
        play.addActionListener((e)->{ levelOne();});

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(play);
        p.add(ep);

        currentPanel = p;
        mainController.clearKeyBind();
        changeKeyListener(mainController);
        newPanel = ()->{remove(p);};
        pack();
        currentPanel.requestFocus();
    }

    //TODO: can improve the coding here - reduce redundancy
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     */
    public void levelOne() {
        System.out.println("loading lvl 1");
        status = 1;
        Level lvl = new Filereader().loadLevel("level1.xml");
        Maze m = new Maze(lvl, 22, 22);
        lvl.getChap().setMaze(m); 
        // now have the maze object
        gameController = new Controller(this, lvl.getChap());
        setPhase(new Phase(m, gameController, lvl), TIMELIMIT_ONE);
    }
    
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     * 
     */
    public void levelTwo() {
        System.out.println("loading lvl 2");
        status = 2;
        Level lvl = new Filereader().loadLevel("level2.xml");
        Maze m = new Maze(lvl, 66, 66);
        lvl.getChap().setMaze(m); 
        // now have the maze object
        gameController = new Controller(this, lvl.getChap());
        setPhase(new Phase(m, gameController, lvl), TIMELIMIT_TWO);
    }

    /**
     * Saves game into an xml file
     */
    public void saveGame() {
        System.out.println("saving game");
        System.out.println("time is " + timeLeft);
        String levelname = status==2?"level2":"level1";
        Filewriter fw = new Filewriter(game.phase().maze().getLevel(), timeLeft); //TODO: undo after pull
        fw.saveToXML(levelname + "save");
    }

    public void loadSavedGame(JFileChooser jfc) {
        System.out.println("loading saved game");
        int j = jfc.showOpenDialog(null);
        //check user opened a file
        String filename = "";
        if(j == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getName();
            System.out.println("loading " + filename);
        }

        Level lvl = new Filereader().loadLevel(filename);
        Maze m = new Maze(lvl, 22, 22); //TODO: have to change, consult nathan
        lvl.getChap().setMaze(m); 
        // now have the maze object
        gameController = new Controller(this, lvl.getChap());
        setPhase(new Phase(m, gameController, lvl), lvl.getTime()); //TODO: undo after pull
    }
    
    public Game getGame() {
    	return this.game;
    }

    public Timer getTimer(){
        return this.timer;
    }

    public int getStatus(){
        return this.status;
    }
    
    public void changeKeyListener(KeyListener keyListener) {
        System.out.println("changing key listener to..."+ keyListener);
        if (currentPanel.getKeyListeners().length > 0) {
            currentPanel.removeKeyListener(currentPanel.getKeyListeners()[0]);
        }
        currentPanel.addKeyListener(keyListener);
        currentPanel.setFocusable(true);
    }
    
    
    /*--- FOR TESTING -------------------------------------------------------*/
    
    // GETTERS AND SETTERS FOR FUZZ BOOLEANS
    public boolean fuzzStarted() { return fuzzStarted; }
    public boolean initializeDone() { return initializeDone; }
    public void setFuzzStarted(boolean b) { this.fuzzStarted = b; }
    public void setInitializeDone(boolean b) { this.initializeDone = b; }



}
