package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

import java.awt.Color;

public class Game extends JPanel {
    public Game() {
        
        
        var bgImage = new JLabel();
        bgImage.setBounds(0, 0, 900, 650);
        //Scaling Image
        Image scaledImage = Img.GameBackground.image.getScaledInstance(App.WIDTH,App.HEIGHT-30,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setLayout(null);
        bgImage.setIcon(icon);
        this.add(bgImage);
        this.setPreferredSize(new Dimension(App.WIDTH,App.HEIGHT));
    }
    
    public void gameView() {
        setLayout(null);
        JPanel p = new JPanel();
        p.setBackground(new Color(216, 191, 216));
        p.setBounds(0,0,App.HEIGHT, App.HEIGHT);
        
        this.add(p);
        
    }
    
    public void sidePanel() {
        JPanel p = new JPanel();
        p.setBackground(new Color(173, 216, 230));
        p.setBounds(App.HEIGHT, 0, App.WIDTH-App.HEIGHT, App.HEIGHT);
        
        this.add(p);
    }
}
