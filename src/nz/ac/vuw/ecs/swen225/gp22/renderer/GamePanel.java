package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.app.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Free;
import nz.ac.vuw.ecs.swen225.gp22.domain.Location;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class GamePanel extends JPanel implements ActionListener{
	final int IMAGE_DIM = 62;
	final int BOARD_DIM = 558;
	
	Tile[][] board;
	Tile[][] test = new Tile[9][9]; // This 2D array is for testing purposes only
	Timer timer; 
	

	public GamePanel() {		
		// This is just to fill the array for testing
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			test[x][y] = new Free(new Location(x,y));
    		}
    	}
    	
    	this.setBounds(62,35,BOARD_DIM,BOARD_DIM);
    	this.setLayout(new GridLayout(9,9));
    	timer = new Timer(100, this); // Timer works in milliseconds
		timer.start();
	}
	
	/**
	 * Paint the board onto the Graphics Panel
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			Image i = test[x][y].icon.image.getScaledInstance(IMAGE_DIM,IMAGE_DIM,Image.SCALE_SMOOTH);
    			g2d.drawImage(i, x * IMAGE_DIM, y * IMAGE_DIM, null);
    		}
    	}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// get the board
		repaint(); 
		
	}
	
	/**
	 * When a character has been moved, update the board.
	 *  
	 * @param b The new board. 
	 */
	 public void updateBoard(Tile[][] b) {
		 this.board = b; 
	 }

	 
	 
	 /**
	  * Place this Game Panel onto the game 
	  * 
	  * @param g The Game. 
	  */
	 public void getBoard(Game g) {
	    	g.add(this);
	    	
	    	for(int x = 0; x < 9; x++) {
	    		for(int y = 0; y < 9; y++) {
	    			 var tileLabel = new JLabel();
	    			 ImageIcon icon = new ImageIcon(test[x][y].icon.image.getScaledInstance(IMAGE_DIM,IMAGE_DIM,Image.SCALE_SMOOTH));
	    			 tileLabel.setIcon(icon);
	    			 this.add(tileLabel);
	    		}
	    	}
	    }
	 
	 
}
