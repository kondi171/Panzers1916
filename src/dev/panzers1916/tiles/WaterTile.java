package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

public class WaterTile extends Tile {

    public WaterTile(int id){
        super(Assets.water,id);
    }

    @Override
    public boolean isSolid() { return true; }
    @Override
    public boolean isHovered(){ return true; }
    @Override
    public boolean isStatic(){ return true;}
    @Override
    public void isInvisible(boolean statement) {
        if(statement) new Tile(Assets.empty, 3);
    }
}
