package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class UpImage implements CatImage{
	public Img currImg = Img.CatUp1; // Default fields are normal chap
	public Img[] animation = {Img.CatUp1, Img.CatUp2, Img.CatUp3, Img.CatUp4};
	public boolean done = false; 

	@Override
	public Img getCurrImg() {
		if(currImg.equals(Img.CatUp1)) {
			done = false; 
			currImg = animation[1];
		}else if(currImg.equals(Img.CatUp2)) {
			currImg = animation[2];
		}else if(currImg.equals(Img.CatUp3)) {
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
