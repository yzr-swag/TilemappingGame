package yzr.hud;

import yzr.tilemapping.KeyHandler;

import java.awt.*;


public class Button {
    int x,y,height,width,buttonNum, tileSize;
    Boolean selected = false;
    String text;
    KeyHandler keyH;

    public Button(int height,int width,int buttonNum, int tileSize, String text, KeyHandler keyH){
        this.x = (width/2)-(width/6);
        this.y = (tileSize * 4) + height/(6 / buttonNum);
        this.height = (height/8);
        this.width = (width/3);
        this.buttonNum = buttonNum;
        this.tileSize = tileSize;
        this.text = text;
        this.keyH = keyH;
    }

    public int update(int selectedNum){
        selected = selectedNum == buttonNum;
        if (keyH.spacePressed == true){
            if (selectedNum == 1){
                return -1;
            }else if (selectedNum == 2){
                System.exit(0);
            }
        }
        return 1;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.black);
        g.fillRect(x,y,width,height);
        if (selected){
            g.setColor(Color.white);
            g.drawRect(x,y,width,height);
        }
    }

}
