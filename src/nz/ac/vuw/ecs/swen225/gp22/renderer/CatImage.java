package nz.ac.vuw.ecs.swen225.gp22.renderer;
/**
* Wipateella
* 300558005
*
* Interface to represent the chaps animation
*/
public interface CatImage {
	public Img currImg = Img.Chap; // Default fields are normal chap
	public Img[] animation = {Img.Chap};
	public boolean done = false; 
	
	public Img getCurrImg();
	public boolean done();
}
