package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp22.app.App;

public class StartPanel extends JLabel implements ActionListener {
	private int counter = 0; 
	private Img currImg = Img.StartOne;
	Timer timer; 

	public StartPanel() {
		this.setBounds(0,0, App.WIDTH, App.HEIGHT);
		ImageIcon icon = new ImageIcon(currImg.image);
		this.setIcon(icon);
		timer = new Timer(600, this); // Image will change every 600 milliseconds
		timer.start();
	}
	
	/**
	 * Paint the starting image onto the JLabel
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(currImg.image, 0, 0, null);
	}
	
	/**
	 * Iterate between the two starting images for the animation
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currImg.equals(Img.StartOne)) {
			currImg = Img.StartTwo;
		}else {
			currImg = Img.StartOne;
		}
		repaint();
	}
}
