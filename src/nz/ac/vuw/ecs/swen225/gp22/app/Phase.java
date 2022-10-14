package nz.ac.vuw.ecs.swen225.gp22.app;

import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Filereader;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

/**
 * Stores the essential classes needed for the game level.
 * 
 * @author Naomi Parte 300562058
 * 
 */
public record Phase(Maze maze, Controller controller) {
	/**
	 * Runnables to change the phase when player dies, or when player finished a level.
	 * 
	 */
	static Runnable next;
	static Runnable first;
	
	/**
	 * Creates level one.
	 * 
	 * @param n runs on next level
	 * @param f runs when chap dies
	 * @return Phase
	 * 
	 */
	public static Phase levelOne(Runnable n, Runnable f) {
		next = n;
		first = f;

		Level lvl = new Filereader().loadLevel("levels/level1.xml");
		Controller c = new Controller(App.getInstance(), lvl.getChap(), false);
		Maze m = null;
		try {
			m = new Maze(lvl, 22, 22);
			lvl.getChap().setMaze(m); 
		} catch (IOException e) {e.printStackTrace();}
		return new Phase(m, c); 
	}

	/**
	 * Creates level two.
	 * 
	 * @param n runs on next level
	 * @param f runs when chap dies
	 * @return Phase
	 * 
	 */
	public static Phase levelTwo(Runnable n, Runnable f) {
		next = n;
		first = f;

		Level lvl = new Filereader().loadLevel("levels/level2.xml");
		Controller c = new Controller(App.getInstance(), lvl.getChap(), false);
		Maze m = null;
		try {
			m = new Maze(lvl, 66, 66);
			lvl.getChap().setMaze(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Phase(m, c);
	}


	/**
	 * Creates phase for recorder replay.
	 * 
	 * @param level level number
	 * @return Phase
	 */
	public static Phase replayPhase(int level){

		String filename = level==2?"level2.xml":"level1.xml";
		int size = level==2?66:22;

		Level lvl = new Filereader().loadLevel("levels/"+filename);
		Controller c = new Controller(App.getInstance(), lvl.getChap(), true);
		c.clearKeyBind();
		Maze m = null;
		try {
			m = new Maze(lvl, size, size);
			lvl.getChap().setMaze(m); 
		} catch (IOException e) {e.printStackTrace();}
		
		return new Phase(m, c);
	}
}
