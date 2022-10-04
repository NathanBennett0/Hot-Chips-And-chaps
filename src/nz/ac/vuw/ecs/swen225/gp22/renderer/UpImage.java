package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class UpImage implements CatImage{
	public Img currImg = Img.CatUp1; // Default fields are normal chap
	public Img[] animation = {Img.CatUp1};
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
