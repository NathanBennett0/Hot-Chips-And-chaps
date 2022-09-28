package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.app.App;

public class StartPanel extends JLabel {

	public StartPanel() {}

	public void addStartPanel(JPanel p) {
		 var backgroundImage = new JLabel();
	     backgroundImage.setBounds(0, 0, App.WIDTH, App.HEIGHT);
	     ImageIcon icon = new ImageIcon(Img.StartOne.image);
	     backgroundImage.setIcon(icon);
	     p.add(backgroundImage);
	}
}
