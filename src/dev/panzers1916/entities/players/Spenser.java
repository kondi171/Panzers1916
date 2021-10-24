package dev.panzers1916.entities.players;

import dev.panzers1916.Handler;
import dev.panzers1916.bullet.Controller;
import dev.panzers1916.bullet.SpenserBullet;
import dev.panzers1916.graphics.Assets;
import dev.panzers1916.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Spenser extends Player {
    private Handler handler;
    private byte direction;
    private int panzerFrames;
    public static Controller c;
    private Pioner pioner;
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
    private SpenserBullet bullet;
    public static byte health;
    public static int points;
    public static float sX, sY;
    private boolean isTankCanShoot = true;

    public Spenser(Handler handler, float x, float y){
        super(handler,x ,y ,Player.DEFAULT_PLAYER_WIDTH, Player.DEFAULT_PLAYER_HEIGHT);
        this.handler = handler;
        this.health = 3;
        this.points = 0;
        this.c = new Controller(handler);
    }

    private int resetPanzerFrames(int panzerFrames){
        if(panzerFrames >= 100) panzerFrames = 0;
        return panzerFrames;
    }

    @Override
    public void tick(){
        c.tick();
        getInput();
        move();
        checkAttack();
        sX = (float) x;
        sY = (float) y;
        //System.out.println(sX);
    }
    public void checkAttack(){
        bullet = new SpenserBullet(handler, x + 20, y + 19, direction);
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            if(isTankCanShoot) {
                c.addBullet(bullet);
                isTankCanShoot = false;
                bulletShoot.play();
            }
        }
        if(bullet.isBulletImpact) isTankCanShoot = true;
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().upSpenser){
            yMove = -speed;
            panzerFrames++;
            direction = 1;
        }
        else if(handler.getKeyManager().downSpenser){
            yMove = speed;
            panzerFrames++;
            direction = 2;
        }
        else if(handler.getKeyManager().leftSpenser){
            xMove = -speed;
            panzerFrames++;
            direction = 3;
        }
        else if(handler.getKeyManager().rightSpenser){
            xMove = speed;
            panzerFrames++;
            direction = 4;
        }
    }
    @Override
    public void render(Graphics g){
        if(direction == 0){
            g.drawImage(Assets.firstFrameSpenserLeft,1233,83,width,height,null);
        }
        if(direction == 1){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFrameSpenserUp,(int) x,(int) y,width,height,null);
            else
                g.drawImage(Assets.secondFrameSpenserUp,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames(panzerFrames);
        }
        if(direction == 2){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFrameSpenserDown,(int) x,(int) y,width,height,null);
            else
                g.drawImage(Assets.secondFrameSpenserDown,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames(panzerFrames);
        }
        if(direction == 3){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFrameSpenserLeft,(int) x,(int) y,width,height,null);
            else
                g.drawImage(Assets.secondFrameSpenserLeft,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames(panzerFrames);
        }
        if(direction == 4){
            if((panzerFrames % 2 ) == 0)
                g.drawImage(Assets.firstFrameSpenserRight,(int) x,(int) y,width,height,null);
            else
                g.drawImage(Assets.secondFrameSpenserRight,(int) x,(int) y,width,height,null);
            panzerFrames = resetPanzerFrames(panzerFrames);
        }
        c.render(g);
        Text.drawString(g,"Spenser", 1200, 718,true,Assets.spenserColor,Assets.mainFont24);
        Text.drawString(g,"Life x" + health, 1050, 718,true,Assets.spenserColor,Assets.mainFont24);
        Text.drawString(g,"Points " + points, 900, 718,true,Assets.spenserColor,Assets.mainFont24);
    }
}
