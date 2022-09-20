package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;

import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;

public class ScoreBoardPanel extends JPanel {
	private Img background = Img.GameBackground;
	private static int levelNum = 1;
	private static String timeVal = "0:00";
	private static int treasuresLeft = 8;
	private Entity[] inventory;

	public ScoreBoardPanel() {
	}

	// NOTE TO SELF, MAKE ONE OF THESE FOR START PANEL AND GAME PANEL ASWELL!!
	public static JLayeredPane getScoreBoard() {
		// var p = new JPanel();
		// p.setLayout(null); // Layout must be null in order to set coord for labels

		var backgroundImage = new JLabel(new ImageIcon(Img.GameBackground.image));
		var level = new JLabel(String.valueOf(levelNum));
		var time = new JLabel(timeVal);
		var treasures = new JLabel(String.valueOf(treasuresLeft));

		var x1 = new JLabel("i");
		var x2 = new JLabel("i");

		x1.setBounds(40, 0, 100, 100);
		x2.setBounds(40, 600, 100, 100);

		// NOTE
		// 40 - 640 for the x coord
		// y = 0 - 600

		backgroundImage.setBounds(0, 0, 900, 700);
		level.setBounds(766, 71, 100, 100);
		time.setBounds(740, 203, 100, 100);
		treasures.setBounds(766, 337, 100, 100); // try the set location instead...

		level.setFont(new Font("Monospaced", Font.PLAIN, 30));
		time.setFont(new Font("Monospaced", Font.PLAIN, 30));
		treasures.setFont(new Font("Monospaced", Font.PLAIN, 30));

		level.setForeground(new Color(74, 100, 70));
		time.setForeground(new Color(74, 100, 70));
		treasures.setForeground(new Color(74, 100, 70));

		var p = new JLayeredPane();
		p.setBounds(0, 0, 900, 700);

		p.add(x1);
		p.add(x2);

		p.add(level);
		p.add(time);
		p.add(treasures);
		p.add(backgroundImage);

		/**
		 * level.setLocation(500, 100); time.setLocation(500, 300);
		 * treasures.setLocation(500, 500);
		 */

		return p;
	}

}
