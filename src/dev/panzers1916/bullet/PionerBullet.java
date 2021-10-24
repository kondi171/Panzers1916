package dev.panzers1916.bullet;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.tiles.Tile;

import java.awt.*;

public class PionerBullet extends Bullet {

    private double xBullet;
    private double yBullet;
    private byte direction;
    private Handler handler;
    public static byte winner = 0;//change to boolean
    public static boolean isBulletImpact = true;
    public PionerBullet(Handler handler, double x, double y, byte direction){
        super(handler,x,y,direction);
        this.handler = handler;
        this.xBullet = x;
        this.yBullet = y;
        this.direction = direction;
    }
    private byte resetBulletFrames(byte bulletFrames){
        if(bulletFrames >= 100) bulletFrames = 0;
        return bulletFrames;
    }

    @Override
    protected void checkPlayerCollision(){
        if((xBullet > Spenser.sX - 5 && xBullet < Spenser.sX + 45) && (yBullet > Spenser.sY - 5 && yBullet < Spenser.sY + 45)){
            isBulletImpact = true;
            Pioner.bulletController.removeBullet(this);
            Pioner.points += 1000;
            Spenser.health--;
            bulletImpactWithTank.play();
            if(Spenser.health <= 0) winner = 1;
        } //else isBulletImpact = false;
    }

    public void checkRockCollision(){
        /*int tx = (int) xBullet/ Tile.TILE_WIDTH;
        int ty = (int) yBullet / Tile.TILE_HEIGHT;*/
        System.out.println((int)yBullet/Tile.TILE_WIDTH);
        System.out.println((int)xBullet/Tile.TILE_HEIGHT);
        int tx = (int) xBullet / Tile.TILE_WIDTH;
        int ty = (int) yBullet / Tile.TILE_HEIGHT;
        if(tx > 0 && ty > 0) {
            if (bulletRockCollision(tx - 1, ty - 1) && !bulletWaterCollision(tx, ty)) {
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                bulletImpactWithRock.play();
            }
        }
    }

    public void tick(){
        bulletFrames++;
        if(direction == 1){
            if(yBullet <= 33){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                yBullet -= 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 2){
            if(yBullet >= 683){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                yBullet += 10;
                isBulletImpact = false;
            }
        }
        else if(direction == 3){
            if(xBullet <= 33){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                xBullet -= 10;
                isBulletImpact = false;
            }
        } else {
            if(xBullet >= 1320){
                isBulletImpact = true;
                Pioner.bulletController.removeBullet(this);
                bulletImpactWithWall.play();
            } else {
                xBullet += 10;
                isBulletImpact = false;
            }
        }
        checkPlayerCollision();
        checkRockCollision();
    }

    public void render(Graphics g){
        int tx = (int) xBullet;
        int ty = (int) yBullet;
        g.setColor(Color.red);
      //  g.fillRect(tx-20,ty-19,50,50);
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
        else if(direction == 3){
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletLeft,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletLeft,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        } else {
            if((bulletFrames % 4 ) == 0)
                g.drawImage(Assets.firstBulletRight,(int) xBullet,(int) yBullet,null);
            else
                g.drawImage(Assets.secondBulletRight,(int) xBullet,(int) yBullet,null);
            bulletFrames = resetBulletFrames(bulletFrames);
        }
    }
}
