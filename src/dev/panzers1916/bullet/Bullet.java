package dev.panzers1916.bullet;

import dev.panzers1916.Handler;
import dev.panzers1916.sounds.SoundLoader;

import java.awt.*;
import java.io.File;

public abstract class Bullet {

    private double xBullet;
    private double yBullet;
    private byte direction;
    private Handler handler;
    public static boolean removeTile = false;
    protected byte bulletFrames;
    protected SoundLoader bulletImpactWithWall, bulletImpactWithTank, bulletImpactWithBrick, bulletImpactWithRock;

    public Bullet(Handler handler, double x, double y, byte direction){
        this.xBullet = x;
        this.yBullet = y;
        this.direction = direction;
        this.handler = handler;
        initSounds();
    }

    protected abstract void checkPlayerCollision();

    private void initSounds(){
        bulletImpactWithWall = new SoundLoader(new File("res/sounds/impacts/wallImpact.wav"),false);
        bulletImpactWithTank = new SoundLoader(new File("res/sounds/impacts/tankImpact.wav"),false);
        bulletImpactWithBrick = new SoundLoader(new File("res/sounds/impacts/rockImpact.wav"),false);
        bulletImpactWithRock = new SoundLoader(new File("res/sounds/impacts/brickImpact.wav"),false);
    }
    private byte resetBulletFrames(byte bulletFrames){
        if(bulletFrames >= 100) bulletFrames = 0;
        return bulletFrames;
    }


    public void tick(){

    }

    public void render(Graphics g){

    }

    protected boolean bulletBrickCollision(int x, int y){
       return handler.getWorld().getTile(x,y).isSolid();
    }
    protected boolean bulletRockCollision(int x, int y){
        return handler.getWorld().getTile(x,y).isStatic();
    }
    protected boolean bulletWaterCollision(int x, int y){
        return handler.getWorld().getTile(x,y).isHovered();
    }

    public double getXBullet() {
        return xBullet;
    }

    public void setXBullet(double xBullet) {
        this.xBullet = xBullet;
    }

    public double getYBullet() {
        return yBullet;
    }

    public void setYBullet(double yBullet) {
        this.yBullet = yBullet;
    }

}
