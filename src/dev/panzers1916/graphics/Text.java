package dev.panzers1916.graphics;

import java.awt.*;

/** Represents a text loader class
 * @author Konrad Nowak */

public abstract class Text {
    /** method draw a String on frame
     * @param g Graphics element inherited from JFrame
     * @param text content of String
     * @param xPos x position
     * @param yPos y position
     * @param center text center
     * @param color text color
     * @param font text font */
    public static void drawString(Graphics g,String text, int xPos, int yPos, boolean center, Color color, Font font){
        int x = xPos;
        int y = yPos;
        g.setColor(color);
        g.setFont(font);
        if(center){
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y =(yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text,x,y);
    }
}
