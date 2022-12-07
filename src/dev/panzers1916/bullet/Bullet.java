package dev.panzers1916.bullet;

import dev.panzers1916.Handler;
import dev.panzers1916.sounds.SoundLoader;

import java.awt.*;
import java.io.File;

/** Represents a bullet
 * @author Konrad Nowak */

public abstract class Bullet {
    /** bullet x position */
    protected double xBullet;
    /** bullet y position */
    protected double yBullet;
    /** bullet direction */
    protected byte direction;
    /** handler to necessary method */
    protected Handler handler;
    /** frames for animation of bullet */
    protected byte bulletFrames;
    /** sounds storage variables */
    protected SoundLoader bulletImpactWithWall, bulletImpactWithTank, bulletImpactWithBrick, bulletImpactWithRock;
    /** set starting values and call initSounds() method
     *  @param handler set new handler
     *  @param x set x coordinate
     *  @param y set y coordinate
     *  @param direction check bullet direction */
    public Bullet(Handler handler, double x, double y, byte direction){
        this.xBullet = x;
        this.yBullet = y;
        this.direction = direction;
        this.handler = handler;
        initSounds();
    }
    /** Abstract method which must be overridden
     * <br> method must check the player collision */
    protected abstract void checkPlayerCollision();
    /** Abstract method which must be overridden
     * <br> method must check the entity collision */
    protected abstract void checkEntityCollision();
    /** this method save a sounds to relevant variables */
    private void initSounds(){
        bulletImpactWithWall = new SoundLoader(new File("res/sounds/impacts/wallImpact.wav"),false);
        bulletImpactWithTank = new SoundLoader(new File("res/sounds/impacts/tankImpact.wav"),false);
        bulletImpactWithBrick = new SoundLoader(new File("res/sounds/impacts/rockImpact.wav"),false);
        bulletImpactWithRock = new SoundLoader(new File("res/sounds/impacts/brickImpact.wav"),false);
    }
    /** this method reset a frames for bullet which we can animate the bullet, if <b>bulletFrames</b> has 100 frames, do reset
     * @return numbers of frames */
    protected byte resetBulletFrames(){
        if(bulletFrames >= 100) bulletFrames = 0;
        return bulletFrames;
    }
    /** this method is abstract and overriding is in subclasses
     * @see PionerBullet
     * @see SpenserBullet */
    public abstract void tick();
    /** this method is abstract and overriding is in subclasses
     * @param g Graphics element inherited from JFrame
     * @see PionerBullet
     * @see SpenserBullet */
    public abstract void render(Graphics g);
    /** a method which check the collision with rockTile
     * @param x set x coordinate (1-26)
     * @param y set y coordinate (1-13)
     * @return true if collision has occurred
     * @see dev.panzers1916.tiles.RockTile */
    protected boolean bulletRockCollision(int x, int y){ return handler.getWorld().getTile(x,y).isHovered(); }
    /** a method which check the collision with waterTile
     * @param x set x coordinate (1-26)
     * @param y set y coordinate (1-13)
     * @return true if collision has occurred
     * @see dev.panzers1916.tiles.WaterTile */
    protected boolean bulletWaterCollision(int x, int y){ return handler.getWorld().getTile(x,y).isStatic(); }
}
