package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public enum Img {
    Box, CatDown1, CatDown2, CatDown3, CatDown4, CatLeft1, CatLeft2, CatLeft3, CatLeft4, CatRight1, CatRight2, CatRight3,
    CatRight4, CatUp1, CatUp2, CatUp3, CatUp4, Chap, EndBackground, EndCat1, EndCat2, EndCat3, EndCat4, EndCat5, Exit,
    ExitLock, Free, GameOver, GameBackground, Info, InfoPopUp, KeyBlue, KeyGreen, KeyOrange, KeyYellow, LawnmowerDown, LawnmowerLeft, 
    LawnMowerRight, LockedDoorBlue,LockedDoorGreen, LockedDoorOrange, LockedDoorYellow, StartOne, StartTwo, TreasureOne, 
    TreasureTwo, TreasureThree, TreasureFour, TreasureFive, Tutorial, Wall, Water;

    // Note: the bellow code was inspired by the Img.Java class from assignment one
    public final BufferedImage image;

    Img() {
        image = loadImage(this.name());
    }

    /**
     * Return the image that is linked to a certain image
     * @param name
     * @return
     */
    static private BufferedImage loadImage(String name) {
        URL imagePath = Img.class.getResource("Images/" + name + ".png");
        try {
            return ImageIO.read(imagePath);
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
