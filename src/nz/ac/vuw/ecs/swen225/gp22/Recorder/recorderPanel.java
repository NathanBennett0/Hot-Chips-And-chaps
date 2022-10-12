package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.Graphics;



import  javax.swing.JFrame;

import nz.ac.vuw.ecs.swen225.gp22.app.App;

public class recorderPanel extends JPanel {
    
    private JPanel panel;
    private JButton loadRecord = new JButton("Load Record");
    private JButton moveBack = new JButton("Step-by-Step");
    private JButton autoReplay = new JButton("Auto Replay");
    private JButton setRepSpeed = new JButton("Set Replay Speed");
    private JButton loadButton = new JButton("Load");
    private JSlider steps; //scrubber
    App app;
    
    private List<Move> moves;
    private int currMove =0;

    private boolean playing = false;

    //button dimensions
    private static final Dimension BUTTON_SIZE = new Dimension(50, 20);
    private static final Dimension SLIDER_SIZE = new Dimension(600, 20);


    public recorderPanel(App a){
        app = a;
    }
    public void recordPanel(){

            moveBack.setSize(BUTTON_SIZE);
            steps.setValue(steps.getValue()-1);
            this.playing = false;
            panel.repaint();

            steps= moves == null ? new JSlider() : new JSlider(0, moves.size()-1);
            steps.setPreferredSize(SLIDER_SIZE);
            steps.setValue(0);
            steps.addChangeListener(e ->{
                JSlider slider = (JSlider) e.getSource();
                if(!slider.getValueIsAdjusting()){
                    movesStep(slider.getValue());
                }
            });
            steps.setBackground(Color.CYAN);
            steps.setUI(new BasicSliderUI(steps){

                
                 
                public void paint(Graphics g){
                    g.setColor(Color.BLACK);
                }
            });
           JButton mainMenu = new JButton("Recorder Menu"); //return back to main menu from App
           loadButton.setSize(new Dimension(70,20));
           load();
           if(moves != null){steps.setMaximum(moves.size());}

           JButton moveStraight = new JButton("Move Straight");
           moveStraight.setSize(70,20);
           steps.setValue(steps.getValue()+1);
           playing = false;
           panel.repaint();

           JButton play = new JButton("Play Record");
           play.setSize(70,20);
           setPlayButton();

           JButton setSpeed = new JButton("Set Speed");
           setSpeed.setSize(BUTTON_SIZE.width*2, BUTTON_SIZE.height);
           setSpeedButton();
            
           this.setLayout(new BorderLayout());
           this.add(panel);
           this.add(moveBack);
           this.add(steps);
           this.add(moveStraight);
           add(mainMenu);

           setPreferredSize(new Dimension(700, 490));
           setBackground(Color.CYAN);
    }

    public void load(){ }
    public void setPlayButton(){ }
    public void setSpeedButton(){ }

    /**
     * 
     * moveAction
     * 
     * @param pos place that is being moved to
     */
    private void movesStep (int pos){
        if(moves == null){return;}
        if(pos > currMove ){
            //moves forward
            for(int i = currMove; i<pos; i++){
                moves.get(i).move();
                panel.repaint();
            }
        } else if (pos<currMove){
            //moves back
            for(int i= currMove; i>pos; i--){
            Move prevMove = moves.get(i-1);
            if(prevMove instanceof Move m){
                m.move();
            }
            moves.get(i).undo();
            panel.repaint();
            }
        }
        currMove = pos < moves.size() ? pos : moves.size() -1;

    }
}
