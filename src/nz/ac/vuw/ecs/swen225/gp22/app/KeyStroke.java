package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp22.Recorder.directionMove;

/**
 * Custom keyListener class for the game.
 *
 * @author Naomi Parte 300562058
 */
public class KeyStroke implements KeyListener {
    /**
     * Stores normal keybinding.
     */
    private static final Map<Integer, Runnable> onPressed = new HashMap<>();

    /**
     * Stores keybinding for control actions.
     */
    private static final Map<Integer, Runnable> onCtrlPressed = new HashMap<>();

    /**
     * Stores normal keybinding for chap - only used for automations in fuzz and recorder.
     */
    private static final Map<Integer, Runnable> autoControl = new HashMap<>();

    /**
     * Stores the app.
     */
    protected App app;

    /**
     * Constructor for the keystroke.
     *
     * @param app App
     */
    KeyStroke(App app) {
        this.app = app;
    }

    /**
     * A function for keybinding - Maps a keycode with given action.
     *
     * @param keyCode Key code number
     * @param action  Key action
     * @param ctrl    Is a control key
     */
    public void setAction(int keyCode, Runnable action, boolean ctrl) {
        if (ctrl) {
            onCtrlPressed.put(keyCode, action);
        } else {
            onPressed.put(keyCode, action);
            autoControl.put(keyCode, action);
        }
    }

    /**
     * Clears the keybinding for chap.
     */
    public void clearKeyBind() {
        onPressed.clear();
    }

    /**
     * Called whenever key is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            onCtrlPressed.getOrDefault(e.getKeyCode(), () -> {
            }).run();
        } else {
            onPressed.getOrDefault(e.getKeyCode(), () -> {
            }).run();
            //for recorder
            if (autoControl.containsKey(e.getKeyCode())) {
                if (app.recorder != null) app.recorder.addMove(new directionMove(e.getKeyCode()));
            }
        }

        //System.out.println("size: "+onPressed.size());
    }

    /**
     * Fuzz: keyPressed method for calling with manual keycode.
     *
     * @param keycode constant representing a key
     */
    public void keyPressed(int keycode) {
        autoControl.getOrDefault(keycode, () -> {
        }).run();
    }

    /**
     * Called when keys are released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Called when keys are typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

}
