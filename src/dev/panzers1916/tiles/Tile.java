package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[338];
    public static Tile emptyTile = new EmptyTile(0);
    public static Tile brickTile = new BrickTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile waterTile = new WaterTile(3);
    public static final int TILE_WIDTH = 50,
                            TILE_HEIGHT = 50;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }

    public boolean isSolid(){ return false; }
    public boolean isHovered(){ return false; }
    public boolean isStatic(){ return false;}
    public void isInvisible(boolean statement) {
        if(statement) new Tile(Assets.empty, 0);
    }
    public int getId(){
        return id;
    }
}
