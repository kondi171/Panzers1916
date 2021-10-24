package dev.panzers1916.entities.players;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.Entity;
import dev.panzers1916.sounds.SoundLoader;
import dev.panzers1916.tiles.Tile;

import java.io.File;

public abstract class Player extends Entity{

    public static final int DEFAULT_PLAYER_WIDTH = 50,
                            DEFAULT_PLAYER_HEIGHT = 50;
    protected float xMove, yMove, speed;
    protected SoundLoader bulletShoot,tankDrive;
    public Player(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
        speed = 3.0f;
        xMove = 0;
        yMove = 0;
        bulletShoot = new SoundLoader(new File("res/sounds/runnable/startBullet.wav"),false);
        tankDrive = new SoundLoader(new File("res/sounds/runnable/tankDrive.wav"),false);
    }

    public void move(){
        moveX();
        moveY();
    }

    public void moveX(){
        if(xMove > 0){  //Move right
            int tx = (int) (x + xMove + 16)/ Tile.TILE_WIDTH;
            if(!playerCollision(tx,(int) y / Tile.TILE_HEIGHT) && (x <= 1283)){
                x += xMove;
            }
        } else if(xMove < 0) {    //Move left
            int tx = (int) (x + xMove - 33) / Tile.TILE_WIDTH;
            if(!playerCollision(tx,(int) y / Tile.TILE_HEIGHT) && x >= 33){
                x += xMove;
            }
        }
    }

    public void moveY(){
        if(yMove < 0){  //Move Up
            int ty = (int) (y + yMove - 33) / Tile.TILE_HEIGHT;
            if(!playerCollision((int) x / Tile.TILE_WIDTH, ty) && y >= 33){
                y += yMove;
            }
        }
        else if(yMove > 0){ //Move Down
            int ty = (int) (y + yMove + 16) / Tile.TILE_HEIGHT;
            if(!playerCollision((int) x / Tile.TILE_WIDTH, ty) && y <= 633){
                y += yMove;
            }
        }
    }

    protected boolean playerCollision(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getxMove() {
        return xMove;
    }
    public void setxMove(float xMove) {
        this.xMove = xMove;
    }
    public float getyMove() {
        return yMove;
    }
    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

}
