package dev.panzers1916.bullet;

import dev.panzers1916.Game;
import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.tiles.Tile;

import java.awt.*;

/** Represents a Spenser bullet
 * @author Konrad Nowak */

public class SpenserBullet extends Bullet {
    /** this variable set a winner player, if winner equals 2, then winner is Spenser */
    public static byte winner = 0;
    /** flag for collision */
    public static boolean isBulletImpact = true;
    /** Constructor for PionerBullet class
     * @param handler set a handler for this class
     * @param x set x coordinate
     * @param y set y coordinate
     * @param direction set bullet direction */
    public SpenserBullet(Handler handler, double x, double y, byte direction){ super(handler,x,y,direction); }
    /** this method check bullet collision with Pioner */
    @Override
    protected void checkPlayerCollision(){
        if((xBullet > Pioner.pX - 5 && xBullet < Pioner.pX + 45) && (yBullet > Pioner.pY - 5 && yBullet < Pioner.pY + 45)){
            isBulletImpact = true;
            Spenser.bulletController.removeBullet(this);
            Spenser.points += 1000;
            Pioner.health--;
            if(Game.sounds) bulletImpactWithTank.play(5f);
            if(Pioner.health <= 0) winner = 2;
        }
    }
    /** this method check bullet collision with entity */
    @Override
    public void checkEntityCollision(){
        int tx = (int) xBullet / Tile.TILE_WIDTH;
        int ty = (int) yBullet / Tile.TILE_HEIGHT;
        if(tx > 0 && ty > 0) {
            if(!bulletRockCollision(tx - 1, ty - 1) && bulletWaterCollision(tx -1, ty - 1)) {
                isBulletImpact = true;
                Spenser.bulletController.removeBullet(this);
                if(handler.getWorld().tiles[tx-1][ty-1] == 1){
                    Spenser.points += 100;
                    handler.getWorld().tiles[tx-1][ty-1] = 0;
                    if(Game.sounds) bulletImpactWithBrick.play(0f);
                } else if(Game.sounds) bulletImpactWithRock.play(0f);
            }
        }
    }
    /** refreshing frames for bullet for animation and checking all collision on this class */
    public void tick(){
        bulletFrames++;
        if(direction == 1){
            if(yBullet <= 33){
                isBulletImpact = true;
                Spenser.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                yBullet -= 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 2){
            if(yBullet >= 673){
                isBulletImpact = true;
                Spenser.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                yBullet += 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 4){
            if(xBullet >= 1320){
                isBulletImpact = true;
                Spenser.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                xBullet += 10;
                isBulletImpact = false;
            }
        } else {
            if(xBullet <= 33){
                isBulletImpact = true;
                Spenser.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                xBullet -= 10;
                isBulletImpact = false;
            }
        }
        checkPlayerCollision();
        checkEntityCollision();
    }
    /** rendering and animate bullet
     * @param g Graphics element inherited from JFrame */
    public void render(Graphics g){
        if(direction == 1){
            if((bulletFrames % 4 ) == 0) g.drawImage(Assets.firstBulletUp,(int) xBullet,(int) yBullet,null);
            else g.drawImage(Assets.secondBulletUp,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
        else if(direction == 2){
            if((bulletFrames % 4 ) == 0) g.drawImage(Assets.firstBulletDown,(int) xBullet,(int) yBullet,null);
            else g.drawImage(Assets.secondBulletDown,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
        else if(direction == 4){
            if((bulletFrames % 4 ) == 0) g.drawImage(Assets.firstBulletRight,(int) xBullet,(int) yBullet,null);
            else g.drawImage(Assets.secondBulletRight,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        } else {
            if((bulletFrames % 4 ) == 0) g.drawImage(Assets.firstBulletLeft,(int) xBullet,(int) yBullet,null);
            else g.drawImage(Assets.secondBulletLeft,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
    }
}
