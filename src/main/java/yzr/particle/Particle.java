package yzr.particle;

import java.awt.*;

public class Particle {

    int size;
    int xLoc;
    int yLoc;
    Image imgSrc;

    public Particle(int size, int xLoc, int yLoc, String imgSrc) {
        this.size = size;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.imgSrc = Toolkit.getDefaultToolkit().getImage(imgSrc);
    }

    public void drawParticle(Graphics2D g){
        g.drawImage(this.imgSrc, xLoc, yLoc, size, size, null, null);
    }
}
