package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp22.Recorder.directionMove;

/**
 * Custom keyListener class for the game.
 * 
 * @author Naomi Parte
 * 
 */
public class KeyStroke implements KeyListener {
	private static Map<Integer,Runnable> onPressed = new HashMap<>();
	private static Map<Integer,Runnable> onCtrlPressed = new HashMap<>();
	protected App app;

	KeyStroke(App app){
		this.app = app;
	}

	/**
	 * A function for keybinding - Maps a keycode with given action.
	 * 
	 * @param keyCode
	 * @param action
	 * @param ctrl
	 * 
	 */
	public void setAction(int keyCode, Runnable action, boolean ctrl) {
		if(ctrl) {
			onCtrlPressed.put(keyCode, action);
		}else {
			onPressed.put(keyCode, action);
		}
	}

	/**
	 * Clears the keybinding for chap.
	 * 
	 */
	public void clearKeyBind(){
		onPressed.clear();
	}

	/**
	 * Called whenever key is pressed.
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown()) {
			onCtrlPressed.getOrDefault(e.getKeyCode(),()->{}).run();
		}else {
			onPressed.getOrDefault(e.getKeyCode(),()->{}).run();
			//for recorder
			if(onPressed.containsKey(e.getKeyCode())){
				if(app.recorder != null) app.recorder.addMove(new directionMove(e.getKeyCode()));
			}
		}

		System.out.println("size: "+onPressed.size());
	}

	/**
	 * Fuzz: keyPressed method for calling with manual keycode.
	 * 
	 * @param keycode constant representing a key
	 * 
	 */
	public void keyPressed(int keycode) {
		onPressed.getOrDefault(keycode,()->{}).run();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

}
