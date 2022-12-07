package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

/** Represents a EmptyTile class
 * @author Konrad Nowak */

public class EmptyTile extends Tile {
    /** Constructor set the textures and id
     * @param id set the id */
    public EmptyTile(int id){
        super(Assets.empty,id);
    }
    /** check if solid
     * @return false for collision detection */
    @Override
    public boolean isSolid(){
        return false;
    }
    /** check if hovered
     * @return false for collision detection */
    @Override
    public boolean isHovered(){ return false; }
    /** check if static
     * @return false for collision detection */
    @Override
    public boolean isStatic(){ return false;}
}
