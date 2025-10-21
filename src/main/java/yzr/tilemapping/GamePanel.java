package yzr.tilemapping;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import yzr.entity.Player;
import yzr.hud.Timer;

public class GamePanel extends JPanel implements Runnable{

    //to ensure that all elements can be seen on any size screen #swag
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();

    final int maxScreenRow = 24;
    final int originalTileSize = 16;
    final int scale = (width / maxScreenRow) / originalTileSize;

    public final int tileSize = originalTileSize * scale;

    int FPS= 60;

    //define gameplay vars
    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Timer dashCooldown = new Timer((int) (width * 0.075), (int)(height * 0.1), tileSize, player.dashCooldownLength, "src/main/resources/Dash icon.png");
    Timer shieldCooldown = new Timer((int) (width *0.12), (int)(height * 0.1), tileSize, player.shieldCooldownLength, "src/main/resources/Shield icon.png");


    public GamePanel() {

        //initialize the window
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //control fps
        while(gameThread != null) {

            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime)  / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
            }


        }

    }


    public void update(){
        //updates each object called

        player.update();
        dashCooldown.update(player.dashCooldown);
        shieldCooldown.update(player.shieldCooldown);

    }

    @Override
    public void paintComponent(Graphics g) {
        //draws each object called

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        dashCooldown.drawDefault(g2);
        dashCooldown.drawOverlay(g2);

        shieldCooldown.drawDefault(g2);
        shieldCooldown.drawOverlay(g2);

        g2.dispose();

    }

}