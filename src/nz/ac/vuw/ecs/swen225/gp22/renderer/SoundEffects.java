package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

/**
* Wipateella
* 300558005
*
* This class will load and hold all of the sound effects for the game
*/ 
public class SoundEffects {
    Clip startMusic;
    Clip backgroundMusic; 
    Clip collectSound;
    Clip unlockSound;
    Clip completeLevel; 

    public SoundEffects() {
        File startFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/StartSound.wav");
        File backgroundFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/BackgroundSound.wav");
        File completeLevelFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/GameOverSound.wav");
        File unlockSoundFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/UnlockSound.wav");
        File collectSoundFile = new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/CollectSound.wav");
        try {
            AudioInputStream audioStreamStart = AudioSystem.getAudioInputStream(startFile);
            startMusic = AudioSystem.getClip();
            startMusic.open(audioStreamStart);
            
            AudioInputStream audioStreamBackground = AudioSystem.getAudioInputStream(backgroundFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStreamBackground);
            
            AudioInputStream audioStreamCompleteLevel = AudioSystem.getAudioInputStream(completeLevelFile);
            completeLevel = AudioSystem.getClip();
            completeLevel.open(audioStreamCompleteLevel);
            
            AudioInputStream audioStreamCollect = AudioSystem.getAudioInputStream(collectSoundFile);
            collectSound = AudioSystem.getClip();
            collectSound.open(audioStreamCollect);
            
            AudioInputStream audioStreamUnlock = AudioSystem.getAudioInputStream(unlockSoundFile);
            unlockSound = AudioSystem.getClip();
            unlockSound.open(audioStreamUnlock);
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
    }

    /**
     * Stop playing the start music clip
     */
    public void stopStart() {
        startMusic.stop(); 
    }
    
    /**
     * Start playing the background music for the game
     */
    public void playGameMusic() {
    	backgroundMusic.setMicrosecondPosition(0);
    	backgroundMusic.start();
    }
    
    /**
     * Stop playing the background music for the game
     */
    public void stopGameMusic() {
    	backgroundMusic.stop();
    }
    
    /**
     * Play the congrats music once level has been completed
     */
    public void playCongratsMusic() {
    	completeLevel.setMicrosecondPosition(0);
    	completeLevel.start(); 
    }
    
    /**
     * When a treasure or key has been picked up, 
     * play collect sound effect
     */
    public void playCollectMusic() {
    	collectSound.setMicrosecondPosition(0);
    	collectSound.start(); 
    }
    
    /**
     * When a bush has been unlocked, 
     * play unlock sound effect
     */
    public void playUnlockMusic() {
    	unlockSound.setMicrosecondPosition(0);
    	unlockSound.start();
    }

}
