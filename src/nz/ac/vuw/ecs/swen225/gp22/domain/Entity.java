package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Img;

public interface Entity { //needed?
	public Location getLocation();
	public boolean CanWalkOn();
	public Img icon; 
}
