package dev.panzers1916.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/** Represents a SoundLoader class
 * Main Music Composed by Kamil Wypych<br>
 * @author Kamil Wypych */

public class SoundLoader {
    /** declaration file object which store a filepath */
    private File filePath;
    /** sound variable */
    private Clip clip;
    /** flag for loop */
    private boolean loop;

    /**Constructor for SoundLoader
     * @param filePath file path
     * @param loop loop */
    public SoundLoader(File filePath, boolean loop) {
        this.filePath = filePath;
        this.loop = loop;
    }

    /** this method starts playing a music
     * @param volume set the volume of sound */
    public void play(float volume){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(filePath));
            loop();
            volumeControl(volume);
            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /** this method regulates a volume of sound
     * @param volume set the volume for method */
    public void volumeControl(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
    /** if variable <b>loop</b> is true, then loop the sound */
    public void loop(){
        if(loop) clip.loop(1000000000);
    }
    /** stop the sound */
    public void stop(){ clip.stop(); }
}
