package dev.panzers1916.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/** Represents a ImageLoader class
 * @author Konrad Nowak*/

public abstract class ImageLoader {
	/** method load images to our project
	 * @param path filepath image
	 * @return always null */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
