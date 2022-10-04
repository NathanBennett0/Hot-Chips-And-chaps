package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class RightImage implements CatImage{
	public Img currImg = Img.CatRight1; // Default fields are normal chap
	public Img[] animation = {Img.CatRight1};
	public boolean done = false; 

	@Override
	public Img getCurrImg() {
		return null;
	}

	@Override
	public boolean done() {
		return done;
	}

}
