package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Free extends Tile{

	public Free(Location l) {
		super(l);
		// TODO Auto-generated constructor stub
	} //empty tile

	@Override
	public boolean CanWalkOn(Chap p) {
		return true;
	}
}
