package dev.panzers1916.input;

import dev.panzers1916.Game;
import dev.panzers1916.states.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean upPioner,downPioner,leftPioner,rightPioner;
    public boolean upSpenser,downSpenser,leftSpenser,rightSpenser;
    public boolean pionerShoot, spenserShoot;
    private Game game;

    public KeyManager(Game game) {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
        this.game = game;
    }

    public void tick(){
        for(int i = 0; i < keys.length; i++){
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        if(State.getState() == game.getGameState()) {
            upPioner = keys[KeyEvent.VK_W];
            downPioner = keys[KeyEvent.VK_S];
            leftPioner = keys[KeyEvent.VK_A];
            rightPioner = keys[KeyEvent.VK_D];

            upSpenser = keys[KeyEvent.VK_UP];
            downSpenser = keys[KeyEvent.VK_DOWN];
            leftSpenser = keys[KeyEvent.VK_LEFT];
            rightSpenser = keys[KeyEvent.VK_RIGHT];

            pionerShoot = keys[KeyEvent.VK_SPACE];
            spenserShoot = keys[KeyEvent.VK_ENTER];
        }
    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) { /*Must Override! We don't use it*/ }
}
