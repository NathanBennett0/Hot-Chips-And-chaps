package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.app.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Free;
import nz.ac.vuw.ecs.swen225.gp22.domain.Location;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class GamePanel extends JPanel {
	Tile[][] board;
	Tile[][] test = new Tile[9][9]; // This 2D array is for testing purposes only
	int dim = 62;

	public GamePanel() {		
		// This is just to fill the array for testing
    	for(int x = 0; x < 9; x++) {
    		for(int y = 0; y < 9; y++) {
    			test[x][y] = new Free(new Location(x,y));
    		}
    	}
	}
	
	 public void updateBoard(Tile[][] b) {
		 this.board = b; 
	 }

	 public void getBoard(Game g) {
	    	JPanel board = new JPanel(); 
	    	board.setBounds(50,35,558,558);
	    	board.setLayout(new GridLayout(9,9));
	    	
	    	g.add(board);
	    	
	    	for(int x = 0; x < 9; x++) {
	    		for(int y = 0; y < 9; y++) {
	    			 var tileLabel = new JLabel();
	    			 ImageIcon icon = new ImageIcon(test[x][y].icon.image.getScaledInstance(dim,dim,Image.SCALE_SMOOTH));
	    			 tileLabel.setIcon(icon);
	    			 board.add(tileLabel);
	    		}
	    	}
	    }
	 
}
