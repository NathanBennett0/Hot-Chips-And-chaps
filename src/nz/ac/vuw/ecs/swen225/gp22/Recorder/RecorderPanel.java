
package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RecorderPanel extends JPanel {
    App app;
    RecordLoad recordLoad;
    Runnable setInactive;
    boolean recordTimer;
    public JButton back = new JButton("Back");
    public JButton stepByStep = new JButton("Step-By-Step");
    public JSlider setSpeed = new JSlider(1, 5);
    public JButton autoReplay = new JButton("Auto-Replay");
    public JLabel displaySpeed = new JLabel(setSpeed.getValue() + "x");

    public RecorderPanel(App app, RecordLoad recordLoad) {
        this.app = app;
        this.recordLoad = recordLoad;
        this.setLayout(null);
        // this.setOpaque(false);

        // BUTTONS
        back.setBounds(120, 608, 100, 30);
        back.addActionListener((e) -> app.home());
        stepByStep.setBounds(424, 608, 200, 30);
        autoReplay.setBounds(222, 608, 200, 30);

        setSpeed.setMinorTickSpacing(1);
        setSpeed.setBounds(626, 608, 150, 30);

        displaySpeed.setBounds(780, 608, 30, 30);
        displaySpeed.setForeground(new Color(255, 255, 255));

        // ACTION LISTENERS
        autoReplay.addActionListener((e) -> playRecording(setSpeed.getValue()));
        stepByStep.addActionListener((e) -> {
            if (recordLoad.getMoves().isEmpty()) {
                JOptionPane.showConfirmDialog(this, "Go back to Main to load another recording.", "Notice",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                App.getInstance().home();
            }
            Objects.requireNonNull(recordLoad.getMoves().poll()).move();
        });
        setSpeed.addChangeListener((e) -> displaySpeed.setText(setSpeed.getValue() + "x"));

        setInactive = () -> {
            setSpeed.setEnabled(false);
            stepByStep.setEnabled(false);
            autoReplay.setEnabled(false);
        };
    }

    /**
     * Plays recordings - triggers moves.
     */
    public void playRecording(int value) {
        if (recordLoad.getMoves().isEmpty())
            return;
        int delay = 550 - 100 * value;
        recordTimer = true;
        setInactive.run(); // making the buttons inactive while recording is being played
        Timer t = new Timer(delay, e -> {
            AtomicInteger val = new AtomicInteger(0);
            if (delay == 0)
                return;

            // Decides how often the following code runs
            if (val.getAndIncrement() % delay == 0) {
                Objects.requireNonNull(recordLoad.getMoves().poll(), "No more moves left.").move();
            }
            if (recordLoad.getMoves().size() == 0) {
                recordTimer = false;
                JOptionPane.showConfirmDialog(this, "Go back to Main to load another recording.", "Notice",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                app.home();
            }
            if (!recordTimer)
                ((Timer) e.getSource()).stop();
        });
        t.start();
    }
}
