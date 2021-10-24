package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionState extends State {

    private byte stateOption;
    private byte choose;
    private boolean fullscreen;
    private boolean sounds;
    private boolean music;
    public OptionState(Handler handler){
        super(handler);
        fullscreen = false;
        sounds = true;
        music = true;
    }
    @Override
    public byte getStateOption(){
        return stateOption;
    }
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }
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

    @Override
    public void render(Graphics g){
        Text.drawString(g,"Options",width / 2, height / 2 - 300, true,Color.darkGray ,Assets.mainFont84);
        if(choose <= 0){
            if(!fullscreen)
                Text.drawString(g,"FullScreen: OFF",width/2,height/2 - 100,true,Assets.mainColor, Assets.secondFont56);
            else Text.drawString(g,"FullScreen: ON",width/2,height/2 - 100,true,Assets.mainColor, Assets.secondFont56);
        }
        else if(choose == 1){
            if(!music)
                Text.drawString(g,"Music: OFF",width/2,height/2 ,true,Assets.mainColor,Assets.secondFont56);
            else Text.drawString(g,"Music: ON",width/2,height/2 ,true,Assets.mainColor,Assets.secondFont56);
        }
        else if(choose == 2){
            if(!sounds)
                Text.drawString(g,"Sounds: OFF",width/2,height/2 + 100,true,Assets.mainColor,Assets.secondFont56);
            else Text.drawString(g,"Sounds: ON",width/2,height/2 + 100,true,Assets.mainColor,Assets.secondFont56);
        }
        else if(choose == 3)Text.drawString(g,"Back",width/2,height/2 + 200,true,Assets.mainColor,Assets.secondFont56);
    }
    public boolean isFullscreen() { return fullscreen; }
    public boolean isSounds() { return sounds; }
    public boolean isMusic() { return music; }
}
