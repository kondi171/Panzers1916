package dev.panzers1916.entities;

import dev.panzers1916.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected double x,y;
    protected int width, height;
    protected boolean active = true;
    protected Rectangle bounds;
    protected byte health, points;

    public Entity(Handler handler, double x,double y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = 3;
        points = 0;
        bounds = new Rectangle(0,0,width,height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public double getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() { return active; }
}
