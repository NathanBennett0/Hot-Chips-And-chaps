
package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;

/**
 * @author Christine Jayme
 *         Student ID: 300580764
 */

/**
 * directionMove class creates a move object for each move carried out in the
 * game. Also can execute the move.
 */
public class directionMove {

    /**
     * KeyCode for the move. The KeyCode is required in order to use the keyPressed
     * method inside KeyStroke.java.
     * 
     */
    public int keyCode = 0;

    /**
     * Gets the app from app class. This app will get the controller in order to
     * control the moves of the chap.
     */
    App app = App.getInstance();

    /**
     * Constructor for directionMove.
     * 
     * @param code keyCode for the directionMove.
     */
    public directionMove(int code) {
        keyCode = code;
    }

    /**
     * Executes the move
     */
    public void move() {
        Controller c = App.getInstance().getPhase().controller();
        c.keyPressed(keyCode);
        // For testing: System.out.println("Move being carried out. Key code: " +  keyCode);

    }

    /**
     * Return the key code for this directionMove.
     * 
     * @return getKeyCode for the move.
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Returns app for this directionMove.
     * 
     * @return app.
     */
    public App getApp() {
        return this.app;
    }

}
