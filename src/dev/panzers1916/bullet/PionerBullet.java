package dev.panzers1916.bullet;

import dev.panzers1916.Game;
import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.tiles.Tile;

import java.awt.*;

/** Represents a Pioner bullet
 * @author Konrad Nowak */

public class PionerBullet extends Bullet {
    /** this variable set a winner player, if winner equals 1, then winner is Pioner */
    public static byte winner = 0;
    /** flag for collision */
    public static boolean isBulletImpact = true;
    /** Constructor for PionerBullet class
     * @param handler set a handler for this class
     * @param x set x coordinate
     * @param y set y coordinate
     * @param direction set bullet direction */
    public PionerBullet(Handler handler, double x, double y, byte direction){ super(handler,x,y,direction); }
    /** this method check bullet collision with Spenser */
    @Override
    protected void checkPlayerCollision(){
        if((xBullet > Spenser.sX - 5 && xBullet < Spenser.sX + 45) && (yBullet > Spenser.sY - 5 && yBullet < Spenser.sY + 45)){
            isBulletImpact = true;
            Pioner.bulletController.removeBullet(this);
            Pioner.points += 1000;
            Spenser.health--;
            if(Game.sounds) bulletImpactWithTank.play(5f);
            if(Spenser.health <= 0) winner = 1;
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
                Pioner.bulletController.removeBullet(this);
                if(handler.getWorld().tiles[tx-1][ty-1] == 1){
                    Pioner.points += 100;
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
                Pioner.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                yBullet -= 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 2){
            if(yBullet >= 683){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                yBullet += 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 3){
            if(xBullet <= 33){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                xBullet -= 10;
                isBulletImpact = false;
            }
        } else {
            if(xBullet >= 1320){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                if(Game.sounds) bulletImpactWithWall.play(5f);
            } else {
                xBullet += 10;
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
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletUp,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletUp,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
        else if(direction == 2){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletDown,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletDown,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
        else if(direction == 3){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletLeft,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletLeft,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        } else {
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletRight,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletRight,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames();
        }
    }
}
