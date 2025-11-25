package yzr.particle;

import java.awt.*;

public class DashParticleHolder extends ParticleHolder{
    Particle[] myParticleHolder;
    int particleAmount;
    int particleSize;
    int particleX = 0;
    int particleY= 0;
    int tileSize;

    public DashParticleHolder(int amount, int size, int tileSize){
        particleAmount = amount;
        particleSize = size;
        this.tileSize = tileSize;
    }


    public void createParticleHolder(int x, int y, String dashAxis, int dashDirection){
        //reinitializes particleHolder, and sets particles
        myParticleHolder = new Particle[particleAmount];
        for(int i = 0; i< particleAmount; i++){
            particleX = x;
            particleY = y;
            setParticleLoc(dashAxis,dashDirection * -1, tileSize);
            myParticleHolder[i] = new Particle(particleSize, particleX, particleY, "src/main/resources/Star Particle.png");
        }
    }

    public void setParticleLoc(String dashAxis, int dashDirection, int tileSize){
        //creates a triangle-ish shape of particles when dashing
        int yMoved;
        int xMoved;
        if(dashAxis.equals("y")){
            yMoved = (int) (Math.random() * tileSize * dashDirection );
            if (Math.random() > 0.5){
                xMoved = (int) (yMoved * Math.random() * -0.5);
            }else {
                xMoved = (int) (yMoved * Math.random() * 0.5);
            }
        } else{
            xMoved = (int) (Math.random() * tileSize * dashDirection);
            if (Math.random() > 0.5){
                yMoved = (int) (xMoved * Math.random() * -0.5);
            }else {
                yMoved = (int) (xMoved * Math.random() * 0.5);
            }
        }
        particleY += yMoved + tileSize/2;
        particleX += xMoved + tileSize/2;
    }

    @Override
    public void update(int xMoved, int yMoved){
        //particles follow player dashing
        for(int i =0; i< particleAmount; i++){
            myParticleHolder[i].xLoc += xMoved;
            myParticleHolder[i].yLoc += yMoved;
        }
    }

    @Override
    public void drawParticles(Graphics2D g2){
        //calls each item in particleHolder to be drawn
        if (myParticleHolder != null){
            for(int i =0; i< particleAmount; i++){
                myParticleHolder[i].drawParticle(g2);
            }
        }
    }

    @Override
    public void disposeParticleHolder(){
        myParticleHolder = null;
    }
}
