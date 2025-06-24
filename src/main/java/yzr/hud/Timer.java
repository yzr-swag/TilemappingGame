package yzr.hud;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


public class Timer extends Hud{
    int height;
    int width;
    int x;
    int y;
    int defaultCooldown;
    int defaultHeight;
    Color colour;
    Image imgSrc;
    Image overlay = Toolkit.getDefaultToolkit().getImage("src/main/resources/Cooldown overlay.png");
    ImageObserver observer;

    public Timer(int x, int y, int width, int defaultHeight, int defaultCooldown, Color colour, String imgSrc){
    this.x = x;
    this.y = y;
    this.defaultHeight = defaultHeight;
    this.width = width;
    this.colour = colour;
    this.imgSrc = Toolkit.getDefaultToolkit().getImage(imgSrc);
    height = defaultCooldown;
    }

    public void update(int cooldown){
        height = cooldown;
    }

    @Override
    public void drawOverlay(Graphics2D g) {
        g.drawImage(overlay, x, y, width,  height, colour, observer);
    }


    @Override
    public void drawDefault(Graphics2D g){ 
        g.drawImage(imgSrc, x, y, width, defaultHeight, colour, observer);
    }

    
}
