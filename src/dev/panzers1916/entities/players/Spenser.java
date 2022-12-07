package dev.panzers1916.entities.players;

import dev.panzers1916.Game;
import dev.panzers1916.Handler;
import dev.panzers1916.bullet.Controller;
import dev.panzers1916.bullet.SpenserBullet;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

/** Represents a Pioner player
 * @author Konrad Nowak */

public class Spenser extends Player {
    /** declaration of Spenser direction */
    private byte direction;
    /** variable for storing instance of controller for Spenser bullet */
    public static Controller bulletController;
    /** static variable for storing Spenser health */
    public static byte health;
    /** static variable for storing Spenser points */
    public static int points;
    /** static variables for storing coordinates for Spenser position */
    public static float sX, sY;
    /** a flag which we can decides, if Spenser can shoot*/
    private boolean isTankCanShoot = true;
    /** Constructor which set starting values
     * @param handler set handler
     * @param x set x coordinate
     * @param y set y coordinate */
    public Spenser(Handler handler, float x, float y){
        super(handler,x ,y ,Player.DEFAULT_PLAYER_WIDTH, Player.DEFAULT_PLAYER_HEIGHT);
        this.handler = handler;
        health = 3;
        points = 0;
        bulletController = new Controller();
    }
    /** overriding from Player class,
     *  <br> check attack for this player,
     *  <br> sending local coordinates to static coordinates,
     *  <br> refreshing move and keyListener */
    @Override
    public void tick(){
        bulletController.tick();
        getInput();
        move();
        checkAttack();
        sX = (float) x;
        sY = (float) y;
    }
    /** check statement if we can shoot or not */
    public void checkAttack(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_K)) {
            if(isTankCanShoot) {
                SpenserBullet bullet = new SpenserBullet(handler, x + 20, y + 19, direction);
                bulletController.addBullet(bullet);
                isTankCanShoot = false;
                if(Game.sounds) bulletShoot.play(-12f);
            }
        }
        if(SpenserBullet.isBulletImpact) isTankCanShoot = true;
    }
    /** key listener for Pioner */
    private void getInput(){
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().upSpenser){
            yMove = -speed;
            panzerFrames++;
            direction = 1;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().downSpenser){
            yMove = speed;
            panzerFrames++;
            direction = 2;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().leftSpenser){
            xMove = -speed;
            panzerFrames++;
            direction = 3;
            if(panzerFrames % 7 == 0 && Game.sounds) tankDrive.play(-5f);
        }
        else if(handler.getKeyManager().rightSpenser){
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
        if(direction == 0) g.drawImage(Assets.firstFrameSpenserLeft,1233,83,width,height,null);
        if(direction == 1){
            if((panzerFrames % 2 ) == 0) g.drawImage(Assets.firstFrameSpenserUp,(int) x,(int) y,width,height,null);
            else g.drawImage(Assets.secondFrameSpenserUp,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 2){
            if((panzerFrames % 2 ) == 0) g.drawImage(Assets.firstFrameSpenserDown,(int) x,(int) y,width,height,null);
            else g.drawImage(Assets.secondFrameSpenserDown,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 3){
            if((panzerFrames % 2 ) == 0) g.drawImage(Assets.firstFrameSpenserLeft,(int) x,(int) y,width,height,null);
            else g.drawImage(Assets.secondFrameSpenserLeft,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        if(direction == 4){
            if((panzerFrames % 2 ) == 0) g.drawImage(Assets.firstFrameSpenserRight,(int) x,(int) y,width,height,null);
            else g.drawImage(Assets.secondFrameSpenserRight,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames();
        }
        Text.drawString(g,"Spenser", 1200, 718,true,Assets.spenserColor,Assets.mainFont24);
        Text.drawString(g,"Life x" + health, 1050, 718,true,Assets.spenserColor,Assets.mainFont24);
        Text.drawString(g,"Points " + points, 900, 718,true,Assets.spenserColor,Assets.mainFont24);
        bulletController.render(g);
    }
}
