package dev.panzers1916;

import dev.panzers1916.input.KeyManager;
import dev.panzers1916.worlds.World;

/** Represents a main Handler class
 * @author Konrad Nowak */

public class Handler {
    /** handler for game */
    private Game game;
    /** handler for world */
    private World world;

    /** Contructor set the handler
     * @param game set the game object as a handler */
    public Handler(Game game){
        this.game = game;
    }

    /** getter for keyManager
     * @return a KeyManager object */
    public KeyManager getKeyManager(){ return game.getKeyManager(); }
    /** getter for width
     * @return width */
    public int getWidth(){
        return game.getWidth();
    }
    /** getter for height
     * @return height */
    public int getHeight(){ return game.getHeight();}
    /** getter for game object
     * @return game object */
    public Game getGame() {
        return game;
    }

    /** setter for game object
     * @param game set the game object */
    public void setGame(Game game) {
        this.game = game;
    }
    /** getter for world object
     * @return world object */
    public World getWorld() {
        return world;
    }
    /** setter for world object
     * @param world set the game object */
    public void setWorld(World world) {
        this.world = world;
    }
}
