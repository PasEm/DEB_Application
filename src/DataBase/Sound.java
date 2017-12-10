package DataBase;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static final Sound instance;
    private Clip clip;
    private boolean playing;

    private Sound() {
        try {
            File soundFile = new File("Resources/Music/luciano_frolic.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
            playing = false;
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (UnsupportedAudioFileException e){
            e.printStackTrace();
            System.out.println("Неверный формат входного аудиофайла. Перезапустите приложение, проверив формат аудиофайла");
        }
        catch (LineUnavailableException e){
            e.printStackTrace();
            System.out.println("Невозможно использовать данный аудиофайл для прослушивания. Переустановите приложение");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка при загрузке файла, проверьте целостность системы. Перезапустите приложение, проверив наличие аудиофайла");
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
