package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

public class ScoreBoardPanel extends JPanel implements ActionListener{
    final int IMAGE_DIM = 62;

    public Chap chap;
    private Timer timer; 


    public ScoreBoardPanel(Maze m) {
        this.setBounds(710,407,133,190);
        this.setLayout(new GridLayout(2,3));
        this.setBackground(new Color(186,212,186));
        chap = m.player; 
        timer = new Timer(200, this); // Update the score board every 200 milliseconds
        timer.start();
    }

    /**
     * Paint the score board onto the JPanel
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        ArrayList<Tile> chest = chap.getChest();

        int x = 0; 
        int y = -1; 
        for(int i = 0; i < chest.size(); i++) {
            if(i % 2 == 0) {y++;}
            Image img = Img.TreasureOne.image.getScaledInstance(IMAGE_DIM,IMAGE_DIM,Image.SCALE_SMOOTH);
            g2d.drawImage(img, IMAGE_DIM * x, IMAGE_DIM * y, null);
            x = (x == 0 ? 1 : 0);
        }
    }

    /**
     * Repaint every 200 milliseconds 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


}