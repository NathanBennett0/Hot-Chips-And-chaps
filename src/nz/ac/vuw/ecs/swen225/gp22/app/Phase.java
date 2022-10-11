package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Level;

public record Phase(Maze maze, Controller controller, Level level) {
	
	static Phase level(Runnable next, Runnable first, Maze maze) {
		
		
		return new Phase(null, null, null); //change
	}
	
}
