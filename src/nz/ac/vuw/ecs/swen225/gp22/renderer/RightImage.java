package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class RightImage implements CatImage{
	public Img currImg = Img.CatRight1; // Default fields are normal chap
	public Img[] animation = {Img.CatRight1, Img.CatRight2, Img.CatRight3, Img.CatRight4};
	public boolean done = false; 

	/**
	 * Get the current image of the chap. 
	 * This method will iterate through the four
	 * images for the animation. 
	 */
	@Override
	public Img getCurrImg() {
		if(currImg.equals(Img.CatRight1)) {
			done = false;
			currImg = animation[1];
		}else if(currImg.equals(Img.CatRight2)) {
			currImg = animation[2];
		}else if(currImg.equals(Img.CatRight3)) {
			currImg = animation[3];
		}else {
			currImg = animation[0];
			done = true;
		}
		return currImg; 
	}

	/**
	 * If the animation is done.
	 */
	@Override
	public boolean done() {
		return done;
	}

}
