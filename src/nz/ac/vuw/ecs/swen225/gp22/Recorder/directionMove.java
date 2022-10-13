
package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;

public class directionMove implements Move {
    /**
     * keyCode for the move.
     * keyCode is required in order to use the keyPressed method inside
     * KeyStroke.java
     */
    public int keyCode = 0;

    /**
     * Gets the app from app class
     */
    App app = App.getInstance();

    /**
     * Constructor for the Move
     * 
     * @param code keyCode for the directionMove
     */
    public directionMove(int code) {
        keyCode = code;
    }

    /**
     * Executes the move
     */
    @Override
    public void move() {
        Controller c = app.getPhase().controller();
        c.keyPressed(keyCode);
    }

    /**
     * Return the key code for this directionMove
     * 
     * @return getKeyCode for the move
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Returns app for this directionMove
     * 
     * @return app
     */
    public App getApp() {
        return this.app;
    }

}
