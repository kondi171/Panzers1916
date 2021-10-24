package dev.panzers1916.entities;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Pioner pioner;
    private Spenser spenser;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Pioner pioner, Spenser spenser){
        this.handler = handler;
        this.pioner = pioner;
        this.spenser = spenser;
        entities = new ArrayList<Entity>();
        addEntity(pioner);
        addEntity(spenser);
    }

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

    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Pioner getPioner() {
        return pioner;
    }

    public void setPioner(Pioner player) {
        this.pioner = pioner;
    }

    public Spenser getSpenser() {
        return spenser;
    }

    public void setSpenser(Spenser spenser) {
        this.spenser = spenser;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

}
