package dev.panzers1916.bullet;

import dev.panzers1916.Handler;
import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullet> b = new LinkedList<>();
    Bullet TempBullet;
    Handler handler;

    public Controller(Handler handler){
        this.handler = handler;
    }
    public void tick(){
        for(Bullet bullet : b) {
            TempBullet = bullet;
            TempBullet.tick();
        }
    }

    public void render(Graphics g){
        for(Bullet bullet : b) {
            TempBullet = bullet;
            TempBullet.render(g);
        }
    }

    public void addBullet(Bullet block){
        b.add(block);
    }
    public void removeBullet(Bullet block){
        b.remove(block);
    }
}
