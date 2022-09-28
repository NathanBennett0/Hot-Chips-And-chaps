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
		System.out.println("KeyStroke.java: setAction() called.");
		onPressed.put(keyCode, pressed);
		//onReleased.put(keyCode, released);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyStroke.java: keyPressed() called.");
		onPressed.getOrDefault(e.getKeyCode(),()->{}).run();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyStroke.java: keyReleased() called.");
		//onReleased.getOrDefault(e.getKeyCode(),()->{}).run();
	}

}
