package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class StartPanel extends JLabel { ///// HMMMM MIGHT NOT NEED THIS CLASS
    private static Img imgOne = Img.StartOne;
	private static Img imgTwo = Img.StartTwo;
	
	public StartPanel() {}
	
	protected void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; 
		System.out.println(imgOne.image.getWidth());
		System.out.println(imgOne.image.getHeight());
		super.paintComponent(g);
		g.drawImage(imgOne.image, 0, 0, null);
	}

	public static Img getImageOne(){
		return imgOne;
	}
}
