package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class DownImage implements CatImage{
	public Img currImg = Img.CatDown1;
	public Img[] animation = {Img.CatDown1, Img.CatDown2, Img.CatDown3};
	public boolean done = false; 

	@Override
	public Img getCurrImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean done() {
		return done;
	}

}
