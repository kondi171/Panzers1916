package dev.panzers1916.entities;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/** Represents a EntityMenager class
 * @author Konrad Nowak */

public class EntityManager {
    /** storing handler */
    private Handler handler;
    /** storing Pioner object */
    private Pioner pioner;
    /** storing Spenser object */
    private Spenser spenser;
    /** declaiyring a new ArrayList of entity objects */
    private ArrayList<Entity> entities;
    /** a comparator for comparing Entity objects
     * @param a object a
     * @param b object b
     * @return negative or positive int */
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    /** Constructors for setting start values and add players for entity manager
     * @param handler set new handler
     * @param pioner set pioner position
     * @param spenser set spenser position */
    public EntityManager(Handler handler, Pioner pioner, Spenser spenser){
        this.handler = handler;
        this.pioner = pioner;
        this.spenser = spenser;
        entities = new ArrayList<Entity>();
        addEntity(pioner);
        addEntity(spenser);
    }
    /** refreshing a new added entity */
    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);
    }
    /** rendering new entity on map
     * @param g Graphics element inherited from JFrame */
    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    /** add entity on map
     * @param e entity object */
    public void addEntity(Entity e){
        entities.add(e);
    }

    /** getter for handler
     * @return handler */
    public Handler getHandler() {
        return handler;
    }

    /** setter for handler
     * @param handler set handler */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    /** getter for Pioner
     * @return Pioner object */
    public Pioner getPioner() {
        return pioner;
    }
    /** getter for Spenser
     * @return Spenser object */
    public Spenser getSpenser() {
        return spenser;
    }
}
