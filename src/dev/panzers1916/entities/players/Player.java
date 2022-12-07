package dev.panzers1916.entities.players;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.Entity;
import dev.panzers1916.sounds.SoundLoader;
import dev.panzers1916.tiles.Tile;

import java.io.File;
/** Represents a Base abstract player class
 * @author Konrad Nowak */
public abstract class Player extends Entity{
    /** declaring final variables for player width and hight */
    public static final int DEFAULT_PLAYER_WIDTH = 50, DEFAULT_PLAYER_HEIGHT = 50;
    /** declaring a variables thanks to them we can move a entity */
    protected float xMove, yMove, speed;
    /** variables for storing sounds */
    protected SoundLoader bulletShoot,tankDrive;
    /** player frames */
    protected byte panzerFrames;

    /** Constructor for set starting value for Player class
     * @param handler set the handler
     * @param x set x coordinate
     * @param y set y coordinate
     * @param width set width player
     * @param height set height player */
    public Player(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
        bulletShoot = new SoundLoader(new File("res/sounds/runnable/startBullet.wav"),false);
        tankDrive = new SoundLoader(new File("res/sounds/runnable/tankDrive.wav"),false);
        speed = 3.0f;
        xMove = 0;
        yMove = 0;
    }

    /** reset a frames for tank
     * @return tank frames, if <b>panzerFrames</b> equals 100 then, set this variable for 0 */
    protected byte resetPanzerFrames(){
        if(panzerFrames >= 100) panzerFrames = 0;
        return panzerFrames;
    }
    /** moving a player */
    public void move(){
        moveX();
        moveY();
    }
    /** moving a player in X-axis */
    public void moveX(){
        if(xMove > 0){  //Move right
            int tx = (int) (x + xMove + 16)/ Tile.TILE_WIDTH;
            if(playerCollision(tx, (int) y / Tile.TILE_HEIGHT) && (x <= 1283)){
                x += xMove;
            }
        } else if(xMove < 0) {    //Move left
            int tx = (int) (x + xMove - 33) / Tile.TILE_WIDTH;
            if(playerCollision(tx, (int) y / Tile.TILE_HEIGHT) && x >= 33){
                x += xMove;
            }
        }
    }
    /** moving a player in Y-axis */
    public void moveY(){
        if(yMove < 0){  //Move Up
            int ty = (int) (y + yMove - 33) / Tile.TILE_HEIGHT;
            if(playerCollision((int) x / Tile.TILE_WIDTH, ty) && y >= 33){
                y += yMove;
            }
        }
        else if(yMove > 0){ //Move Down
            int ty = (int) (y + yMove + 16) / Tile.TILE_HEIGHT;
            if(playerCollision((int) x / Tile.TILE_WIDTH, ty) && y <= 633){
                y += yMove;
            }
        }
    }
    /** check the player collistion
     * @param x number of x tile
     * @param y number of y tile
     * @return true if collision exist */
    protected boolean playerCollision(int x, int y){ return !handler.getWorld().getTile(x, y).isSolid(); }
}
