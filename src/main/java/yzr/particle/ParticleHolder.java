package yzr.particle;

import java.awt.*;

public class ParticleHolder {
    Particle[] myParticleHolder;
    int particleAmount;
    int particleSize;
    int particleX = 0;
    int particleY= 0;

    public ParticleHolder(int amount, int size){
        particleAmount = amount;
        particleSize = size;
    }

    public void createDashParticleHolder(int x, int y, String dashAxis, int dashDirection){
        //reinitializes particleHolder, and sets particles
        myParticleHolder = new Particle[particleAmount];
        for(int i = 0; i< particleAmount; i++){
            particleX = x;
            particleY = y;
            setDashParticleLoc(dashAxis,dashDirection * -1);
            myParticleHolder[i] = new Particle(particleSize, particleX, particleY, "src/main/resources/Star Particle.png");
        }
    }

    public void drawParticles(Graphics2D g2){
        //calls each item in particleHolder to be drawn
        if (myParticleHolder != null){
            for(int i =0; i< particleAmount; i++){
                myParticleHolder[i].drawParticle(g2);
            }
        }
    }

    public void setDashParticleLoc(String dashAxis, int dashDirection){
        //creates a triangle-ish shape of particles when dashing
        int yMoved;
        int xMoved;
        if(dashAxis.equals("y")){
            yMoved = (int) (Math.random() * 64 * dashDirection );
            if (Math.random() > 0.5){
                xMoved = (int) (yMoved * Math.random() * -0.5);
            }else {
                xMoved = (int) (yMoved * Math.random() * 0.5);
            }
        } else{
            xMoved = (int) (Math.random() * 64 * dashDirection);
            if (Math.random() > 0.5){
                yMoved = (int) (xMoved * Math.random() * -0.5);
            }else {
                yMoved = (int) (xMoved * Math.random() * 0.5);
            }
        }
        particleY += yMoved + 16;
        particleX += xMoved + 16;
    }

    public void update(int xMoved, int yMoved){
        //particles follow player dashing
        for(int i =0; i< particleAmount; i++){
            myParticleHolder[i].xLoc += xMoved;
            myParticleHolder[i].yLoc += yMoved;
        }

    }

    public void disposeParticleHolder(){
        myParticleHolder = null;
    }

}
