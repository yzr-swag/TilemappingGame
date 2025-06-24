package yzr.entity;


import java.awt.Color;
import java.awt.Graphics2D;

import yzr.tilemapping.GamePanel;
import yzr.tilemapping.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    int dashCooldownLength;
    public int dashCooldown;
    int dashDistance;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 10;
        dashCooldownLength = 120;
        dashCooldown = 0;
        dashDistance = 100;
    }

    public void update() {
        if(dashCooldown >0){
            dashCooldown -= 1;
        }
        if(keyH.upPressed == true) {
            y-= speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                y-= dashDistance;
                dashCooldown = dashCooldownLength;
            }
        }
        if(keyH.downPressed == true) {
            y += speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                y+= dashDistance;
                dashCooldown = dashCooldownLength;
            }
        }
        if(keyH.leftPressed == true) {
            x -= speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                x-= dashDistance;
                dashCooldown = dashCooldownLength;
            }
        }
        if(keyH.rightPressed == true) {
            x += speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                x+= dashDistance;
                dashCooldown = dashCooldownLength;
            }
        }
    }

    public int dash(int axis, int direction){
        int dashTime = dashDistance / speed;
        axis += dashTime * direction;
        return axis;
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.white);

        g2.fillRect(x,y, gp.tileSize, gp.tileSize);

    }

}
