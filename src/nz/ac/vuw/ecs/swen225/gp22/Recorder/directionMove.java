
package nz.ac.vuw.ecs.swen225.gp22.Recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Controller;

public class directionMove implements Move {
    public int keyCode = 0;
    App app = App.getInstance();

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
     * @return app
     */
    public App getApp() {
        return this.app;
    }

}
