package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;

public class Controller extends KeyStroke {
	App app;
	Controller(App app){
		this.app = app;
		setCtrlKey();
	}

	Controller(App app, Chap chap){
		this.app = app;

		setCtrlKey();
		
		//Moves chap across the maze
		setAction(KeyEvent.VK_W, ()->chap.moveUp(), false); 
		setAction(KeyEvent.VK_A, ()->chap.moveLeft(), false);
		setAction(KeyEvent.VK_D, ()->chap.moveRight(), false);
		setAction(KeyEvent.VK_S, ()->chap.moveDown(), false);
		setAction(KeyEvent.VK_UP, ()->chap.moveUp(), false);
		setAction(KeyEvent.VK_LEFT, ()->chap.moveLeft(), false);
		setAction(KeyEvent.VK_RIGHT, ()->chap.moveRight(), false);
		setAction(KeyEvent.VK_DOWN, ()->chap.moveDown(), false);
		

		setAction(KeyEvent.VK_SPACE, null, false); //pause game
		setAction(KeyEvent.VK_ESCAPE, null, false); //resume paused game
		
	}

	public void setCtrlKey(){
		//Control Keys
		setAction(KeyEvent.VK_X, ()->System.exit(0), true); //exit
		setAction(KeyEvent.VK_S, ()->app.saveGame(), true); //save
		setAction(KeyEvent.VK_R, null, true); //resume saved game
		setAction(KeyEvent.VK_1, ()->app.levelOne(), true); //start level 1
		setAction(KeyEvent.VK_2, ()->app.levelTwo(), true); //start level 2
	}

	
}
