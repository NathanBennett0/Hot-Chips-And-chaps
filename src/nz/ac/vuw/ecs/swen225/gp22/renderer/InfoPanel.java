package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class InfoPanel extends JPanel implements ActionListener{
    private String message;
    private Img background = Img.InfoPopUp;
    private JLabel text = new JLabel(); 
    private Timer timer; 
    
    public InfoPanel(String m){
        this.message = m; 
        this.text.setText(message); 
        this.text.setHorizontalAlignment(SwingConstants.CENTER);
        this.text.setFont(new Font("Arial Black", Font.BOLD, 10));
        this.text.setBounds(300,500,200,300);
        this.setBounds(300,500,200,300);
        //this.setOpaque(false);
        this.add(text);
       
        timer = new Timer(100, this);
        timer.start();
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background.image, 0, 0, null);  
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
    }

}
