package dev.panzers1916.entities.players;

import dev.panzers1916.Game;
import dev.panzers1916.Handler;
import dev.panzers1916.bullet.Controller;
import dev.panzers1916.bullet.PionerBullet;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

/** Represents a Pioner player
 * @author Konrad Nowak */

public class Pioner extends Player {
    /** declaration of Pioner direction */
    private byte direction;
    /** variable for storing instance of controller for Pioner bullet */
    public static Controller bulletController;
    /** variavle for storing instance of PionerBullet */
    private PionerBullet bullet;
    /** static variable for storing Pioner health */
    public static byte health;
    /** static variable for storing Pioner points */
    public static int points;
    /** static variables for storing coordinates for Pioner position */
    public static float pX, pY;
    /** a flag which we can decides, if Pioner can shoot*/
    private boolean isTankCanShoot;
    /** Constructor which set starting values
     * @param handler set handler
     * @param x set x coordinate
     * @param y set y coordinate */
    public Pioner(Handler handler, float x, float y){
        super(handler,x ,y ,Player.DEFAULT_PLAYER_WIDTH,Player.DEFAULT_PLAYER_HEIGHT);
        this.handler = handler;
        this.bulletController = new Controller();
        health = 3;
        points = 0;
        isTankCanShoot = true;
    }
    /** overriding from Player class,
     *  <br> check attack for this player,
     *  <br> sending local coordinates to static coordinates,
     *  <br> refreshing move and keyListener */
    @Override
    public void tick(){
        getInput();
        move();
        bulletController.tick();
        checkAttack();
        pX = (float) x;
        pY = (float) y;
    }
    /** check statement if we can shoot or not */
    public void checkAttack(){
        bullet = new PionerBullet(handler, x + 20, y + 19, direction);
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            if(isTankCanShoot) {
                bulletController.addBullet(bullet);
                isTankCanShoot = false;
                if(Game.sounds) bulletShoot.play(-12f);
            }
        }
        if(bullet.isBulletImpact) isTankCanShoot = true;
    }
    /** key listener for Pioner */
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().upPioner){
            yMove = -speed;
            panzerFrames++;
            direction = 1;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().downPioner){
            yMove = speed;
            panzerFrames++;
            direction = 2;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().leftPioner){
            xMove = -speed;
            panzerFrames++;
            direction = 3;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().rightPioner){
            xMove = speed;
            panzerFrames++;
            direction = 4;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
    }

    /** rendering and animate graphics and stats
     * @param g Graphics element inherited from JFrame */
    @Override
    public void render(Graphics g){
        if(direction == 0){
            g.drawImage(Assets.firstFramePionerRight,88,583,width,height,null);
        }
        if(direction == 1){
           if((panzerFrames % 2 ) == 0)
               g.drawImage(Assets.firstFramePionerUp,(int) x,(int) y,width,height,null);
            else
               g.drawImage(Assets.secondFramePionerUp,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 2){
           if((panzerFrames % 2 ) == 0)
               g.drawImage(Assets.firstFramePionerDown,(int) x,(int) y,width,height,null);
            else
               g.drawImage(Assets.secondFramePionerDown,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 3){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFramePionerLeft, (int) x, (int) y, width, height, null);
            else
                g.drawImage(Assets.secondFramePionerLeft, (int) x, (int) y, width, height, null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 4){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFramePionerRight, (int) x, (int) y, width, height, null);
            else
                g.drawImage(Assets.secondFramePionerRight, (int) x, (int) y, width, height, null);
            panzerFrames = resetPanzerFrames();
        }
        Text.drawString(g,"Pioner", 150, 718,true,Assets.pionerColor,Assets.mainFont24);
        Text.drawString(g,"Life x" + health, 300, 718,true,Assets.pionerColor,Assets.mainFont24);
        Text.drawString(g,"Points " + points, 450, 718,true,Assets.pionerColor,Assets.mainFont24);
        bulletController.render(g);
    }
}
