package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

/** Represents a GameOverState
 * @author Konrad Nowak */

public class GameOverState extends State {
    /** storing a number of state option */
    private byte stateOption;
    /** Constructor set the handler
     * @param handler set handler */
    public GameOverState(Handler handler){
        super(handler);
    }
    /** listener for accept a option, and exit game if enter pressed */
    @Override
    public void tick() { if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) handler.getGame().getMenuState().setStateOption((byte)4); }
    /** rendering all state
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        Text.drawString(g,"GameOver",width / 2, height / 2 - 300, true,Assets.mainColor, Assets.mainFont84);
        Text.drawString(g,"That was incredible battle!",width / 2, height / 2 - 200, true,Color.darkGray, Assets.secondFont56);
        Text.drawString(g,"Pioner: ",width / 2 - 300, height / 2 - 100, true,Assets.pionerColor, Assets.secondFont56);
        Text.drawString(g,"Points: " + Pioner.points,width / 2 - 300, height / 2, true,Assets.pionerColor, Assets.secondFont56);
        Text.drawString(g,"Spenser: ",width / 2 + 300, height / 2 - 100, true,Assets.spenserColor, Assets.secondFont56);
        Text.drawString(g,"Points: " + Spenser.points,width / 2 + 300, height / 2, true,Assets.spenserColor, Assets.secondFont56);

        Text.drawString(g,"Exit",width / 2, height / 2 + 300, true,Assets.mainColor, Assets.secondFont56);
    }
    /** getter for <b>stateOption</b>
     * @return stateOption */
    @Override
    public byte getStateOption() { return stateOption; }
    /** setter for <b>stateOption</b>
     * @param stateOption set the state option */
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }
}
