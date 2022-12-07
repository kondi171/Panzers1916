package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

/** Represents a BrickTile class
 * @author Konrad Nowak */

public class BrickTile extends Tile{
    /** Constructor set the textures and id
     * @param id set the id */
    public BrickTile(int id){
        super(Assets.brick, id);
    }

    /** check if solid
     * @return true for collision detection */
    @Override
    public boolean isSolid() {
        return true;
    }
    /** check if hovered
     * @return false for collision detection */
    @Override
    public boolean isHovered(){ return false; }
    /** check if static
     * @return false for collision detection */
    @Override
    public boolean isStatic(){ return true;}
}
