package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.ImageLoader;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/** Represents a MenuState class
 * @author Konrad Nowak */

public class MenuState extends State {
    /** storing a number of state option */
    private byte stateOption;
    /** storing a number of choose which we can decide what option was chosen */
    private int choose;
    /** Constructor set the handler
     * @param handler set handler */
    public MenuState(Handler handler) {
        super(handler);
    }
    /** switching options */
    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
            if(choose > 0) choose--;
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
            if(choose < 3) choose++;
        }
        else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            if(choose == 0){
                this.stateOption = 1;
            } else if(choose == 1){
                this.stateOption = 2;
            } else if(choose == 2){
                this.stateOption = 3;
            } else if(choose == 3){
                this.stateOption = 4;
            }
        }
    }
    /** rendering all state
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g){
        BufferedImage mainLogo = ImageLoader.loadImage("/img/main_logo.png");
        mainLogo.getSubimage(0,0,50,50);
        g.drawImage(mainLogo,width/2-170,100,null);
        if(choose == 0) Text.drawString(g,"Start",width/2,height/2 - 100,true,Assets.mainColor, Assets.secondFont56);
        else if(choose == 1) Text.drawString(g,"Options",width/2,height/2 ,true,Assets.mainColor,Assets.secondFont56);
        else if(choose == 2) Text.drawString(g,"Credits",width/2,height/2 + 100,true,Assets.mainColor,Assets.secondFont56);
        else if(choose == 3)Text.drawString(g,"Exit",width/2,height/2 + 200,true,Assets.mainColor,Assets.secondFont56);
    }
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
