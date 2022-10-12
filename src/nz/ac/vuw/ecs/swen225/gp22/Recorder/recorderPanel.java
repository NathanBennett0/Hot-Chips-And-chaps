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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

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
    // private JSlider steps; //scrubber
    App app;

    private List<Move> moves;
    private int currMove = 0;

    private boolean playing = false;
    private int speed = 1;

    // button dimensions
    private static final Dimension BUTTON_SIZE = new Dimension(50, 20);
    private static final Dimension SLIDER_SIZE = new Dimension(600, 20);

    public recorderPanel(App a) {
        System.out.println("Testing for entering constructor");
        app = a;
        recordPanel();
    }

    public void recordPanel() {
        System.out.println("Entered panel");
        panel = new JPanel();

        // loadButton.setSize(new Dimension(70, 20));
        // load();
        // if(moves != null){steps.setMaximum(moves.size());}

        // JButton moveForward = new JButton("Move Forward");
        // steps.setValue(steps.getValue()+1);
        // playing = false;
        // panel.repaint();
        
        JButton mainMenu = new JButton("Recorder Menu"); // return back to main menu from App
        JButton play = new JButton("Play Record");

        setSpeedButton();
        play.setSize(70, 20);
        // moveForward.setSize(70, 20);
        loadRecord.setSize(70, 20);
      //  moveBack.setSize(70, 20);
       // autoReplay.setSize(70, 20);
        setRepSpeed.setSize(70, 20);

        this.add(panel);
       // this.add(moveBack);
        // this.add(steps);
        this.add(mainMenu);
        this.add(play);
        // this.add(pause); // so a player can pause a reccording. Not compulsory
        // this.add(moveForward);
        this.add(loadRecord);
        this.add(setRepSpeed);

        setPreferredSize(new Dimension(700, 490));
        Color c = new Color(51, 153, 255);
        setBackground(c);

        // anonymous classes to handle button click

        loadRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                // if steps..
            }
        });

        /**
         * moveForward.addActionListener(new ActionListener() {
         * 
         * @Override
         *           public void actionPerformed(ActionEvent e) {
         *           playing = false;
         *           panel.repaint();
         *           // moveStep()
         *           }
         *           });
         */

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playRecorder();
            }
        });

        setRepSpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeedButton();
                // if steps..
            }
        });
    }

    public void playRecorder() {
        playing = true;
        new Thread(() -> {
            for (int i = currMove; i < moves.size(); i++) {
                if (!playing) {
                    break;
                }
                moves.get(i).move();
                if (status(playing, i)) {
                    break;
                }
            }
            playing = false;
            pause.setText("Play");
        }).start();
    }

    /**
     * status
     */
    public boolean status(boolean statusMoving, int currMoveInt) {
        currMove = currMoveInt < moves.size() ? currMoveInt : moves.size() - 1;
        // steps.setValue(currMoveInt);
        try {
            Thread.sleep(1000 / speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return !statusMoving;
    }

    public void load() {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user") + "/resources/Recorder");
        fileChooser.setDialogTitle("Select recording to load");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("load file (xml)", "xml");
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(fileChooser); // COME BACK TO THIS

        if (fileChooser.getSelectedFile() != null) {
            RecordLoad record = new RecordLoad(fileChooser.getSelectedFile());
            this.moves = record.getMoves();

            // loads levels from app
            if (record.level() == 1) {
                app.levelOne();
            } else if (record.level() == 2) {
                app.levelTwo();
            }

            /**
             * 
             * if(steps != null){
             * steps.setMaximum(moves.size()-1);
             * currMove = 0;
             * steps.setValue(0);
             * panel.repaint();
             * }
             */
        }
    }

    public void setSpeedButton() {
        speed = speed == maxSpeed ? 1 : speed + 1;
        System.out.println(speed);
        setRepSpeed.setText("Set Replay Speed");
    }

    /**
     * 
     * moveAction
     * 
     * @param pos place that is being moved to
     */
    private void movesStep(int pos) {
        if (moves == null) {
            return;
        }
        if (pos > currMove) {
            // moves forward
            for (int i = currMove; i < pos; i++) {
                moves.get(i).move();
                panel.repaint();
            }
        } else if (pos < currMove) {
            // moves back
            for (int i = currMove; i > pos; i--) {
                Move prevMove = moves.get(i - 1);
                if (prevMove instanceof Move m) {
                    m.move();
                }
                moves.get(i).undo();
                panel.repaint();
            }
        }
        currMove = pos < moves.size() ? pos : moves.size() - 1;

    }
}
