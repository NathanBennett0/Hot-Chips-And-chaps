package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Game extends JPanel {
	private static int level;
	/**
	 * JPanel for when the Game runs. Contains viewport and scoreboard.
	 */
    public Game(int lvl) {
    	this.level = lvl;
    	

        sidePanel();
    	
    	//Setting Background Image
        var bgImage = new JLabel();
        bgImage.setBounds(0, 0, 900, 630);
        
        //Scaling Image
        Image scaledImage = Img.GameBackground.image.getScaledInstance(App.WIDTH,App.HEIGHT-50,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setLayout(null);
        bgImage.setIcon(icon);
        this.add(bgImage);
        this.setPreferredSize(new Dimension(App.WIDTH,App.HEIGHT));
        
    }
    
    //Import viewport
    public void gameView() {
        setLayout(null);
        JPanel p = new JPanel();
        p.setBackground(new Color(216, 191, 216));
        p.setBounds(0,0,App.HEIGHT, App.HEIGHT);
        
        this.add(p);
        
    }
    
    public void sidePanel() {
        JPanel p = new JPanel();
        
        p.setBounds(App.HEIGHT, 0, App.WIDTH-App.HEIGHT, App.HEIGHT);
        p.setOpaque(false); //Transparent Panel
        this.add(p);
        p.setLayout(null);
        
        JLabel level = new JLabel("1");
        level.setHorizontalAlignment(SwingConstants.CENTER);
        level.setFont(new Font("Arial Black", Font.BOLD, 30));
        level.setBounds(16, 71, 155, 54);
        p.add(level);
        
        JLabel timeLeft = new JLabel("01:30");
        timeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        timeLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        timeLeft.setBounds(16, 193, 155, 54);
        p.add(timeLeft);
        
        JLabel itemLeft = new JLabel("1");
        itemLeft.setHorizontalAlignment(SwingConstants.CENTER);
        itemLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        itemLeft.setBounds(16, 321, 155, 54);
        p.add(itemLeft);
    }
    
    /**
     * Getters for level
     * @return
     */
    public int getLevel() {
    	return level;
    }
}