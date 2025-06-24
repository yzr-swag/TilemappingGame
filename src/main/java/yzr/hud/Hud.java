package yzr.hud;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hud {
    int xLocation;
    int yLocation;
    int xSize;
    int ySize;

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(xLocation, yLocation, xSize, ySize);
    }
}
