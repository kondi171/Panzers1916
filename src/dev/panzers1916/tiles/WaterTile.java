package dev.panzers1916.tiles;

import dev.panzers1916.graphics.Assets;

/** Represents a Base abstract player class
 * @author Konrad Nowak */

public class WaterTile extends Tile {
    /** Constructor set the textures and id
     * @param id set the id */
    public WaterTile(int id){
        super(Assets.water,id);
    }
    /** check if solid
     * @return true for collision detection */
    @Override
    public boolean isSolid() { return true; }
    /** check if hovered
     * @return true for collision detection */
    @Override
    public boolean isHovered(){ return true; }
    /** check if static
     * @return true for collision detection */
    @Override
    public boolean isStatic(){ return true;}
}
