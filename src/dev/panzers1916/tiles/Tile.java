package dev.panzers1916.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Represents a abstract Tile class
 * @author Konrad Nowak*/

public abstract class Tile {
    /** declaring array a 4 different type of tiles */
    public static Tile[] tiles = new Tile[4];
    /** set the id equals 0 for <b>emptyTile</b> */
    public static Tile emptyTile = new EmptyTile(0);
    /** set the id equals 1 for <b>brickTile</b> */
    public static Tile brickTile = new BrickTile(1);
    /** set the id equals 2 for <b>rockTile</b> */
    public static Tile rockTile = new RockTile(2);
    /** set the id equals 3 for <b>waterTile</b> */
    public static Tile waterTile = new WaterTile(3);
    /** set final variables for width and height of tile */
    public static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
    /** storing a texture */
    protected BufferedImage texture;
    /** storing id for tile */
    protected final int id;

    /** Constructor set the texture id and tiles[id] for this class inherited from subclass
     * @param texture set the texture
     * @param id set the id */
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    /** rendering a tiles
     * @param g Graphics element inherited from JFrame
     * @param x set x coordinate
     * @param y set y coordinate */
    public void render(Graphics g, int x, int y){ g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null); }

    /** abstract method to overridden in subclass
     * @return boolean*/
    public abstract boolean isSolid();
    /** abstract method to overridden in subclass
     * @return boolean*/
    public abstract boolean isHovered();
    /** abstract method to overridden in subclass
     * @return boolean*/
    public abstract boolean isStatic();
}
