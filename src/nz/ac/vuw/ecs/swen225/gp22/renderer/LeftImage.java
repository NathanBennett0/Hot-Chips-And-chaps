package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class LeftImage implements CatImage{
	public Img currImg = Img.CatLeft1; // Default fields are normal chap
	public Img[] animation = {Img.CatLeft1, Img.CatLeft2, Img.CatLeft3, Img.CatLeft4};
	public boolean done = false; 

	/**
	 * Get the current image of the chap. 
	 * This method will iterate through the four
	 * images for the animation. 
	 */
	@Override
	public Img getCurrImg() {
		if(currImg.equals(Img.CatLeft1)) {
			done = false; 
			currImg = animation[1];
		}else if(currImg.equals(Img.CatLeft2)) {
			currImg = animation[2];
		}else if(currImg.equals(Img.CatLeft3)) {
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
