package dev.panzers1916.bullet;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.tiles.Tile;

import java.awt.*;

public class SpenserBullet extends Bullet {

    private double xBullet;
    private double yBullet;
    private byte direction;
    private Handler handler;
    public static byte winner = 0;
    public static boolean isBulletImpact;
    public SpenserBullet(Handler handler, double x, double y, byte direction){
        super(handler,x,y,direction);
        this.handler = handler;
        this.xBullet = x;
        this.yBullet = y;
        this.direction = direction;

        int tx = (int) (xBullet + 16)/ Tile.TILE_WIDTH - 1;
        int ty = (int) yBullet / Tile.TILE_HEIGHT - 1;
    }

    @Override
    protected void checkPlayerCollision(){
        if((xBullet > Pioner.pX - 5 && xBullet < Pioner.pX + 45) && (yBullet > Pioner.pY - 5 && yBullet < Pioner.pY + 45)){
            Spenser.points += 1000;
            Pioner.health--;
            isBulletImpact = true;
            Spenser.c.removeBullet(this);
            bulletImpactWithTank.play();
            if(Pioner.health <= 0) winner = 2;
        }
    }
    private byte resetBulletFrames(byte bulletFrames){
        if(bulletFrames >= 100) bulletFrames = 0;
        return bulletFrames;
    }


    public void tick(){
        checkPlayerCollision();
        bulletFrames++;
        if(direction == 1){
            if(yBullet <= 33){
                isBulletImpact = true;
                Spenser.c.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                yBullet -= 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 2){
            if(yBullet >= 673){
                isBulletImpact = true;
                Spenser.c.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                yBullet += 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 4){
            if(xBullet >= 1320){
                isBulletImpact = true;
                Spenser.c.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                xBullet += 10;
                isBulletImpact = false;
            }
        }
        else{
            if(xBullet <= 33){
                isBulletImpact = true;
                Spenser.c.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                xBullet -= 10;
                isBulletImpact = false;
            }
        }
    }

    public void render(Graphics g){
        if(direction == 1){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletUp,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletUp,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        }
        else if(direction == 2){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletDown,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletDown,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        }
        else if(direction == 4){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletRight,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletRight,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        }
        else{
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletLeft,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletLeft,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        }
    }
}
