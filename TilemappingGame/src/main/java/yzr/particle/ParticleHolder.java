package yzr.particle;

import java.awt.*;

public class ParticleHolder {
    Particle[] myParticleHolder;
    int particleAmount;
    int particleSize;
    int particleX = 0;
    int particleY= 0;

    public ParticleHolder(){
        particleAmount = 20;
        particleSize = 5;
    }


    public void createParticleHolder(int x, int y, String dashAxis, int dashDirection){
        //reinitializes particleHolder, and sets particles
        myParticleHolder = new Particle[particleAmount];
        for(int i = 0; i< particleAmount; i++){
            particleX = x;
            particleY = y;
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



    public void update(int xMoved, int yMoved){
       //update particles here

    }

    public void disposeParticleHolder(){
        myParticleHolder = null;
    }

}
