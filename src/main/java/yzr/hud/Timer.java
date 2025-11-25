package yzr.hud;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


public class Timer extends Hud{
    int height;
    int defaultCooldown;
    Image imgSrc;
    Image overlay = Toolkit.getDefaultToolkit().getImage("src/main/resources/Cooldown overlay.png");
    ImageObserver observer;

    public Timer(int x, int y, int size, int defaultCooldown, String imgSrc){
    this.xLocation = x;
    this.yLocation = y;
    this.defaultCooldown = defaultCooldown;
    this.size = size;
    this.imgSrc = Toolkit.getDefaultToolkit().getImage(imgSrc);
    height = defaultCooldown;
    }

    public void update(double cooldown){
        //updates the timer's cooldown bar size

        height = (int)((cooldown / (double)defaultCooldown) * (double)size);
    }

    @Override
    public void drawOverlay(Graphics2D g) {
        //draws the cooldown overlay onto the timer

        if(height > 0){
            g.drawImage(overlay, xLocation, yLocation, size, size, null, observer);
        }
        g.drawImage(overlay, xLocation, yLocation+size, size, height * -1, null, observer);
    }


    @Override
    public void drawDefault(Graphics2D g){
        //draws the background image for the timer

        g.drawImage(imgSrc, xLocation, yLocation, size, size, null, observer);
    }

}
