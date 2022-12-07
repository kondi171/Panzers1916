package dev.panzers1916.states;

import dev.panzers1916.Handler;
import dev.panzers1916.graphics.ImageLoader;
import dev.panzers1916.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Represents a GameState
 * @author Konrad Nowak */

public class GameState extends State{
    /** handler to a world class */
    private World world;
    /** storing a number of state option */
    private byte stateOption;
    /** Constructor set the handler
     * @param handler set handler */
    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
    }
    /** refreshing a world class */
    @Override
    public void tick() { world.tick(); }
    /** rendering all state
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g) {
        world.render(g);
        BufferedImage logoInGame = ImageLoader.loadImage("/img/logo_in_game_handler.png");
        logoInGame.getSubimage(0,0,50,50);
        g.drawImage(logoInGame,633,685,null);
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
