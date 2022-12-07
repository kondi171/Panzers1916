package dev.panzers1916.graphics;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Represents FontLoader class
 * @author Konrad Nowak */

public abstract class FontLoader {
    /** method load font to our project
     * @param path filepath font
     * @param size a size of font
     * @return always null */
    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}

