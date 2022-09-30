package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

public interface Model {
	Maze maze();
	void onGameOver();
	void onNextLevel();  //think of how to detect this later
}
