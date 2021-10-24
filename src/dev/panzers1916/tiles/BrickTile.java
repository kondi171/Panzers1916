package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

public class BrickTile extends Tile{

    public BrickTile(int id){
        super(Assets.brick, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
    @Override
    public boolean isHovered(){ return false; }
    @Override
    public boolean isStatic(){ return false;}
    @Override

    public void isInvisible(boolean statement) {
        if(statement) new Tile(Assets.empty, 1);
    }
}
