package dev.panzers1916.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Represents standard graphics elements
 * @author Konrad Nowak */

public abstract class Assets {
    /** final variables of width and height */
    private static final int width = 50, height = 50;
    /** set a variables for storing default colors */
    public static Color mainColor, pionerColor, spenserColor;
    /** set a variables for storing default fonts */
    public static Font mainFont84, mainFont56, mainFont24, secondFont56;
    /** set a variables for storing textures of obstacles */
    public static BufferedImage brick, rock, water,empty;
    /** set a variables for storing first Pioner frame on every directions */
    public static BufferedImage firstFramePionerRight, firstFramePionerLeft, firstFramePionerUp, firstFramePionerDown;
    /** set a variables for storing second pioner frame on every directions */
    public static BufferedImage secondFramePionerRight, secondFramePionerLeft, secondFramePionerUp, secondFramePionerDown;
    /** set a variables for storing first Spenser frame on every directions */
    public static BufferedImage firstFrameSpenserRight, firstFrameSpenserLeft, firstFrameSpenserUp, firstFrameSpenserDown;
    /** set a variables for storing second Spenser frame on every directions */
    public static BufferedImage secondFrameSpenserRight, secondFrameSpenserLeft, secondFrameSpenserUp, secondFrameSpenserDown;
    /** set a variables for storing first bullet frame on every directions */
    public static BufferedImage firstBulletRight, firstBulletLeft, firstBulletUp,firstBulletDown;
    /** set a variables for storing second bullet frame on every directions */
    public static BufferedImage secondBulletRight, secondBulletLeft, secondBulletUp,secondBulletDown;
    /** method initialize:
     * <ol>
     *     <li>colors</li>
     *     <li>fonts</li>
     *     <li>textures</li>
     * </ol> */
    public static void init(){
        mainColor = new Color(14, 121, 0);
        pionerColor = new Color(27, 96, 211);
        spenserColor = new Color(246, 40, 32);

        mainFont24 = FontLoader.loadFont("res/fonts/8-bit-limit.r-brk.ttf",24);
        mainFont56 = FontLoader.loadFont("res/fonts/8-bit-limit.r-brk.ttf",56);
        mainFont84 = FontLoader.loadFont("res/fonts/8-bit-limit.r-brk.ttf",84);
        secondFont56 = FontLoader.loadFont("res/fonts/6809-chargen.regular.ttf",56);
        SpriteSheet obstacles = new SpriteSheet(ImageLoader.loadImage("/textures/obstacles.png"));
        SpriteSheet playerOne = new SpriteSheet(ImageLoader.loadImage("/textures/pioner_sprite_sheet.png"));
        SpriteSheet playerTwo = new SpriteSheet(ImageLoader.loadImage("/textures/spenser_sprite_sheet.png"));
        SpriteSheet bullet = new SpriteSheet(ImageLoader.loadImage("/textures/bullet.png"));

        firstFramePionerRight = playerOne.crop(0,0,width,height);
        firstFramePionerLeft = playerOne.crop(0,height,width,height);
        firstFramePionerUp = playerOne.crop(0,height * 2,width,height);
        firstFramePionerDown = playerOne.crop(0,height * 3,width,height);

        secondFramePionerRight = playerOne.crop(width,0, width, height);
        secondFramePionerLeft = playerOne.crop(width,height, width, height);
        secondFramePionerUp = playerOne.crop(width,height * 2, width, height);
        secondFramePionerDown = playerOne.crop(width,height * 3, width, height);

        firstFrameSpenserRight = playerTwo.crop(0,0,width,height);
        firstFrameSpenserLeft = playerTwo.crop(0,height,width,height);
        firstFrameSpenserUp = playerTwo.crop(0,height * 2,width,height);
        firstFrameSpenserDown = playerTwo.crop(0,height * 3,width,height);

        secondFrameSpenserRight = playerTwo.crop(width,0, width, height);
        secondFrameSpenserLeft = playerTwo.crop(width,height, width, height);
        secondFrameSpenserUp = playerTwo.crop(width,height * 2, width, height);
        secondFrameSpenserDown = playerTwo.crop(width,height * 3, width, height);

        firstBulletRight = bullet.crop(0,0,10,10);
        firstBulletLeft = bullet.crop(0,10,10,10);
        firstBulletUp = bullet.crop(0,20,10,10);
        firstBulletDown = bullet.crop(0,30,10,10);

        secondBulletRight = bullet.crop(10,0,10,10);
        secondBulletLeft = bullet.crop(10,10,10,10);
        secondBulletUp = bullet.crop(10,20,10,10);
        secondBulletDown = bullet.crop(10,30,10,10);

        empty = obstacles.crop(0,0,width,height);
        brick = obstacles.crop(width,0, width, height);
        rock = obstacles.crop(width * 2,0, width, height);
        water = obstacles.crop(width * 3,0, width, height);
    }
}
