package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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
    private Color themeColor = new Color(83, 132, 181);

    //Menu Items - do these need to stay here? //TODO
    private JMenuBar menuBar=new JMenuBar();
    private JMenuItem home=new JMenuItem("Home");
    private JMenu start=new JMenu("Start");
    private JMenuItem lvl1=new JMenuItem("Level 1");
    private JMenuItem lvl2=new JMenuItem("Level 2");
    private JMenuItem load=new JMenuItem("Load Game");
    private JMenuItem resume=new JMenuItem("Resume");
    private JMenuItem pause=new JMenuItem("Pause");
    private JMenuItem save=new JMenuItem("Save");  
    private JMenuItem exit=new JMenuItem("Exit");  
    private JDialog dialogWindow =  new JDialog(this, "dialog Box");

    //Boolean variables for fuzz testing
    private boolean fuzzStarted = false;
    private boolean initializeDone = false;

    private boolean stopTimer = true;
    private boolean pauseTimer = false;

    Runnable restart = ()->{ stopTimer = true;};
    Runnable newPanel = ()->{};

    //TODO: possibly run panels
    Runnable pauseGame = ()->{ 
        dialogWindow.setVisible(true);
        currentPanel.setFocusable(false);
        pauseTimer = true; 
        pause.setText("Resume");
        mainController.clearKeyBind();
        mainController.setPauseKey();
        changeKeyListener(mainController);
    };  
    Runnable resumeGame = ()->{ 
        currentPanel.setFocusable(true);
        dialogWindow.setVisible(false);
        pauseTimer = false; 
        pause.setText("Pause");
        gameController.setChapKey();
        changeKeyListener(gameController);
    };

    public App(){
    	System.out.println("App.java: App constructor called.");
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit on close
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        initialize();
        this.addWindowListener(new WindowAdapter(){  //check
            public void windowClosed(WindowEvent e){restart.run(); System.exit(0);}
        });
    }

    /**
     * Initializing the game variables
     */
    public void initialize(){
    	
    	//this.setSize(new Dimension(600,800));
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("GAME TITLE"); //come back

       
        menuItemActions();
        prepareDialogWindow();
        home();
        initializeDone = true; // For fuzz testing
    }
    
    /*-----	GUI	-------------------------------------------------------------*/
    
    public void menuItemActions(){
         //MENU BAR FUNCTIONS
         JFileChooser loadsave = new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/");
         pause.addActionListener((e)->{
             if(!fuzzStarted)return;
             if(pauseTimer){resumeGame.run();}
             else{pauseGame.run();} 
         }); 
         lvl1.addActionListener((e)->levelOne());
         lvl2.addActionListener((e)->levelTwo());
         save.addActionListener((e)->saveGame());
         load.addActionListener((e)->loadSavedGame(loadsave));
         resume.addActionListener((e)->resumeGame());
         exit.addActionListener((e)->System.exit(0));
         start.add(lvl1);
         start.add(lvl2);
         menuBar.setBounds(0,0,WIDTH,20);
    }

    public void prepareDialogWindow(){
        dialogWindow.setTitle("Game Paused");
        dialogWindow.setSize(new Dimension(200,400));
        dialogWindow.setResizable(false);
        addAButton("Home", ()->home(), dialogWindow);
        addAButton("Resume", ()->resumeGame.run(), dialogWindow);
    }

    public void addAButton(String text, Runnable function, JDialog container){
        JButton button = new JButton(text);
        //button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((e)->function.run());
        container.add(button);
    }
    
    public JMenuBar mainMenu(){
        menuBar.removeAll();
        start.setPreferredSize(new Dimension(WIDTH/4, start.getPreferredSize().height));
    	load.setPreferredSize(new Dimension(WIDTH/4, load.getPreferredSize().height));
    	resume.setPreferredSize(new Dimension(WIDTH/4, resume.getPreferredSize().height));
    	exit.setPreferredSize(new Dimension(WIDTH/4, exit.getPreferredSize().height));
        lvl2.setPreferredSize(new Dimension(WIDTH/4, lvl2.getPreferredSize().height));

        menuBar.add(start);
        menuBar.add(load);
        menuBar.add(resume);
        menuBar.add(exit);
        return menuBar;
    }

    public JMenuBar gameMenu(){
        menuBar.removeAll();
    	start.setPreferredSize(new Dimension(WIDTH/4, start.getPreferredSize().height));
    	pause.setPreferredSize(new Dimension(WIDTH/4, pause.getPreferredSize().height));
    	save.setPreferredSize(new Dimension(WIDTH/4, save.getPreferredSize().height));
    	exit.setPreferredSize(new Dimension(WIDTH/4, exit.getPreferredSize().height));

    	start.add(load);
    	menuBar.add(start);
        menuBar.add(pause);
        menuBar.add(save);
    	menuBar.add(exit);
        return menuBar;
    }

    public void home(){

        newPanel.run();
        restart.run();
        status = 0;

        var p = new JPanel();
        StartPanel sp = new StartPanel();
        sp.setBounds(0,20,WIDTH, HEIGHT-20);
        
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
        p.add(mainMenu());

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
        newPanel.run();
        restart.run();
        this.setBounds(0,0, App.WIDTH, App.HEIGHT);

		

        var p = new JPanel();
        p.setLayout(null);

        var back = new JButton("Back");
        back.setBounds(150, 580, 100, 30);

        var next = new JButton("Next");

        JLabel instruction = new JLabel();
        Image scaled = Img.Tutorial.image.getScaledInstance(App.WIDTH,App.HEIGHT-20,Image.SCALE_SMOOTH);
        ImageIcon page1 = new ImageIcon(scaled);
		instruction.setIcon(page1);
        instruction.setBounds(0,20,WIDTH, HEIGHT-20);
        
        p.add(back);
        p.add(instruction);
        p.add(mainMenu());

        back.addActionListener((e)->{ home();});

        getContentPane().add(BorderLayout.CENTER, p);
        newPanel = ()->{
            remove(p);
            sound.stopStart();
        };
        currentPanel = p;
        pack();
    }

    void endPhase(Phase phase){
        newPanel.run();
        restart.run();
        status = -1;

        phase.level().getChap().changeState(new DeadState()); //dead chap

        var p = new JPanel();
        EndPanel ep = new EndPanel();
        ep.setBounds(0,20,WIDTH, HEIGHT-20);

        var play = new JButton("Restart");
        play.setBounds(400, 580, 100, 30);
        var home = new JButton("Home");
        home.setBounds(400, 550, 100, 30);

        play.addActionListener((e)->{ levelOne();});
        home.addActionListener((e)->{ home();});

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(play);
        p.add(home);
        p.add(ep);
        p.add(mainMenu());

        currentPanel = p;
        mainController.clearKeyBind();
        changeKeyListener(mainController);
        newPanel = ()->{remove(p);};
        pack();
        currentPanel.requestFocus();
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
                try {
                    game.itemLeft.setText(Integer.toString(phase.maze().numOfTreasures()));
                } catch (IOException e1) { e1.printStackTrace();}
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
        gameMenu();
        game.add(menuBar);
        pack();
        sound.playGameMusic();
        currentPanel.requestFocus();
        timer.start();
        fuzzStarted = true; // For fuzz testing
    }

    //TODO: can improve the coding here - reduce redundancy
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     */
    public void levelOne() {
        System.out.println("loading lvl 1");
        status = 1;
        Level lvl = new Filereader().loadLevel("level1.xml");
        Maze m;
        try {
            m = new Maze(lvl, 22, 22);
            lvl.getChap().setMaze(m); 
            // now have the maze object
            gameController = new Controller(this, lvl.getChap());
            setPhase(new Phase(m, gameController, lvl), TIMELIMIT_ONE);
        } catch (IOException e) {  e.printStackTrace(); }
    }
    
    /**
     * Loads the level 1 of the game and creates the Maze accordingly
     * 
     */
    public void levelTwo() {
        System.out.println("loading lvl 2");
        status = 2;
        Level lvl = new Filereader().loadLevel("level2.xml");
        Maze m;
        try {
            m = new Maze(lvl, 66, 66);
            lvl.getChap().setMaze(m); 
            // now have the maze object
            gameController = new Controller(this, lvl.getChap());
            setPhase(new Phase(m, gameController, lvl), TIMELIMIT_TWO);
        } catch (IOException e) { e.printStackTrace(); }
        
    }

    /**
     * Saves game into an xml file
     */
    public void saveGame() {
        System.out.println("saving game");
        Filewriter fw = new Filewriter(game.phase().maze().getLevel(), timeLeft); //TODO: undo after pull
        fw.saveToXML("lastSaved");
    }

    public void resumeGame() {
        Level lvl = new Filereader().loadLevel("lastSaved.xml");
        Maze m;
        try {
            m = new Maze(lvl, 22, 22);
            lvl.getChap().setMaze(m); 
            // now have the maze object
            gameController = new Controller(this, lvl.getChap());
            setPhase(new Phase(m, gameController, lvl), lvl.getTime()); //TODO: undo after pull
        } catch (IOException e) { e.printStackTrace(); } //TODO: have to change, consult nathan
    }

    public void loadSavedGame(JFileChooser jfc) {
        int j = jfc.showOpenDialog(null);
        //check user opened a file
        String filename = "";
        if(j == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getName();
            System.out.println("loading " + filename);
        }

        Level lvl = new Filereader().loadLevel(filename);
        Maze m;
        try {
            m = new Maze(lvl, 22, 22);
            lvl.getChap().setMaze(m); 
            status = 1; //TODO: level
            // now have the maze object
            gameController = new Controller(this, lvl.getChap());
            setPhase(new Phase(m, gameController, lvl), lvl.getTime()); //TODO: undo after pull
        } catch (IOException e) { e.printStackTrace();} //TODO: have to change, consult nathan
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
