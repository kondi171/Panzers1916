package dev.panzers1916.graphics;

import java.awt.*;

public abstract class Text {
    public static void drawString(Graphics g,String text, int xPos, int yPos, boolean center, Color color, Font font){
        int x = xPos;
        int y = yPos;
        g.setColor(color);
        g.setFont(font);
        if(center == true){
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y =(yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text,x,y);
    }
}
