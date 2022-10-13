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
    private JButton loadRecord = new JButton("Auto Play");
    private JButton setRepSpeed = new JButton("Set Replay Speed");
    private JButton pause = new JButton("Pause");
    //private JButton play = new JButton("Play Record");
    
    App app;

    private List<directionMove> moves;
    private int speed = 1;

    /**
     * Constructor for the panel
     * @param a app
     */
    public recorderPanel(App a) {
        System.out.println("Testing for entering constructor");
        app = a;
        recordPanel();
    }

    /**
     * Creating the JPanel for the recorder
     */
    public void recordPanel() {
        System.out.println("Entered panel");
        panel = new JPanel();
        setSpeedButton();

        // set button sizes
        loadRecord.setSize(70, 20);
        setRepSpeed.setSize(70, 20);
        //play.setSize(70, 20);

        // add panels to JPanel
        this.add(panel);
        this.add(mainMenu);
        this.add(loadRecord);
        this.add(setRepSpeed);

        // style JPanel
        setPreferredSize(new Dimension(700, 490));
        setBackground(new Color(51, 153, 255));

        // set Action Listeners
        loadRecord.addActionListener((e) -> {
            load();
        });
        
        setRepSpeed.addActionListener((e) -> {
            setSpeedButton();
        });
        mainMenu.addActionListener((e) -> {
            app.home();
        });

        //play.addActionListener((e) -> {playRecorder();});

    }

     /**
     * Loads a game from RecordLoad and execute its moves
     */
    public void load() {
        JFileChooser fileChooser = new JFileChooser("src/nz/ac/vuw/ecs/swen225/gp22/Recorder/");
        fileChooser.setDialogTitle("Select recording to load");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("load file (xml)", "xml");
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(fileChooser);

        if (fileChooser.getSelectedFile() != null) {

            RecordLoad record = new RecordLoad(fileChooser.getSelectedFile()); // creates new recordLoad object
            this.moves = record.getMoves();
            System.out.println("moves have been printed");

            // load new panel
            app.recorderGame();
            for (directionMove m : moves) {
                System.out.println("print moves");
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
     * For the play record.
     *
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
    */

}
