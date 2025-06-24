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
    Color colour;
    Image imgSrc;
    ImageObserver observer;

    public Timer(int x, int y, int width, int cooldown, Color colour, String imgSrc){
    this.x = x;
    this.y = y;
    this.width = width;
    this.colour = colour;
    this.imgSrc = Toolkit.getDefaultToolkit().getImage(imgSrc);
    height = cooldown;

    }

    public void update(int cooldown){
        height = cooldown;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(imgSrc, x, y, width,  height * -1, colour, observer);
    }

    
}
