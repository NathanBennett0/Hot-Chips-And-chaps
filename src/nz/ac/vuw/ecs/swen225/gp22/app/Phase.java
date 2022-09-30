package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

record Phase(Model model, Controller controller) {
	
	static Phase level(Runnable next, Runnable first, Maze maze) {
		
		
		return new Phase(null, null); //change
	}
	
}
