package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class SoundEffects {
    Clip startMusic;
    Clip backgroundMusic; 
    Clip walkSound; 
    Clip treasureSound;
    Clip completeLevel; 

    public SoundEffects() {
        File startFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/StartSound.wav");
        File backgroundFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/BackgroundSound.wav");
        try {
            AudioInputStream audioStreamStart = AudioSystem.getAudioInputStream(startFile);
            startMusic = AudioSystem.getClip();
            startMusic.open(audioStreamStart);
            
            AudioInputStream audioStreamBackground = AudioSystem.getAudioInputStream(backgroundFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStreamBackground);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start playing the start music clip
     */
    public void playStart() {
        startMusic.start();
        //  startMusic.setMicrosecondPosition(0); to reset the clip 
    }

    /**
     * Stop playing the start music clip
     */
    public void stopStart() {
        startMusic.stop(); 
    }
    
    
    public void playGameMusic() {
    	backgroundMusic.start();
    }
    
    public void stopGameMusic() {
    	backgroundMusic.stop();
    }

}