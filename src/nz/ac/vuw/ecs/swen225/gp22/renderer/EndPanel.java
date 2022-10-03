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

public class EndPanel extends JLabel implements ActionListener{
	
	private int counter = 0; 
	private Img backgroundImg = Img.EndBackground;
	Timer timer; 

	public EndPanel() {
		this.setBounds(0,0, App.WIDTH, App.HEIGHT);
		timer = new Timer(600, this);
		timer.start();
	}
	
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backgroundImg.image, 0, 0, null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	
	
}
