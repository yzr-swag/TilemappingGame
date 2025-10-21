package yzr.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import yzr.particle.ParticleHolder;
import yzr.tilemapping.GamePanel;
import yzr.tilemapping.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public int dashCooldownLength;
    public int dashCooldown;
    int dashSpeed;
    String dashAxis;
    int dashTimeRemaining;
    int dashDirection;
    int dashLength;

    int shieldLength;
    public int shieldCooldown;
    public int shieldCooldownLength;
    int shieldTimeRemaining;
    Color shieldColor;

    int particleAmount;
    int particleSize;
    ParticleHolder dashParticleHolder;


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();

    }

    public void setDefaultValues() {
        //it's in the name

        x = 500;
        y = 500;
        speed = 10;

        dashCooldownLength = 120;
        dashCooldown = 0;
        dashSpeed = 40;
        dashTimeRemaining = 0;
        dashDirection = -1;
        dashLength = 10;

        shieldCooldownLength = 180;
        shieldCooldown = 0;
        shieldTimeRemaining = 0;
        shieldLength = 30;
        shieldColor = Color.BLUE;

        particleSize = 8;
        particleAmount = 25;

        dashParticleHolder = new ParticleHolder(particleAmount, particleSize);

    }

    public void update(){

        normaliseMovement();

        //in order to dash or shield, the update loop is taken over, so the player cannot move
        //inputs will be eaten
        if (dashTimeRemaining > 0){
            if("x".equals(dashAxis)){
                x += dashSpeed * dashDirection;
                dashParticleHolder.update(dashSpeed * dashDirection, 0);
            }else{
                y += dashSpeed * dashDirection;
                dashParticleHolder.update(0, dashSpeed * dashDirection);
            }
            dashTimeRemaining--;
            dashCooldown = dashCooldownLength;

        }else if(shieldTimeRemaining > 0){
            shieldTimeRemaining--;
            shieldCooldown = shieldCooldownLength;
        }

        else{
            dashParticleHolder.disposeParticleHolder();
            if(dashCooldown >0){
            dashCooldown --;
            }
            if(shieldCooldown >0){
                shieldCooldown --;
            }

        if(keyH.ctrlPressed & shieldCooldown == 0 &  dashTimeRemaining == 0){
            shieldTimeRemaining = shieldLength;
        }

        movementHandler();
        }
    }

    public void normaliseMovement(){
        //ensures that diagonal movement is not faster than other movement
        speed = 10;
        if (keyH.upPressed && keyH.leftPressed){
            speed = (int) (Math.sqrt((double) (speed * speed) / 2));
        }else if (keyH.upPressed && keyH.rightPressed){
            speed = (int) (Math.sqrt((double) (speed * speed) / 2));
        }else if (keyH.downPressed && keyH.leftPressed){
            speed = (int) (Math.sqrt((double) (speed * speed) / 2));
        }else if (keyH.downPressed && keyH.rightPressed){
            speed = (int) (Math.sqrt((double) (speed * speed) / 2));
        }
    }

    public void movementHandler(){
        //general movement code. Listens for dashes also
        if(keyH.upPressed) {
            y-= speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "y";
                dashDirection = -1;
                dashParticleHolder.createDashParticleHolder(x,y +50, dashAxis, dashDirection);
            }
        }
        if(keyH.downPressed) {
            y += speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "y";
                dashDirection = 1;
                dashParticleHolder.createDashParticleHolder(x,y-50,dashAxis, dashDirection);
            }

        }
        if(keyH.leftPressed) {
            x -= speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "x";
                dashDirection = -1;
                dashParticleHolder.createDashParticleHolder(x+50,y,dashAxis, dashDirection);
            }
        }
        if(keyH.rightPressed) {
            x += speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "x";
                dashDirection = 1;
                dashParticleHolder.createDashParticleHolder(x-50,y, dashAxis, dashDirection);
            }
        }
    }

    public void draw(Graphics2D g2) {
        //it's in the name
        if(shieldTimeRemaining >0) {
            g2.setColor(shieldColor);
        }else {

            g2.setColor(Color.white);
        }

        g2.fillRect(x,y, gp.tileSize, gp.tileSize);
        //drawParticles(g2);
        dashParticleHolder.drawParticles(g2);
    }

}