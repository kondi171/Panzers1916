package dev.panzers1916.worlds;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.EntityManager;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.tiles.Tile;
import dev.panzers1916.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width,height;
    protected int pionerSpawnX, pionerSpawnY, spenserSpawnX,spenserSpawnY;
    private int[][] tiles;
    private EntityManager entityManager;

    public World(Handler handler,String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler,new Pioner(handler,88,583), new Spenser(handler,1233,83));
        loadWorld(path);
        entityManager.getPioner().setX(pionerSpawnX);
        entityManager.getPioner().setY(pionerSpawnY);
        entityManager.getSpenser().setX(spenserSpawnX);
        entityManager.getSpenser().setY(spenserSpawnY);
    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x,y).render(g,x * Tile.TILE_WIDTH + 33,y * Tile.TILE_HEIGHT + 33);
            }
        }
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[tiles[x][y]];
        //t == null;
        if(t == null)
            return Tile.emptyTile;
        else return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        pionerSpawnX = Utils.parseInt(tokens[2]);
        pionerSpawnY = Utils.parseInt(tokens[3]);
        spenserSpawnX = Utils.parseInt(tokens[4]);
        spenserSpawnY = Utils.parseInt(tokens[5]);
        tiles = new int[width+1][height+1];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 6]);
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
