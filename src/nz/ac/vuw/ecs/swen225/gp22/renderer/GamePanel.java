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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

public class GamePanel extends JPanel implements ActionListener{
	final int IMAGE_DIM = 62;
	final int BOARD_DIM = 558;
	
	Tile[][] board = new Tile[9][9];
	Timer timer; 
	Maze m;
	App app; 
	

	public GamePanel(Maze m, App a) {	
		this.m = m;
		this.app = a; 
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
				board[x][y] = m.grid[x][y];
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
    			Image i = board[x][y].getImg().image.getScaledInstance(IMAGE_DIM,IMAGE_DIM,Image.SCALE_SMOOTH);
    			g2d.drawImage(i, x * IMAGE_DIM, y * IMAGE_DIM, null);
    		}
    	}
	}

	/**
	 * Every 100 milli seconds, update the gui (repaint)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Tile[][] temp = app.getGame().phase().maze().getGrid();
		for(int x = 0; x < temp.length; x++) {
    		for(int y = 0; y < temp.length; y++) {
    			if(temp[x][y] instanceof Chap) {
    				updateCamera(x,y,temp);
    			}
			}
		}
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
	 
	 
	 public void updateCamera(int x, int y, Tile[][] temp){
		 int shiftX = x - 4; 
		 int shiftY = y - 4;
		 
		 for(int i = 0; i < 9; i++) {
	    	for(int j = 0; j < 9; j++) {
	    		board[i][j] = temp[i + shiftX][j+ shiftY];
    		}
    	}
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
	    			 ImageIcon icon = new ImageIcon(board[x][y].icon.image.getScaledInstance(IMAGE_DIM,IMAGE_DIM,Image.SCALE_SMOOTH));
	    			 tileLabel.setIcon(icon);
	    			 this.add(tileLabel);
	    		}
	    	}
	    }
	 
	 
}