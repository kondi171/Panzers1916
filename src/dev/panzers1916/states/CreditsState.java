package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CreditsState extends State {
    private byte stateOption;
    public CreditsState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            stateOption = 1;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawRect(0,0,width,height);
        Text.drawString(g,"Credits",width / 2, height / 2 - 300, true,Assets.mainColor , Assets.mainFont84);
        Text.drawString(g,"Konrad Nowak",width / 2, height / 2 - 100, true,Color.darkGray , Assets.secondFont56);
        Text.drawString(g,"Kamil Wypych",width / 2, height / 2 , true,Color.darkGray , Assets.secondFont56);
        Text.drawString(g,"Adam Wróblewski",width / 2, height / 2 + 100, true,Color.darkGray , Assets.secondFont56);
        Text.drawString(g,"Exit",width / 2, height / 2 + 300, true,Assets.mainColor, Assets.secondFont56);
    }

    @Override
    public byte getStateOption(){
        return stateOption;
    }
    @Override
    public void setStateOption(byte stateOption) { this.stateOption = stateOption; }
}
