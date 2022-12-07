package dev.panzers1916.states;

import dev.panzers1916.Handler;

import java.awt.*;

/** Represents a abstract State class
 * @author Konrad Nowak */

public abstract class State {
    /** declaring handler */
    protected Handler handler;
    /** declaring final variables: width and height */
    protected final int width = 1366, height = 768;
    /** this variable storing a current state */
    private static State currentState;

    /** Constructor set the <b>currentState</b> to null and set a handler
     * @param handler set the handler */
    public State(Handler handler){
        currentState = null;
        this.handler = handler;
    }
    /** abstract method to overridden in subclass */
    public abstract void tick();

    /** abstract method to overridden in subclass
     * @param g Graphics element inherited from JFrame */
    public abstract void render(Graphics g);

    /** abstract method to overridden in subclass
     * @return stateOption */
    public abstract byte getStateOption();

    /** abstract method to overridden in subclass
     * @param stateOption set stateOption */
    public abstract void setStateOption(byte stateOption);
    /** setter for <b>currentState</b>
     * @param state set the state option */
    public static void setState(State state){
        currentState = state;
    }
    /** getter for <b>currentState</b>
     * @return currentState */
    public static State getState(){
        return currentState;
    }
}
