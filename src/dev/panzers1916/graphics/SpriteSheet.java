package dev.panzers1916.graphics;

import java.awt.image.BufferedImage;

/** Represents a SpriteSheet class
 * @author Konrad Nowak */

public class SpriteSheet {
    /** declaring variable storing a spritesheet */
    private BufferedImage sheet;

    /** Constructor set the spritesheet
     * @param sheet set the sheet */
    public SpriteSheet(BufferedImage sheet){ this.sheet = sheet; }

    /** this method crop the image for sub images to get the content of spritesheet
     * @param x send x coordinate
     * @param y send y coordinate
     * @param width send width of sub image
     * @param height send height of sub image
     * @return content of necessary image */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
