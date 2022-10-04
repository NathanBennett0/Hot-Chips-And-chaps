package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class DownImage implements CatImage{
	public Img currImg = Img.CatDown1;
	public Img[] animation = {Img.CatDown1, Img.CatDown2, Img.CatDown3, Img.CatDown4};
	public boolean done = false; 

	@Override
	public Img getCurrImg() {
		if(currImg.equals(Img.CatDown1)) {
			done = false;
			currImg = animation[1];
		}else if(currImg.equals(Img.CatDown2)) {
			currImg = animation[2];
		}else if(currImg.equals(Img.CatDown3)) {
			currImg = animation[3];
		}else {
			currImg = animation[0];
			done = true; 
		}
		return currImg; 
	}

	@Override
	public boolean done() {
		return done;
	}

}
