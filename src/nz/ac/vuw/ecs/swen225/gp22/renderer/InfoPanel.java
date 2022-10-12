package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class InfoPanel extends JPanel{
    private String message;
    private Img background = Img.InfoPopUp;
    
    public InfoPanel(String m){
        this.message = m; 
        this.setBounds(708,407,133,190);
        this.setOpaque(false);
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background.image, 300, 500, null);
    }

}
