package nz.ac.vuw.ecs.swen225.gp22.domain;

public interface Entity { //needed?
	public Location getLocation();

	/**
	 * determines if Chap can walk on an entity
	 * @param p
	 * @return
	 */
	public boolean CanWalkOn(Chap p);
}
