package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import  javax.swing.JFrame;

import nz.ac.vuw.ecs.swen225.gp22.app.App;

public class recorderPanel extends JPanel {
    
    private JPanel panel;
    private static final int maxSpeed = 4;
    private JButton loadRecord = new JButton("Load Record");
    private JButton moveBack = new JButton("Step-by-Step");
    private JButton autoReplay = new JButton("Auto Replay");
    private JButton setRepSpeed = new JButton("Set Replay Speed");
    private JButton loadButton = new JButton("Load");
    private JButton pause = new JButton("Pause");
  //  private JSlider steps; //scrubber
    App app;

    
    private List<Move> moves;
    private int currMove =0;

    private boolean playing = false;
    private int speed = 1;

    //button dimensions
    private static final Dimension BUTTON_SIZE = new Dimension(50, 20);
    private static final Dimension SLIDER_SIZE = new Dimension(600, 20);
    
    public recorderPanel(App a){
        System.out.println("Hi");
        app = a;
        recordPanel();
    }
    public void recordPanel(){
        System.out.println("Entered panel");
        panel = new JPanel();
        
       loadRecord.setSize(70,20);
        moveBack.setSize(70,20);
        autoReplay.setSize(70,20);

       this.add(loadRecord);
       this.add(moveBack);
       this.add(autoReplay);

       /** 
        loadRecord.addActionListener(new ActionListener()){


        }
        panel.setPreferredSize(new Dimension(700, 490));
        panel.setBackground(Color.CYAN);
        */

            moveBack.setSize(BUTTON_SIZE);
      /**     steps.setValue(steps.getValue()-1);
            this.playing = false;
            System.out.println("About to repaint panel line 76");
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
            */
            /** 
            steps.setUI(new BasicSliderUI(steps){

                
                 
                public void paint(Graphics g){
                    g.setColor(Color.BLACK);
                }
            });
            */

            /** 
           JButton mainMenu = new JButton("Recorder Menu"); //return back to main menu from App
           loadButton.setSize(new Dimension(70,20));
      //     load();
          // if(moves != null){steps.setMaximum(moves.size());}

           JButton moveStraight = new JButton("Move Straight");
           moveStraight.setSize(70,20);

      //     steps.setValue(steps.getValue()+1);
           playing = false;

         //  panel.repaint();
         

           JButton play = new JButton("Play Record");
           play.setSize(70,20);
           playRecorder();
           

           JButton setSpeed = new JButton("Set Speed");
           setSpeed.setSize(BUTTON_SIZE.width*2, BUTTON_SIZE.height);
           setSpeedButton();
            */
           panel.setLayout(new BorderLayout());
        /**   panel.add(panel);
           panel.add(moveBack);
           panel.add(loadRecord);
           
        // panel.add(steps);
           panel.add(pause);
         //  panel.add(moveStraight);
          // add(mainMenu);
          */

           setPreferredSize(new Dimension(700, 490));
           Color color = new Color(51,153,255);
           setBackground(color);
           
    }

    public void playRecorder(){ 
        playing = true;
        new Thread(() -> {
            for(int i = currMove; i< moves.size(); i++){
                if(!playing){break;} 
                moves.get(i).move();
                if(status(playing, i)){break;}
            }
            playing = false;
            pause.setText("Play");
            }).start();
    }

    /**
     * status
     */
    public boolean status(boolean statusMoving, int currMoveInt){
        currMove = currMoveInt < moves.size() ? currMoveInt : moves.size() -1;
    
    
        //    steps.setValue(currMoveInt);
    
    
        try{
            Thread.sleep(1000/speed);
        } catch(InterruptedException e){
            e.printStackTrace();
        } return !statusMoving;
    }
    public void load(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user") + "/resources/Recorder");
        fileChooser.setDialogTitle("Select recording to load");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("load file (xml)", "xml");
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(fileChooser); //COME BACK TO THIS

        if(fileChooser.getSelectedFile() != null){
            RecordLoad record = new RecordLoad(fileChooser.getSelectedFile());
            this.moves = record.getMoves();

            //loads levels from app
            if(record.level()==1){
                app.levelOne();
            } else if(record.level() == 2){
                app.levelTwo();
            }

            /** 

            if(steps != null){
                steps.setMaximum(moves.size()-1);
                currMove = 0;
                steps.setValue(0);
                panel.repaint();
            }
            */
        }
    }


    public void setSpeedButton(){
        speed = speed == maxSpeed ? 1 : speed + 1;
        System.out.println(speed);
        setRepSpeed.setText("speed");
     }

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
