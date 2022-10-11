package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;

public class KeyStroke implements KeyListener {
	private static Map<Integer,Runnable> onPressed = new HashMap<>();
	private static Map<Integer,Runnable> onCtrlPressed = new HashMap<>();
	//private Map<Integer,Runnable> onReleased = new HashMap<>();
	
	public void setAction(int keyCode, Runnable action, boolean ctrl) {
		System.out.println("KeyStroke.java: setAction() called.");
		if(ctrl) {
			onCtrlPressed.put(keyCode, action);
		}else {
			onPressed.put(keyCode, action);
		}
	}
	
	public void clearKeyBind(){
		onPressed.clear();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown()) {
			onCtrlPressed.getOrDefault(e.getKeyCode(),()->{}).run();
		}else {
			onPressed.getOrDefault(e.getKeyCode(),()->{}).run();

			// if(onPressed.containsKey(e.getKeyCode())){ //and no pause
			// 	recorder.history.add(e.getKeyCode());
			// }
			
		}
	}

	/**
	 * Fuzz: keyPressed method for calling with manual keycode
	 * @param keycode constant representing a key
	 */
	public void keyPressed(int keycode) {
		onPressed.getOrDefault(keycode,()->{}).run();
	}


	@Override
	public void keyReleased(KeyEvent e) {}

}
