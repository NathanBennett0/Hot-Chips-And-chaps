package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;

public class Controller extends KeyStroke {
	Chap chap;
	Controller(Chap c){
		chap = c;
		setAction(KeyEvent.VK_W, ()->chap.moveUp());
		setAction(KeyEvent.VK_A, ()->chap.moveLeft());
		setAction(KeyEvent.VK_D, ()->chap.moveRight());
		setAction(KeyEvent.VK_S, ()->chap.moveDown());
	}
	
}
