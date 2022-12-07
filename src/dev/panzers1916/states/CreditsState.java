package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

/** Represents a CreditsState
 * @author Konrad Nowak */

public class CreditsState extends State {
    /** storing a number of state option */
    private byte stateOption;

    /** Constructor set the handler
     * @param handler set handler */
    public CreditsState(Handler handler){
        super(handler);
    }
    /** listener for accept a option */
    @Override
    public void tick() { if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) stateOption = 1; }

    /** rendering all state
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g) {
        g.drawRect(0,0,width,height);
        Text.drawString(g,"Credits",width / 2, height / 2 - 300, true,Assets.mainColor , Assets.mainFont84);
        Text.drawString(g,"Konrad Nowak",width / 2, height / 2 - 100, true,Color.darkGray , Assets.secondFont56);
        Text.drawString(g,"Kamil Wypych",width / 2, height / 2 , true,Color.darkGray , Assets.secondFont56);
        Text.drawString(g,"Exit",width / 2, height / 2 + 300, true,Assets.mainColor, Assets.secondFont56);
    }

    /** getter for <b>stateOption</b>
      * @return stateOption */
    @Override
    public byte getStateOption(){ return stateOption; }

    /** setter for <b>stateOption</b>
     * @param stateOption set the state option */
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }
}
