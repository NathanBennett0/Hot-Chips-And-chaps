package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public interface Entity { // needed?
	public Location getLocation();

	/**
	 * determines if Chap can walk on an entity
	 * @param Chap 
	 * @return boolean 
	 */
	public boolean CanWalkOn(Chap p);
}
