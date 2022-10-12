package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import nz.ac.vuw.ecs.swen225.gp22.domain.Chap;

/**
 * Controller class for key binding with the app and chap.
 * 
 * @author	Naomi Parte
 */
public class Controller extends KeyStroke {
	Chap chap;

	/**
	 * Main game controller constructor.
	 * @param app
	 */
	Controller(App app){
		super(app);
		setCtrlKey();
	}

	/**
	 * Chap controller constructor.
	 * 
	 * @param app
	 * @param chap
	 */
	Controller(App app, Chap chap){
		super(app);
		this.chap = chap;
		setCtrlKey();
		setPauseKey();
		setChapKey();
		
	}

	/**
	 * Key binding for control keys.
	 * 
	 */
	public void setCtrlKey(){
		//Control Keys
		setAction(KeyEvent.VK_X, ()->app.exitGame.run(), true); //exit
		setAction(KeyEvent.VK_S, ()->{app.saveGame(); app.exitGame.run();}, true); //save
		setAction(KeyEvent.VK_R, ()->app.loadSavedGame(app.loadsave), true); //resume saved game
		setAction(KeyEvent.VK_1, ()->app.phaseOne(), true); //start level 1
		setAction(KeyEvent.VK_2, ()->app.phaseTwo(), true); //start level 2
		
	}

	/**
	 * Key binding for pause and resume key.
	 * 
	 */
	public void setPauseKey(){
		setAction(KeyEvent.VK_SPACE, app.pauseGame, false); //pause game
		setAction(KeyEvent.VK_ESCAPE, app.resumeGame, false); //resume paused game
	}

	/**
	 * Key binding for moving chap key.
	 * 
	 */
	public void setChapKey(){
		//Moves chap across the maze
		setAction(KeyEvent.VK_W, ()->chap.moveUp(), false); 
		setAction(KeyEvent.VK_A, ()->chap.moveLeft(), false);
		setAction(KeyEvent.VK_D, ()->chap.moveRight(), false);
		setAction(KeyEvent.VK_S, ()->chap.moveDown(), false);
		setAction(KeyEvent.VK_UP, ()->chap.moveUp(), false);
		setAction(KeyEvent.VK_LEFT, ()->chap.moveLeft(), false);
		setAction(KeyEvent.VK_RIGHT, ()->chap.moveRight(), false);
		setAction(KeyEvent.VK_DOWN, ()->chap.moveDown(), false);
	}

	
}
