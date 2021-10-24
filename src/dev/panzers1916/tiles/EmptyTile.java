package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

public class EmptyTile extends Tile {
    public EmptyTile(int id){
        super(Assets.empty,id);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
    @Override
    public boolean isHovered(){ return false; }
    @Override
    public boolean isStatic(){ return false;}
    @Override
    public void isInvisible(boolean statement) {
        if(statement) new Tile(Assets.empty, 0);
    }
}
