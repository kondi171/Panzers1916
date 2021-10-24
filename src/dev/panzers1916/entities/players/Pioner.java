package dev.panzers1916.entities.players;

import dev.panzers1916.Handler;
import dev.panzers1916.bullet.Controller;
import dev.panzers1916.bullet.PionerBullet;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pioner extends Player {

    private byte direction, panzerFrames;
    public static Controller bulletController;
    public static byte health;
    public static int points;
    public static float pX, pY;
    private boolean isTankCanShoot;

    public Pioner(Handler handler, float x, float y){
        super(handler,x ,y ,Player.DEFAULT_PLAYER_WIDTH,Player.DEFAULT_PLAYER_HEIGHT);
        this.handler = handler;
        bulletController = new Controller(handler);
        health = 3;
        points = 0;
        isTankCanShoot = true;
    }

    private byte resetPanzerFrames(){
        if(this.panzerFrames >= 100) panzerFrames = 0;
        return panzerFrames;
    }

    @Override
    public void tick(){
        getInput();
        move();
        bulletController.tick();
        checkAttack();
        pX = (float) x;
        pY = (float) y;
    }
    public void checkAttack(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            if(isTankCanShoot) {
                PionerBullet bullet = new PionerBullet(handler, x + 20, y + 19, direction);
                bulletController.addBullet(bullet);
                isTankCanShoot = false;
                bulletShoot.play();
            }
        }
        if(PionerBullet.isBulletImpact) isTankCanShoot = true;
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().upPioner){
            yMove = -speed;
            panzerFrames++;
            direction = 1;
          //  tankDrive.play();
        } else if(handler.getKeyManager().downPioner){
            yMove = speed;
            panzerFrames++;
            direction = 2;
         //   tankDrive.play();
        } else if(handler.getKeyManager().leftPioner){
            xMove = -speed;
            panzerFrames++;
            direction = 3;
          //  tankDrive.play();
        } else if(handler.getKeyManager().rightPioner){
            xMove = speed;
            panzerFrames++;
            direction = 4;
        }
    }

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
            if((panzerFrames % 2 ) == 0){
                g.drawImage(Assets.firstFramePionerRight, (int) x, (int) y, width, height, null);
                }
            else
                g.drawImage(Assets.secondFramePionerRight, (int) x, (int) y, width, height, null);
            panzerFrames = resetPanzerFrames();
        }
        Text.drawString(g,"Pioner", 150, 718,true,Assets.pionerColor,Assets.mainFont24);
        Text.drawString(g,"Life x" + health, 300, 718,true,Assets.pionerColor,Assets.mainFont24);
        Text.drawString(g,"Points " + points, 450, 718,true,Assets.pionerColor,Assets.mainFont24);
        bulletController.render(g);
//        g.setColor(Color.red);
//        g.fillRect((int)x, (int) y,width,height);
    }
}
