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
	Maze maze;
	App app; 
	Location prevLoc; 
	CatImage currChap = new NormalImage();
	

	public GamePanel(Maze m, App a) {	
		this.maze = m;
		this.app = a; 
		this.prevLoc = maze.player.getLocation();
		this.currChap = new NormalImage(); 
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
				board[x][y] = m.grid[x + 6][y + 5];
    		}
    	}
    	
    	this.setBounds(62,35,BOARD_DIM,BOARD_DIM);
    	this.setLayout(new GridLayout(9,9));
    	timer = new Timer(150, this); // Timer works in milliseconds
		timer.start();
	}
	
	/**
	 * Paint the board onto the Graphics Panel
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			if(board[x][y] instanceof Chap) { // For the animation of the chapx
    				if(currChap.done()) {
    					setImgState(board[x][y].getLocation());
    				}
    				Image i = currChap.getCurrImg().image;
    				prevLoc = maze.player.getLocation();
    				g2d.drawImage(i, x * IMAGE_DIM, y * IMAGE_DIM, null);
    			}else {
    				Image i = board[x][y].getImg().image;
        			g2d.drawImage(i, x * IMAGE_DIM, y * IMAGE_DIM, null);
    			}
    		}
    	}
	}
	

	/**
	 * Every 100 milli seconds, update the gui (repaint)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(app.getGame() == null) {return;}
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
	  * This method will make sure the camera is focused around the chap
	  * i.e. the chap is always in the center of the board
	  * @param x
	  * @param y
	  * @param temp
	  */
	 private void updateCamera(int x, int y, Tile[][] temp){
		 int shiftX = x - 4; 
		 int shiftY = y - 4;
		 
		 for(int i = 0; i < 9; i++) {
	    	for(int j = 0; j < 9; j++) {
	    		board[i][j] = temp[i + shiftX][j+ shiftY];
    		}
    	}
	 }
	 
	 /**
	  * This method is used for the animation of the chap.
	  * It compares its current and previous location and 
	  * assigns the chap state accordingly. 
	  * 
	  * @param l = current location of the chap
	  */
	 public void setImgState(Location l) {
		 if(l.getX() < prevLoc.getX()) {
			 currChap = new LeftImage();
		 }else if(l.getX() > prevLoc.getX()) {
			 currChap = new RightImage();
		 }else if(l.getY() < prevLoc.getY()) {
			 currChap = new UpImage();
		 }else if(l.getY() > prevLoc.getY()){
			 currChap = new DownImage();
		 }else {
			 currChap = new NormalImage();
		 }
	 }

	  
	 
	 
}