package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class NormalImage implements CatImage{
	public Img currImg = Img.Chap; // Default fields are normal chap
	public Img[] animation = {Img.Chap};
	public boolean done = false; 
	
	/**
	 * Get the current image of the chap
	 */
	public Img getCurrImg() {
		return currImg; 
	}
	
	/**
	 * If the chap has finished the cycle of the animation
	 */
	public boolean done() {
		return true;
	}

}
