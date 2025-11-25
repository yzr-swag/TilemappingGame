package yzr.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import yzr.particle.DashParticleHolder;
import yzr.particle.ShieldParticleHolder;
import yzr.tilemapping.GamePanel;
import yzr.tilemapping.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    int defaultSpeed;

    public int dashCooldownLength, dashCooldown;
    int dashSpeed, dashTimeRemaining, dashDirection, dashLength;
    String dashAxis;

    int shieldLength, shieldTimeRemaining;
    public int shieldCooldown, shieldCooldownLength;
    Color shieldColor;

    int particleAmount;
    int particleSize;
    DashParticleHolder dashParticleHolder;

    ShieldParticleHolder shieldParticleHolder;


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(GamePanel gp, KeyHandler keyH, int FPS, int tileSize) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues(FPS,tileSize);

    }

    public void setDefaultValues(int FPS,int tileSize) {
        //it's in the name

        x = 100;
        y = 100;
        speed = (int)(((double) 500 /FPS) *(0.1*tileSize));
        defaultSpeed = (int)(((double) 500 /FPS) *(0.1*tileSize));
//depend speed & dash n shit on tileSiz and FPS
        dashCooldownLength = 2*FPS;
        dashCooldown = 0;
        dashSpeed = 2400/FPS;
        dashTimeRemaining = 0;
        dashDirection = -1;
        dashLength = (int) (0.2*FPS);

        shieldCooldownLength = 3*FPS;
        shieldCooldown = 0;
        shieldTimeRemaining = 0;
        shieldLength = (int)(0.75*FPS);
        shieldColor = Color.BLUE;

        particleSize = (int)(0.2 * tileSize);
        particleAmount = 25;

        dashParticleHolder = new DashParticleHolder(particleAmount, particleSize,tileSize);
        shieldParticleHolder = new ShieldParticleHolder(particleAmount, particleSize, tileSize);

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
            shieldParticleHolder.disposeParticleHolder();
            dashParticleHolder.disposeParticleHolder();
            if(dashCooldown >0){
            dashCooldown --;
            }
            if(shieldCooldown >0){
                shieldCooldown --;
            }

        if(keyH.ctrlPressed & shieldCooldown == 0 &  dashTimeRemaining == 0){
            shieldTimeRemaining = shieldLength;
            shieldParticleHolder.createParticleHolder(x,y);
        }

        movementHandler();
        }
    }

    public void normaliseMovement(){
        //ensures that diagonal movement is not faster than other movement
        speed = defaultSpeed;
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
                dashParticleHolder.createParticleHolder(x,y +50, dashAxis, dashDirection);
            }
        }
        if(keyH.downPressed) {
            y += speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "y";
                dashDirection = 1;
                dashParticleHolder.createParticleHolder(x,y-50,dashAxis, dashDirection);
            }

        }
        if(keyH.leftPressed) {
            x -= speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "x";
                dashDirection = -1;
                dashParticleHolder.createParticleHolder(x+50,y,dashAxis, dashDirection);
            }
        }
        if(keyH.rightPressed) {
            x += speed;
            if(keyH.shiftPressed & dashCooldown == 0 & shieldTimeRemaining == 0) {
                dashTimeRemaining = dashLength;
                dashAxis = "x";
                dashDirection = 1;
                dashParticleHolder.createParticleHolder(x-50,y, dashAxis, dashDirection);
            }
        }
    }

    public void draw(Graphics2D g) {
        //it's in the name
        if(shieldTimeRemaining >0) {
            g.setColor(shieldColor);
        }else {

            g.setColor(Color.white);
        }

        g.fillRect(x,y, gp.tileSize, gp.tileSize);
        dashParticleHolder.drawParticles(g);
        shieldParticleHolder.drawParticles(g);

    }

}