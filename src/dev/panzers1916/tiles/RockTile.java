package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

public class RockTile extends Tile {
    public RockTile(int id){
        super(Assets.rock,id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    @Override
    public boolean isHovered(){ return false; }
    @Override
    public boolean isStatic(){ return true;}
    @Override
    public void isInvisible(boolean statement) {
        if(statement) new Tile(Assets.empty, 2);
    }
}
