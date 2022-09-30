package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public enum Img {
	Box, Chap, Exit, ExitLock, Free, GameBackground, Info, KeyBlue, KeyGreen, KeyOrange, KeyYellow, LockedDoorBlue,
	LockedDoorGreen, LockedDoorOrange, LockedDoorYellow, StartOne, StartTwo, TreasureOne, TreasureTwo, TreasureThree,
	TreasureFour, TreasureFive, Tutorial, Wall, Water;

	// Note: the bellow code was inspired by the Img.Java class from assignment one
	public final BufferedImage image;

	Img() {
		image = loadImage(this.name());
	}

	static private BufferedImage loadImage(String name) {
		URL imagePath = Img.class.getResource(name + ".png");
		try {
			return ImageIO.read(imagePath);
		} catch (IOException e) {
			throw new Error(e);
		}
	}
}
