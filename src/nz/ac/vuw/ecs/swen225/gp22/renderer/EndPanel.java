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
    private int x = 900;
    private int y = 450;
    private int textY = 0;
    private int textCounter = 0;

    private Img backgroundImg = Img.EndBackground;
    private Img text = Img.GameOver;
    private Img currCat = Img.EndCat1;
    private Img[] catImages = {Img.EndCat1, Img.EndCat2, Img.EndCat3, Img.EndCat4, Img.EndCat5}; 
    Timer timer; 

    public EndPanel() {
        this.setBounds(0,0, App.WIDTH, App.HEIGHT);
        timer = new Timer(200, this);
        timer.start();
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImg.image, 0, 0, null);
        g2d.drawImage(text.image, 0, textY, null);
        g2d.drawImage(currCat.image, x, y, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        textCounter++;
        if(textCounter % 6 == 0) {
            textY = (textY == 0 ? 15 : 0);
        }

        if(x > 300) {
            if(currCat.equals(Img.EndCat1)) {
                currCat = catImages[1];
            }else if(currCat.equals(Img.EndCat2)) {
                currCat = catImages[2];
            }else if(currCat.equals(Img.EndCat3)) {
                currCat = catImages[3];
            }else {
                currCat = catImages[0];
            }

            x = x - 8; 
        }else {
            currCat = catImages[4];
        }

        repaint();
    }



}