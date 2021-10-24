package dev.panzers1916.states;

import dev.panzers1916.Handler;

import java.awt.*;

public abstract class State {
    protected Handler handler;
    protected final int width = 1366;
    protected final int height = 768;
    private static State currentState = null;
    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract byte getStateOption();
    public abstract void setStateOption(byte stateOption);
}
