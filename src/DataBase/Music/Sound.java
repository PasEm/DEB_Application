package DataBase.Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private static final Sound instance;
    private Clip clip;
    private boolean playing;

    private Sound() {
        try {
            File soundFile = new File("C:\\Users\\bloof\\Desktop\\DEB_Application\\src\\DataBase\\Music\\luciano_frolic.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
            playing = false;
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isPlaying(){
        return playing;
    }

    public void play(){
        playing = true;
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
        playing = false;
    }

    static {
        instance = new Sound();
    }

    public static Sound getSound(){
        return instance;
    }
}
