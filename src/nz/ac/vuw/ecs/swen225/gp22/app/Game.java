package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game extends JPanel {
	private static int level;
	
	JLabel timeLeft = new JLabel("01:30");
	JLabel currLvl = new JLabel("1");
	JLabel itemLeft = new JLabel("1");
	
	Tile[][] test = new Tile[9][9]; // This 2D array is for testing purposes only
	
	
	/**
	 * JPanel for when the Game runs. Contains viewport and scoreboard.
	 */
    public Game(int lvl) {
    	this.level = lvl;
    	
    	// This is just to fill the array for testing
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			test[x][y] = new Free(new Location(x,y));
    		}
    	}
    	
        sidePanel();
        gameBoard();
        
    	//Setting Background Image
        var bgImage = new JLabel();
        bgImage.setBounds(0, 0, 900, 630);
        
        //Scaling Image
        Image scaledImage = Img.GameBackground.image.getScaledInstance(App.WIDTH,App.HEIGHT-50,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setLayout(null);
        bgImage.setIcon(icon);
        this.add(bgImage);
        this.setPreferredSize(new Dimension(App.WIDTH,App.HEIGHT));
        
        System.out.println("Game.java: Game constructor called.");
        
    }
    
    //Import viewport
    public void gameView() {
        setLayout(null);
        JPanel p = new JPanel();
        p.setBackground(new Color(216, 191, 216));
        p.setBounds(0,0,App.HEIGHT, App.HEIGHT);
        
        this.add(p);
        System.out.println("Game.java: gameView() called.");
        
    }
    
    public void sidePanel() {
		
        JPanel p = new JPanel();
        
        p.setBounds(App.HEIGHT, 0, App.WIDTH-App.HEIGHT, App.HEIGHT);
        p.setOpaque(false); //Transparent Panel
        p.setLayout(null);
        
        this.add(p);
        
        currLvl.setHorizontalAlignment(SwingConstants.CENTER);
        currLvl.setFont(new Font("Arial Black", Font.BOLD, 30));
        currLvl.setBounds(16, 71, 155, 54);
        p.add(currLvl);
        
        timeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        timeLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        timeLeft.setBounds(16, 193, 155, 54);
        p.add(timeLeft);
        
        itemLeft.setHorizontalAlignment(SwingConstants.CENTER);
        itemLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        itemLeft.setBounds(16, 321, 155, 54);
        p.add(itemLeft);
        System.out.println("Game.java: sidePanel() called.");
        
    }
    
    public void gameBoard() {
    	JPanel board = new JPanel(); 
    	board.setBounds(50,35,558,558);
    	board.setLayout(new GridLayout(9,9));
    	
    	this.add(board);
    	
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			 var tileLabel = new JLabel();
    			 ImageIcon icon = new ImageIcon(test[x][y].icon.image.getScaledInstance(62,62,Image.SCALE_SMOOTH));
    			 tileLabel.setIcon(icon);
    			 board.add(tileLabel);
    		}
    	}
    }
    
	/**
     * Getters for level
     * @return
     */
    public int getLevel() {
    	System.out.println("Game.java: getLevel() called.");
    	return this.level;
    }
//    
//    public Timer getTimer() {
//    	return this.timer;
//    }
    
}
