package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
    private BufferedImage imgOne;
	private BufferedImage imgTwo;
	
	
	public StartPanel() {
		try {
			imgOne = ImageIO.read(new File("StartOne.png"));
		}catch (IOException e){}
		
		try {
			imgTwo = ImageIO.read(new File("StartTwo.png"));
		}catch(IOException e) {}
	}
	
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; 
		g.drawImage(imgOne, 0, 0, null);
	}
}
