package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends State {

    private byte choose;
    private boolean winner;
    private byte stateOption;
    //true = pioner;
    //false = spenser;
    public GameOverState(Handler handler, boolean winner){
        super(handler);
        this.winner = winner;
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            stateOption = 1;
        }
    }

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

        if(winner)
            Text.drawString(g,"Pioner Wins!",width / 2, height / 2 + 100, true,Assets.pionerColor, Assets.secondFont56);
        else Text.drawString(g,"Spenser Wins!",width / 2, height / 2 + 100, true,Assets.spenserColor, Assets.secondFont56);

        Text.drawString(g,"Exit",width / 2, height / 2 + 300, true,Assets.mainColor, Assets.secondFont56);
    }

    @Override
    public byte getStateOption() {
        return stateOption;
    }
    @Override
    public void setStateOption(byte stateOption) {
        this.stateOption = stateOption;
    }
}
