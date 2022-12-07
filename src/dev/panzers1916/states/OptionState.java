package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

/** Represents a OptionState class
 * @author Konrad Nowak */

public class OptionState extends State {
    /** storing a number of choose which we can decide what option was chosen */
    private byte choose;
    /** storing a number of state option */
    private byte stateOption;
    /** storing a statement of switched options */
    private boolean fullscreen, sounds, music;
    /** Constructor set the handler and default flags
     * @param handler set handler */
    public OptionState(Handler handler){
        super(handler);
        fullscreen = false;
        sounds = true;
        music = true;
    }
    /** switching options */
    @Override
    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            if (choose > 0) choose--;
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            if (choose < 3) choose++;
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            if(choose == 0){
                fullscreen = !fullscreen;
            } else if(choose == 1){
                music = !music;
                handler.getGame().setIsActiveMusic(true);
            } else if(choose == 2){
                sounds = !sounds;
                handler.getGame().setIsActiveSounds(true);
            } else if(choose == 3) this.stateOption = 4;
        }
    }
    /** rendering all state
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g){
        Text.drawString(g,"Options",width / 2, height / 2 - 300, true,Color.darkGray ,Assets.mainFont84);
        if(choose <= 0){
            if(!fullscreen) Text.drawString(g,"FullScreen: OFF",width/2,height/2 - 100,true,Assets.mainColor, Assets.secondFont56);
            else Text.drawString(g,"FullScreen: ON",width/2,height/2 - 100,true,Assets.mainColor, Assets.secondFont56);
        }
        else if(choose == 1){
            if(!music) Text.drawString(g,"Music: OFF",width/2,height/2 ,true,Assets.mainColor,Assets.secondFont56);
            else Text.drawString(g,"Music: ON",width/2,height/2 ,true,Assets.mainColor,Assets.secondFont56);
        }
        else if(choose == 2){
            if(!sounds) Text.drawString(g,"Sounds: OFF",width/2,height/2 + 100,true,Assets.mainColor,Assets.secondFont56);
            else Text.drawString(g,"Sounds: ON",width/2,height/2 + 100,true,Assets.mainColor,Assets.secondFont56);
        }
        else if(choose == 3)Text.drawString(g,"Back",width/2,height/2 + 200,true,Assets.mainColor,Assets.secondFont56);
    }
    /** getter for <b>fullscreen</b>
     * @return fullscreen */
    public boolean isFullscreen() { return fullscreen; }
    /** getter for <b>sounds</b>
     * @return sounds */
    public boolean isSounds() { return sounds; }
    /** getter for <b>music</b>
     * @return music */
    public boolean isMusic() { return music; }
    /** getter for <b>stateOption</b>
     * @return stateOption */
    @Override
    public byte getStateOption(){
        return stateOption;
    }
    /** setter for <b>stateOption</b>
     * @param stateOption set the state option */
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }
}
