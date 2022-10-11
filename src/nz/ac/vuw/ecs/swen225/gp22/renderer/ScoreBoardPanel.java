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
    final int BIG_IMG_DIM = 62;
    final int SMALL_IMG_DIM = 45; 

    public Chap chap;
    private Timer timer; 


    public ScoreBoardPanel(Maze m) {
        this.setBounds(708,407,133,190);
        this.setLayout(new GridLayout(2,3));
        this.setOpaque(false);
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
        	if(chest.size() < 7) {
        		if(i % 2 == 0) {y++;}
                Image img = chest.get(i).getImg().image.getScaledInstance(BIG_IMG_DIM,BIG_IMG_DIM,Image.SCALE_SMOOTH);
                g2d.drawImage(img, BIG_IMG_DIM * x, BIG_IMG_DIM * y, null);
                x = (x == 0 ? 1 : 0);
        	}else {
        		if(i % 3 == 0) {
        			y++;
        			x = 0; 
        		}
        		 Image img = chest.get(i).getImg().image.getScaledInstance(SMALL_IMG_DIM,SMALL_IMG_DIM,Image.SCALE_SMOOTH);
                 g2d.drawImage(img, SMALL_IMG_DIM * x, SMALL_IMG_DIM * y, null);
                 x++;
        		
        	}
            
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