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
    
    //Boolean variables for fuzz testing
    public static boolean fuzzStarted = false;
    public static boolean initializeDone = false;

    Runnable restart = ()->{ stopTimer = true;};

    public App(){
    	System.out.println("App.java: App constructor called.");
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit on close
        initialize();
        this.addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent e){restart.run();}
        });
    }

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
    
    public void mainMenu(){
    	System.out.println("App.java: mainMenu() called.");
        newPanel.run();
        restart.run();
        var p = new JPanel();
        StartPanel sp = new StartPanel();
        
        var tutorial = new JButton("Tutorial");
        tutorial.setBounds(400, 550, 100, 30);
        var start = new JButton("Start!");
        start.setBounds(400, 580, 100, 30);

        tutorial.addActionListener((e)->{ tutorial();});
        start.addActionListener((e)->{ level(1);});

        getContentPane().add(BorderLayout.CENTER, p);
        p.setLayout(null);
        p.add(tutorial);
        p.add(start);
        sp.addStartPanel(p);
        
        newPanel = ()->{ remove(p);};
        //setPreferredSize(new Dimension(900, 750));
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

    public void level(int lvl){
    	System.out.println("App.java: level() called.");
        newPanel.run();
        stopTimer = false;
        Game p = new Game(lvl);
       
        ActionListener countDown=new ActionListener(){
        	int timeLeft = lvl==2?180000:90000; //level 2: 3 mins, level 1: 1.5 mins
        	
		    public void actionPerformed(ActionEvent e){
		        timeLeft -= 250;
		        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
		        //System.out.println(df.format(timeLeft));
		        p.timeLeft.setText(df.format(timeLeft));
		        if(timeLeft<=0 || stopTimer){
		        	((Timer)e.getSource()).stop();
		        }
		    }
		};
		
		Timer timer=new Timer(250, countDown);
        getContentPane().add(BorderLayout.CENTER, p);
                
        newPanel = ()->{ remove(p);};

        pack();
        timer.start();
        fuzzStarted = true; // For fuzz testing
    }
    
    public void menuBar() {
    	System.out.println("App.java: menuBar() called.");
    	//DECLARE
    	JMenuBar mb=new JMenuBar();
    	JMenuItem home=new JMenuItem("Home");
    	JMenuItem exit=new JMenuItem("Exit");  
    	JMenu start=new JMenu("Start");
    	JMenuItem lvl1=new JMenuItem("Level 1");
    	JMenuItem lvl2=new JMenuItem("Level 2");
    	JMenuItem pause=new JMenuItem("Pause");
    	
    	//FUNCTIONS
    	home.addActionListener((e)->mainMenu()); //connect to the main menu pane
    	exit.addActionListener((e)->System.exit(0));
    	pause.addActionListener((e)->System.out.println("Pause"));
    	
    	//ADD TO GUI
    	mb.add(home);
    	start.add(lvl1);
    	start.add(lvl2);
    	mb.add(start);
    	mb.add(pause);
    	mb.add(exit);
    	this.setJMenuBar(mb);
    }
}
