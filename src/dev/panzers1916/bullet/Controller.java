package dev.panzers1916.bullet;

import java.awt.*;
import java.util.LinkedList;

/** Represents a controller of bullet object
 * @author Konrad Nowak */

public class Controller {
    /** a new linked list for bullet */
    private LinkedList<Bullet> bullet = new LinkedList<>();
    /** temporary variable for storing bullet */
    private Bullet tempBullet;
    /** refreshing a bullet */
    public void tick(){
        for(Bullet bullet : bullet) {
            tempBullet = bullet;
            tempBullet.tick();
        }
    }
    /** rendering new bullet
     * @param g Graphics element inherited from JFrame*/
    public void render(Graphics g){
        for(Bullet bullet : bullet) {
            tempBullet = bullet;
            tempBullet.render(g);
        }
    }
    /** add bullet to frame
     * @param block send a bullet object
     * @see dev.panzers1916.entities.players.Pioner in checkAttack
     * @see dev.panzers1916.entities.players.Spenser in checkAttack */
    public void addBullet(Bullet block){ bullet.add(block); }
    /** remove bullet from frame
     * @param block send a bullet object
     * @see dev.panzers1916.entities.players.Pioner in checkAttack
     * @see dev.panzers1916.entities.players.Spenser in checkAttack */
    public void removeBullet(Bullet block){ bullet.remove(block); }
}
