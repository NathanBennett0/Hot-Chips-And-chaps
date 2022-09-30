package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public interface Entity { // needed?
	public Location getLocation();
	public Img icon = Img.Free; // default tile is free

	/**
	 * determines if Chap can walk on an entity
	 * @param p
	 * @return
	 */
	public boolean CanWalkOn(Chap p);
}
