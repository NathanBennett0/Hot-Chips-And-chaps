package nz.ac.vuw.ecs.swen225.gp22.renderer;

public class NormalImage implements CatImage{
	public Img currImg = Img.Chap; // Default fields are normal chap
	public Img[] animation = {Img.Chap};
	public boolean done = false; 
	
	public Img getCurrImg() {
		return currImg; 
	}
	public boolean done() {
		return true;
	}

}
