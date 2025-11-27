package yzr.hud;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hud {
    int xLocation;
    int yLocation;
    int size;

    public void drawOverlay(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(xLocation, yLocation, size, size);
    }

    public void drawDefault(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(xLocation, yLocation, size, size);
    }
}
