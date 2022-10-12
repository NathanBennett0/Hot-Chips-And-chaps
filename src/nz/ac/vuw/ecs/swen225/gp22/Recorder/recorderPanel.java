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
    private JButton mainMenu = new JButton("Home");
    private JButton play = new JButton("Play Record");
    private JButton loadRecord = new JButton("Load Record");
    private JButton setRepSpeed = new JButton("Set Replay Speed");
    private JButton pause = new JButton("Pause");
    // private JButton moveBack = new JButton("Step-by-Step");
    // private JButton autoReplay = new JButton("Auto Replay");
    // private JButton loadButton = new JButton("Load");
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
        setSpeedButton();

        // loadButton.setSize(new Dimension(70, 20));
        // load();
        // if(moves != null){steps.setMaximum(moves.size());}

        // JButton moveForward = new JButton("Move Forward");
        // steps.setValue(steps.getValue()+1);
        // playing = false;
        // panel.repaint();
        // panel.repaint();

    
        // set button sizes
        play.setSize(70, 20);
        loadRecord.setSize(70, 20);
        setRepSpeed.setSize(70, 20);

        // moveForward.setSize(70, 20);
        // moveBack.setSize(70, 20);
        // autoReplay.setSize(70, 20);

        // add panels to JPanel
        this.add(panel);
        this.add(mainMenu);
        this.add(play);
        this.add(loadRecord);
        this.add(setRepSpeed);
        // this.add(moveBack);
        // this.add(steps);
        // this.add(pause); // so a player can pause a reccording. Not compulsory
        // this.add(moveForward);

        // style JPanel
        setPreferredSize(new Dimension(700, 490));
        setBackground(new Color(51, 153, 255) );


        // set Action Listeners
        loadRecord.addActionListener((e) -> {
            load();
        });
        play.addActionListener((e) -> {
            playRecorder();
        });
        setRepSpeed.addActionListener((e) -> {
            setSpeedButton();
        });
        mainMenu.addActionListener((e) -> {
            app.home();
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

    }

    public void playRecorder() {
        System.out.println("in play record");
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

    /**
     * Load a game and execute its moves
     */
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
                app.phaseOne();
            } else if (record.level() == 2) {
                app.phaseTwo();
            }

            //execute all of the moves
            for(Move m: moves){
                m.move();
            }
        }
    }

    public void setSpeedButton() {
        System.out.println("in set speed record");
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
