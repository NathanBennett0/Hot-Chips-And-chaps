package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.Recorder.Recorder;
import nz.ac.vuw.ecs.swen225.gp22.Recorder.recorderPanel;
import nz.ac.vuw.ecs.swen225.gp22.domain.DeadState;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filereader;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filewriter;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;
import nz.ac.vuw.ecs.swen225.gp22.renderer.EndPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.GamePanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;
import nz.ac.vuw.ecs.swen225.gp22.renderer.SoundEffects;
import nz.ac.vuw.ecs.swen225.gp22.renderer.StartPanel;
import java.awt.Color;

/**
 * The main GUI for Chap's Challenge.
 * 
 * @author Naomi Parte
 * 
 */
public class App extends JFrame {

    /**
     * A singleton instance for the game.
     * 
     */
	private static App singleton;

    /**
     * Width of the GUI.
     * 
     */
	public final static int WIDTH = 900;

    /**
     * Height of the GUI.
     * 
     */
	public final static int HEIGHT = 680;

    /**
     * Time limit for level one. (1 min)
     * 
     */
    public final static int TIMELIMIT_ONE = 60000; 

    /**
     * Time limit for level two. (2 mins)
     * 
     */
    public final static int TIMELIMIT_TWO = 120000;

    /**
     * Stores the phase the current game is in. Changes as the phase levels change.
     * 
     */
    private Phase phase;

    /**
     * Timer for the game.
     * 
     */
    private Timer timer;

    /**
     * Keeps track of the current active panel.
     * 
     */
    private JPanel currentPanel;

    /**
     * Recorder.
     * 
     */
    Recorder recorder;

    /**
     * Keeps track of time left
     * 
     */
    private int timeLeft;  

    /**
     * Normal controller with game functionalities.
     * 
     */
    private Controller mainController = new Controller(this);

    /**
     * Game controller that moves chap.
     * 
     */
    private Controller gameController;

    /**
     * Sound effect for the game.
     * 
     */
    private SoundEffects sound = new SoundEffects();

    /**
     * Stores themecolor.
     * 
     */
    private Color themeColor = new Color(13, 59, 94);

    /**
     * Menubar for the game.
     * 
     */
    private JMenuBar menuBar=new JMenuBar();

    /**
     * Start menu, stores level menu items.
     * 
     */
    private JMenu start=new JMenu("Start");

    /**
     * Menu Item variables.
     * 
     */
    private JMenuItem lvl1, lvl2, load, resume, pause, save, exit;

    /**
     * Dialog window for when the game is paused.
     * 
     */
    private JDialog dialogWindow =  new JDialog(this, "Menu");

    /**
     * Boolean for fuzz testing.
     * 
     */
    private boolean fuzzStarted = false;

    /**
     * Initialization for fuzz testing.
     * 
     */
    private boolean initializeDone = false;

    /**
     * Keeps track of when to stop timer. Only turns on when the game play begins.
     * 
     */
    private boolean stopTimer = true;

    /**
     * Keeps track of game pause and resume.
     * 
     */
    private boolean pauseTimer = false;

    /**
     * Keeps track of whether the gameplay is running or not.
     * 
     */
    public boolean runningGame = false;
 
    /**
     * JFileChooser with existing path to where xml files are stored.
     * 
     */
    JFileChooser loadsave = new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/persistency/"); 

    /**
     * Initialzes boolean variables.
     * 
     */
    Runnable restart = ()->{ stopTimer = true; pauseTimer = false; runningGame = false;};

    /**
     * Sets of codes to run everytime we go to a new panel.
     * 
     */
    Runnable newPanel = ()->{};

    /**
     * Stores actions needed for when the game is paused.
     * 
     */
    Runnable pauseGame = ()->{ 
        // showing dialog window
        dialogWindow.setVisible(true);
        dialogWindow.setFocusable(true);
        currentPanel.setFocusable(false);

        // changing controller
        mainController.clearKeyBind();
        mainController.setPauseKey();
        dialogWindow.addKeyListener(mainController);
        dialogWindow.requestFocus();

        pauseTimer = true; 
        pause.setText("Resume");

        //sets current panel's controller to null so that chap can't be controlled while game is paused
        changeKeyListener(null); 
    };  

    /**
     * Stores actoins needed for resuming the game.
     * 
     */
    Runnable resumeGame = ()->{ 
        currentPanel.setFocusable(true);
        dialogWindow.setVisible(false);
        currentPanel.requestFocus();
        pauseTimer = false; 
        pause.setText("Pause");
        gameController.setChapKey();
        changeKeyListener(gameController);
    };

    /**
     * Stores actions for exiting game.
     * 
     */
    Runnable exitGame = ()->{
        restart.run();
        System.exit(0);
    };

    /**
     * App instance constructor.
     * 
     */
    private App(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        initialize();
        this.addWindowListener(new WindowAdapter(){ 
            public void windowClosed(WindowEvent e){exitGame.run();}
        });
    }

    /**
     * Returns app singleton instance.
     * 
     * @return App
     */
    public static App getInstance(){
        if(singleton == null) singleton = new App();
        return singleton;
    }

    /**
     * Initializing the game variables.
     * 
     */
    public void initialize(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Hot Chips and Chaps");
       
        menuItems();
        prepareDialogWindow();
        home();
        initializeDone = true; // For fuzz testing
    }

    /**
     * GUI - Initializing the dialog window for pause.
     * 
     */
    public void prepareDialogWindow(){
        dialogWindow.setPreferredSize(new Dimension(240,310));
		dialogWindow.getContentPane().setBackground(themeColor);
        dialogWindow.setLocation(WIDTH/2 - 240/2, HEIGHT/2 - 310/2);
        dialogWindow.setLayout(null);
        dialogWindow.setResizable(false);
        dialogWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                resumeGame.run();
            }
        });

        JLabel lblNewLabel = new JLabel("Game Paused");
        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblNewLabel.setForeground(new Color(255,255,255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(20, 5, 200, 50);
        dialogWindow.add(lblNewLabel);

        addAButton("Home", ()->home(), 1, dialogWindow);
        addAButton("Resume", ()->resumeGame.run(), 2,  dialogWindow);
        addAButton("Save & Exit", ()->{saveGame(); exitGame.run();}, 3,  dialogWindow);
        addAButton("Exit", ()->exitGame.run(), 4, dialogWindow);

        dialogWindow.pack();
    }

    /**
     * GUI - Helper method for prepareDialogWindow - adds buttons.
     * 
     * @param text
     * @param function
     * @param i
     * @param container
     */
    public void addAButton(String text, Runnable function, int i, JDialog container){
        JButton button = new JButton(text);
        button.addActionListener((e)->{function.run(); container.setVisible(false);});
        button.setBounds(20, 52*i, 200, 50);
        container.add(button);
    }
    
    /**
     * GUI - Initialzing menu item initilizations and actions.
     * 
     */
    public void menuItems(){
        // MENU BAR DISPLAY
        lvl1 = new JMenuItem("Level 1");
        lvl2 = new JMenuItem("Level 2");
        load = new JMenuItem("Load Game");
        resume = new JMenuItem("Resume Game");
        pause = new JMenuItem("Pause");
        save = new JMenuItem("Save");  
        exit = new JMenuItem("Exit");  

        // MENU BAR FUNCTIONS
        pause.addActionListener((e)->{
            if(pauseTimer){resumeGame.run();}
            else{pauseGame.run();} 
        }); 
        lvl1.addActionListener((e)->phaseOne());
        lvl2.addActionListener((e)->phaseTwo());
        save.addActionListener((e)->saveGame());
        load.addActionListener((e)->loadSavedGame(loadsave));
        resume.addActionListener((e)->resumeGame());
        exit.addActionListener((e)->exitGame.run());
        start.add(lvl1);
        start.add(lvl2);
        menuBar.setBounds(0,0,WIDTH,20);
    }
    
    /**
     * GUI - The main menubar.
     * 
     * @return menu bar
     *  
     */
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

    /**
     * GUI - MenuBar for when the game is running.
     * 
     * @return menu bar
     * 
     */
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

    /**
     * GUI - The home panel.
     * 
     */
    public void home(){
        newPanel.run();
        restart.run();

        var p = new JPanel();
        StartPanel sp = new StartPanel();
        sp.setBounds(0,20,WIDTH, HEIGHT-20);
        
        pause.setText("Pause");
        var replay = new JButton("Recorder");
        replay.setBounds(400, 520, 100, 30);
        var tutorial = new JButton("Tutorial");
        tutorial.setBounds(400, 550, 100, 30);
        var play = new JButton("New Game");
        play.setBounds(400, 580, 100, 30);

        replay.addActionListener((e)->{recorderGame();}); //replay() TODO
        tutorial.addActionListener((e)->{ tutorial();});
        play.addActionListener((e)->{ phaseOne();});

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(tutorial);
        p.add(replay);
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
        currentPanel.requestFocus();
    }

    /**
     * GUI - The help/tutorial panel.
     * 
     */
    public void tutorial(){
        newPanel.run();
        restart.run();

        var p = new JPanel();
        p.setLayout(null);

        var back = new JButton("Back");
        back.setBounds(40, 580, 100, 30);

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

    /**
     * GUI - Ending phase panel - appears when chap dies.
     * 
     * @param phase 
     * 
     */
    void endPhase(Runnable phase, boolean status){
        if(status) JOptionPane.showConfirmDialog(this, "You Win!", "Congrats", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);   

        newPanel.run();
        restart.run();

        var p = new JPanel();
        EndPanel ep = new EndPanel(status);
        ep.setBounds(0,20,WIDTH, HEIGHT-20);

        var play = new JButton("Replay");
        play.setBounds(400, 580, 100, 30);
        var home = new JButton("Home");
        home.setBounds(400, 550, 100, 30);

        play.addActionListener((e)->{ phase.run();}); // restarts the last level
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
        newPanel = ()->{
            remove(p);
            sound.stopStart();
        };
        pack();
        sound.playStart();
        currentPanel.requestFocus();
    }

    /**
     * GUI - Recorder Panel.
     * 
     */
    public void replay(){
        newPanel.run();
        restart.run();
        this.setBounds(0,0, App.WIDTH, App.HEIGHT);

        recorderPanel rp = new recorderPanel(this);
        getContentPane().add(BorderLayout.CENTER, rp);

        rp.setVisible(true);
        pack();
        newPanel = ()->{
            remove(rp);
        };
        currentPanel = rp;
        pack();
        rp.requestFocus();
    }

    /**
     * Game panel for recorder.
     * 
     */
    public void recorderGame(){
        newPanel.run();
        restart.run();

        recorderPanel rp = new recorderPanel(this);

        Phase phase = Phase.replayPhase(1); //recorder.level()
        this.phase = phase;
        gameController = phase.controller();

        var panel = new JPanel();
        panel.setBackground(themeColor);
        panel.setLayout(null);
        var back = new JButton("Home");
        back.setBounds(400, 600, 100, 30);
        back.addActionListener((e)->{ home();});

        GamePanel viewport = new GamePanel(phase.maze(), this);
        viewport.setBounds(170,30,558,558);
        
        panel.add(viewport);
        panel.add(back);
        panel.add(mainMenu());

        currentPanel = panel;
        changeKeyListener(phase.controller());

        getContentPane().add(panel);
        newPanel = ()->{
            remove(panel);
        };
        pack();
        currentPanel.requestFocus();
    }

    /**
     * Sets the phase and starts the levels.
     * 
     * @param phase
     * @param time - time left
     * 
     */
    public void setPhase(Phase phase, int time){
        newPanel.run();
        stopTimer = false;
        runningGame = true;
        gameController = phase.controller();
        this.phase = phase;
        Game game = new Game(phase, this);
        recorder = new Recorder(phase.maze().getLevel().getLevel());
        
        game.setFocusable(true);
        timeLeft = time;
       
        // Timer Action
        ActionListener countDown = new ActionListener(){
            
		    public void actionPerformed(ActionEvent e){
                boolean lost = false;
                
                SimpleDateFormat df=new SimpleDateFormat("mm:ss");
                if(!pauseTimer) timeLeft -= 250;

                // CALL ACTOR
                if(timeLeft % 4 == 0 && !pauseTimer){
                    if(phase.maze().getLevel().getLevel() == 2) phase.maze().getLevel().getActor().moveRandomly();
                    if(phase.maze().getChap().s instanceof DeadState) lost = true;
                }

		        game.tLeft.setText(df.format(timeLeft));
                game.itemLeft.setText(Integer.toString(phase.maze().numOfTreasures()));
		        game.repaint();

                // WIN
                if(phase.maze().getLevel().getChap().won()){
                    recorder.saveMove(); 
                    runningGame = false;
                    Phase.next.run();
                }

                // LOSE
		        if(timeLeft<=0){
                    phase.maze().getLevel().getChap().changeState(new DeadState()); // dead chap
                    lost = true;
		        }
                if(lost){
                    stopTimer = true;
                    Phase.first.run();
                    recorder.saveMove(); 
                }
                    
                if(stopTimer) ((Timer)e.getSource()).stop();
                
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
            runningGame = false;
        };

        game.add(gameMenu());
        pack();
        sound.playGameMusic();
        currentPanel.requestFocus();
        timer.start();
        fuzzStarted = true; // For fuzz testing
    }

    /**
     * Helper method - asks the user if they want to save the game.
     * 
     */
    public void confirmSave(){
        if(!runningGame) return;
        int result = JOptionPane.showConfirmDialog(this, "Do you want to save the current game?", "Notice", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.YES_OPTION) saveGame();
    }

    /**
     * Setting phase one/ level 1 of the game.
     * 
     */
    public void phaseOne() {
        confirmSave();
        setPhase(Phase.levelOne(()->phaseTwo(), ()->endPhase(()->phaseOne(), false)), TIMELIMIT_ONE); 
    }
    
    /**
     * Setting phase two/ level 2 of the game.
     * 
     */
    public void phaseTwo() {
        confirmSave();
        setPhase(Phase.levelTwo(()->endPhase(()->phaseOne(),true), ()->endPhase(()->phaseTwo(), false)), TIMELIMIT_TWO); 
    }

    /**
     * Saves game into an xml file.
     * 
     */
    public void saveGame() {
        System.out.println("saving game");
        Filewriter fw = new Filewriter(phase.maze().getLevel(), timeLeft);
        fw.saveToXML("levels/lastSaved");
    }

    /**
     * Resumes the lastest saved game.
     * 
     */
    public void resumeGame() {
        Level lvl = new Filereader().loadLevel("levels/lastSaved.xml");
        if(lvl == null) return;
        Maze m;
        try {
            int size = lvl.getLevel()>=2?66:22;
            m = new Maze(lvl, size, size);
            lvl.getChap().setMaze(m); 
            setPhase(new Phase(m, new Controller(this, lvl.getChap())), lvl.getTime());
        } catch (IOException e) { e.printStackTrace(); } 
    }

    /**
     * Loads a chosen saved game.
     * 
     * @param jfc - filechooser
     * 
     */
    public void loadSavedGame(JFileChooser jfc) {
        int j = jfc.showOpenDialog(null);
        //check user opened a file
        String filename = "";
        if(j == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getName();
        }else{
            return;
        }
        Level lvl = new Filereader().loadLevel("levels/" + filename);
        Maze m;
        try {
            int size = lvl.getLevel()>=2?66:22;
            m = new Maze(lvl, size, size);
            lvl.getChap().setMaze(m); 
            // now have the maze object
            gameController = new Controller(this, lvl.getChap());
            setPhase(new Phase(m, gameController), lvl.getTime());
        } catch (IOException e) { e.printStackTrace();} 
    }


    /**
     * Getter for phase.
     * 
     * @return phase
     */
    public Phase getPhase(){
        return this.phase;
    }

    /**
     * Getter for timer.
     * 
     * @return timer
     * 
     */
    public Timer getTimer(){
        return this.timer;
    }
    
    /**
     * Changes key Listener.
     * 
     * @param keyListener
     * 
     */
    public void changeKeyListener(KeyListener keyListener) {
        if (currentPanel.getKeyListeners().length > 0) {
            currentPanel.removeKeyListener(currentPanel.getKeyListeners()[0]);
        }
        currentPanel.addKeyListener(keyListener);
        currentPanel.setFocusable(true);
    }
    
    // GETTERS AND SETTERS FOR FUZZ BOOLEANS
    public boolean fuzzStarted() { return fuzzStarted; }
    public boolean initializeDone() { return initializeDone; }
    
    /**
     * Locates the chap on the maze and updates the current position.
     * 
     * @param m Maze object found from App.java
     * 
     */
    public int updateLocationX() {
        Maze m = phase.maze();
        if(m.player.l == null) return -1;
        return m.player.l.getX();
    }

    /**
     * Locates the chap on the maze and updates the current position.
     * 
     * @param m Maze object found from App.java
     * 
     */
    public int updateLocationY() {
        Maze m = phase.maze();
        if(m.player.l == null) return -1;
        return m.player.l.getY();
    }

    /**
     * checks if it is wall.
     * 
     * @param wallX
     * @param wallY
     * @return boolean
     * 
     */
    public boolean isWall(int wallX, int wallY) {
        Maze m = phase.maze();
        if(m.getGrid()[wallX][wallY] instanceof Wall) return true;
        return false;
    }



}
