package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

record Phase(Maze maze, Controller controller, int level) {
	
	static Phase level(Runnable next, Runnable first, Maze maze) {
		
		
		return new Phase(null, null, 0); //change
	}
	
}
