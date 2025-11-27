package yzr.hud;

import java.awt.*;

import yzr.tilemapping.GamePanel;
import yzr.tilemapping.KeyHandler;

public class PauseScreen {

    int x,y,height,width;
    int rectX,rectY,rectH,rectW;
    public int paused;
    KeyHandler keyH;
    String imgSrc = "src/main/resources/Cooldown overlay.png";
    Image img = Toolkit.getDefaultToolkit().getImage(imgSrc);


    //button(h,w,loc,selected?,action)
    //esc pauses and restarts
    public PauseScreen( int x, int y, int width, int height,int tileSize, KeyHandler keyH){
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.keyH = keyH;
        paused = -1;


        rectX = width / 6;
        rectY = height / 8;
        rectH = height - (height / 4);
        rectW = width - (width/ 3);

        img = Toolkit.getDefaultToolkit().getImage(imgSrc);

    }

    public int update(int selected){
        paused = keyH.escTyped;

        if (paused == 1){
            if (keyH.upPressed == true){
                if (selected > 1){
                    selected --;
                }
            }else if (keyH.downPressed == true){
                if(selected < 2){
                    selected ++;
                }
            }
        }

        return selected;
    }

    public void draw(Graphics2D g){
        g.drawImage(img, x, y, width, height,null,null);
        g.setColor(Color.GREEN);
        if (paused == 1){
            g.fillRect(rectX,rectY,rectW,rectH);
        }

    }



}
