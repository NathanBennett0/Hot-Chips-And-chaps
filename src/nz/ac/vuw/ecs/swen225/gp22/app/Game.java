package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * JPanel for when the Game runs. Contains viewport and scoreboard.
 * 
 * @author Naomi Parte
 * 
 */
public class Game extends JPanel {
	private Phase phase;
	private GamePanel game;
	
	JLabel tLeft = new JLabel("00:00");
	JLabel currLvl = new JLabel();
	JLabel itemLeft = new JLabel();
	
	/**
     * Game constructor.
     * 
     * @param p
     * @param a
     */
    public Game(Phase p, App a) {
    	this.phase = p;
    	
        sidePanel();
        
        game = new GamePanel(p.maze(), a);
        this.add(game);
        
    	//Setting Background Image
        var bgImage = new JLabel();
        bgImage.setBounds(0, 20, App.WIDTH, App.HEIGHT-45);
        
        //Scaling Image
        Image scaledImage = Img.GameBackground.image.getScaledInstance(App.WIDTH,App.HEIGHT-45,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setLayout(null);
        bgImage.setIcon(icon);
        this.add(bgImage);
        this.setPreferredSize(new Dimension(App.WIDTH,App.HEIGHT));
    }
    
    /**
     * Side Panel GUI display.
     * 
     */
    public void sidePanel() {
		
        JPanel p = new JPanel();
        
        p.setBounds(App.HEIGHT, 20, App.WIDTH-App.HEIGHT, App.HEIGHT);
        p.setOpaque(false); //Transparent Panel
        p.setLayout(null);
        
        this.add(p);
        
        currLvl.setText(Integer.toString(phase.maze().getLevel().getLevel())); 
        currLvl.setHorizontalAlignment(SwingConstants.CENTER);
        currLvl.setFont(new Font("Arial Black", Font.BOLD, 30));
        currLvl.setBounds(16, 71, 155, 54);
        p.add(currLvl);
        
        tLeft.setHorizontalAlignment(SwingConstants.CENTER);
        tLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        tLeft.setBounds(16, 193, 155, 54);
        p.add(tLeft);
        
        itemLeft.setHorizontalAlignment(SwingConstants.CENTER);
        itemLeft.setFont(new Font("Arial Black", Font.BOLD, 30));
        itemLeft.setBounds(16, 321, 155, 54);
        p.add(itemLeft);

        ScoreBoardPanel sp = new ScoreBoardPanel(phase.maze());
        sp.setBounds(20,410,155,200);
        p.add(sp);
    }
    
    /**
     * Getter for game phase.
     * 
     * @return phase
     */
    public Phase phase() {
    	return phase;
    }
    
}
