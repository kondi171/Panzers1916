package dev.panzers1916.worlds;

import dev.panzers1916.Handler;
import dev.panzers1916.entities.EntityManager;
import dev.panzers1916.entities.players.Pioner;
import dev.panzers1916.entities.players.Spenser;
import dev.panzers1916.tiles.Tile;
import dev.panzers1916.utils.Utils;

import java.awt.*;

/** Represents a World class
 * @author Konrad Nowak */

public class World {
    /** storing width and height variables */
    private int width,height;
    /** storing position for players */
    protected int pionerSpawnX, pionerSpawnY, spenserSpawnX,spenserSpawnY;
    /** declaration two-dimensional array which represent a map with tiles */
    public static int[][] tiles;
    /** declaration of EntityManager object, thanks to this variable we can call this object on this class */
    private EntityManager entityManager;

    /** Contructor set the handler file path and call method <b>loadWorld()</b>
     * @param handler set handler
     * @param path set file path */
    public World(Handler handler,String path) {
        entityManager = new EntityManager(handler,new Pioner(handler,88,583), new Spenser(handler,1233,83));
        loadWorld(path);
    }
    /** refreshing a entity in entityManager */
    public void tick(){
        entityManager.tick();
    }

    /** rendering a tile in x position and y position thanks to method <b>getTile()</b>
     * @param g Graphics element inherited from JFrame*/
    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x,y).render(g,x * Tile.TILE_WIDTH + 33,y * Tile.TILE_HEIGHT + 33);
            }
        }
        entityManager.render(g);
    }

    /** download the tile from coordinates x and y
     * @param x send x coordinate
     * @param y set y coordinate
     * @return tile */
    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.emptyTile;
        else return t;
    }

    /** this method load the world from txt file, which we safe earlier in constructor on parameter filepath
     * @param path file path */
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        pionerSpawnX = Utils.parseInt(tokens[2]);
        pionerSpawnY = Utils.parseInt(tokens[3]);
        spenserSpawnX = Utils.parseInt(tokens[4]);
        spenserSpawnY = Utils.parseInt(tokens[5]);
        tiles = new int[width + 1][height + 1];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 6]);
            }
        }
    }

    /** getter for <b>entityManager</b>
     * @return entityManager */
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
