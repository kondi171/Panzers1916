package dev.panzers1916.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundLoader {

    private File filePath;
    private Clip clip;
    private boolean loop;
    public SoundLoader(File filePath, boolean loop) {
        this.filePath = filePath;
        this.loop = loop;
    }

    public void play(){
        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(filePath));
            clip.start();
            if(loop) clip.loop(100000000);
           // Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){ clip.stop(); }
}
