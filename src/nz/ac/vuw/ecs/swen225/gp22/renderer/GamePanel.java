package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

public class GamePanel extends JPanel{
    Entity[][] board; 
	int dim = 80; 
	
	public GamePanel(Entity[][] b) {
		this.setPreferredSize(new Dimension(800, 800));
		this.board = b;  // could use Maze.getGrid() for this... 
	}
	
	/**
	 * Draw the image on the window
	 */
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; 

		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				g.drawImage(board[x][y].icon.image, x * dim, y * dim, null);
			}
		}
		
		
	}

	public static JLabel getStartPanel(){
        var backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon(Img.StartOne.image));
        // REMINDER: 
        // need to add in a if statement here to iterate between StartOne and StartTwo
        // backgroundImage.setIcon(new ImageIcon(Img.StartTwo.image));
        return backgroundImage;
    }

    
}
