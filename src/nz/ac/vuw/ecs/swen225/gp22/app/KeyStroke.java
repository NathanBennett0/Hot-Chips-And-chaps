package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.SwingUtilities;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;

public class KeyStroke implements KeyListener {
	private Map<Integer,Runnable> onPressed = new HashMap<>();
	//private Map<Integer,Runnable> onReleased = new HashMap<>();
	
	public void setAction(int keyCode, Runnable pressed) {
		onPressed.put(keyCode, pressed);
		//onReleased.put(keyCode, released);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		onPressed.getOrDefault(e.getKeyCode(),()->{}).run();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//onReleased.getOrDefault(e.getKeyCode(),()->{}).run();
	}

}
