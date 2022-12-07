package dev.panzers1916.entities;

import dev.panzers1916.Handler;

import java.awt.*;

/** Represents a Entity class
 * @author Konrad Nowak */

public abstract class Entity {
    /** variable for storing own handler */
    protected Handler handler;
    /** coordinates x and y position */
    protected double x,y;
    /** declaration of width and height entity */
    protected int width, height;
    /** enduracnce of entity, if is false, remove entity */
    protected boolean active = true;
    /** borders of collision */
    protected Rectangle bounds;

    /** Constructor set starting values
     * @param handler set own handler
     * @param x set x coordinate
     * @param y set y coordinate
     * @param width set width of entity
     * @param height set height of entity */
    public Entity(Handler handler, double x,double y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0,0,width,height);
    }

    /** abstract method using in subclass player
     * @see dev.panzers1916.entities.players.Player */
    public abstract void tick();
    /** abstract method using in subclass player
     * @param g Graphics element inherited from JFrame
     * @see dev.panzers1916.entities.players.Player */
    public abstract void render(Graphics g);
    /** getter for x
     * @return x coordinate */
    public double getX() {
        return x;
    }
    /** setter for x
     * @param x set x */
    public void setX(float x) {
        this.x = x;
    }
    /** getter for y
     * @return y coordinate */
    public double getY() {
        return y;
    }
    /** setter for y
     * @param y set y coordinate */
    public void setY(float y) {
        this.y = y;
    }
    /** getter for width
     * @return width */
    public int getWidth() {
        return width;
    }
    /** setter for width
     * @param width set width */
    public void setWidth(int width) {
        this.width = width;
    }
    /** getter for height
     * @return height */
    public int getHeight() {
        return height;
    }
    /** setter for height
     * @param height set height */
    public void setHeight(int height) {
        this.height = height;
    }
    /** getter for active
     * @return always true in our project */
    public boolean isActive() { return active; }
}
