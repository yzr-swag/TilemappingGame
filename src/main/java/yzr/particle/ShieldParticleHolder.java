

package yzr.particle;

public class ShieldParticleHolder extends ParticleHolder{

    int x,y,tileSize;

    public ShieldParticleHolder(int particleAmount, int particleSize, int tileSize){
        this.particleAmount = particleAmount;
        this.particleSize = particleSize;
        this.tileSize = tileSize;

    }

    public void createParticleHolder(int x,int y){
        myParticleHolder = new Particle[particleAmount];
        this.x = x + tileSize/2;
        this.y = y + tileSize/2;
        for(int i=0; i < particleAmount; i++){
            setParticleLoc(i, particleAmount);
            myParticleHolder[i] = new Particle(particleSize, particleX, particleY, "src/main/resources/Star Particle.png");
        }
    }

    public void setParticleLoc(int particleNum, int particleAmount){
        double particleAngle = (double) (360 * particleNum) / particleAmount;
        particleY = y + (int) (tileSize * Math.sin(particleAngle));
        particleX = x + (int) (tileSize * Math.cos(particleAngle));
    }
}
