package yzr.entity;


import java.awt.Color;
import java.awt.Graphics2D;

import yzr.tilemapping.GamePanel;
import yzr.tilemapping.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    public int dashCooldownLength;
    public int dashCooldown;
    int dashSpeed;
    String dashAxis;
    int dashCount;
    int dashDirection;
    int dashLength;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();

    }

    public void setDefaultValues() {
        x = 500;
        y = 500;
        speed = 10;
        dashCooldownLength = 120;
        dashCooldown = 0;
        dashSpeed = 80;
        dashCount = 0;
        dashDirection = -1;
        dashLength = 5;
    }

    public void update() {
        
        //code to normalise diagonal movement 
        speed = 10;
        if (keyH.upPressed == true && keyH.leftPressed == true && speed == 10){
            speed = (int) (Math.sqrt((speed * speed)  / 2));
        }else if (keyH.upPressed == true && keyH.rightPressed == true && speed == 10){
            speed = (int) (Math.sqrt((speed * speed)  / 2));
        }else if (keyH.downPressed == true && keyH.leftPressed == true && speed == 10){
            speed = (int) (Math.sqrt((speed * speed)  / 2));
        }else if (keyH.downPressed == true && keyH.rightPressed == true && speed == 10){
            speed = (int) (Math.sqrt((speed * speed)  / 2));
        }

        //this does the dashing
        if (dashCount > 0){
            if("x".equals(dashAxis)){
                x += dashSpeed * dashDirection;
            }else{
                y += dashSpeed * dashDirection;
            }
            dashCount --;
            dashCooldown = dashCooldownLength;
        }else{
            if(dashCooldown >0){
            dashCooldown -= 1;
        }

        //general movement code
        if(keyH.upPressed == true) {
            y-= speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                dashCount = dashLength;
                dashAxis = "y";
                dashDirection = -1;
            }
        }
        if(keyH.downPressed == true) {
            y += speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                dashCount = dashLength;
                dashAxis = "y";
                dashDirection = 1;
            }
        }
        if(keyH.leftPressed == true) {
            x -= speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                dashCount = dashLength;
                dashAxis = "x";
                dashDirection = -1;
            }
        }
        if(keyH.rightPressed == true) {
            x += speed;
            if(keyH.shiftPressed == true & dashCooldown == 0) {
                dashCount = dashLength;
                dashAxis = "x";
                dashDirection = 1;
            }
        }
        }
    }


    public void draw(Graphics2D g2) {

        g2.setColor(Color.white);

        g2.fillRect(x,y, gp.tileSize, gp.tileSize);

    }

}
