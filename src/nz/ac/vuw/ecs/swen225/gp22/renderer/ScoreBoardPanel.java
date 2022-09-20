package nz.ac.vuw.ecs.swen225.gp22.renderer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

public class ScoreBoardPanel extends JPanel {
    private BufferedImage background;
	private int levelNum = 1;
	private String time = "0:00";
	private int treasuresLeft = 8;
	private Entity[] inventory; 
	
	public ScoreBoardPanel() {
		try {
			background = ImageIO.read(new File("GameBackground.png"));
		}catch (IOException e){}
		
	}
	
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; 
		g.drawImage(background, 0, 0, null);
		
		//g.setFont(new Font("Ink Free", Font.BOLD, 50));
		
		// Have to play around with the coordinates
		g.drawString(String.valueOf(levelNum), 50, 50); 
		g.drawString(time, 50, 100);
		g.drawString(String.valueOf(treasuresLeft), 50, 150);
		
		int y = 0; 
		for(int x = 0; x < inventory.length; x++) {
			//g.drawImage(inventory[x], x * 10, y, null);
		}
		
		
	}
    
}
