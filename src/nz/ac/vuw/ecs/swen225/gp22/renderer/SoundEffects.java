package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class SoundEffects {
    Clip startClip; 

    public SoundEffects() {
        File startSound = new File("StartSound.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(startSound);
            startClip = AudioSystem.getClip();
            startClip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playStart() {
        Scanner scanner = new Scanner(System.in);
        startClip.start();
        do {
            try {Thread.sleep(50);}
            catch(InterruptedException ie) {ie.printStackTrace();}
        }while( startClip.isActive());

        //  startClip.setMicrosecondPosition(0); to reset the clip 
    }

    public void stopStart() {
        startClip.stop(); 
    }

}