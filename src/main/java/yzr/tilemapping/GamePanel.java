package yzr.tilemapping;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import yzr.entity.Player;
import yzr.hud.Timer;

public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 24;
    final int screenWidth = tileSize * maxScreenRow;
    final int screenHeight = tileSize * maxScreenCol;

    int FPS= 60;

    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    Timer dashCooldown = new Timer(1000, 700, 64, 64, player.dashCooldown, Color.green, "src/main/resources/Dash icon.png");
    //Graphics g1;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
        //drawDefaults(g1);
    }

    public void drawDefaults(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        dashCooldown.drawDefault(g2);
        g2.dispose();

    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void run() {
        
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

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


    public void update() {

        player.update();
        dashCooldown.update(player.dashCooldown);

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        dashCooldown.drawDefault(g2);
        dashCooldown.drawOverlay(g2);

        //g2.dispose();

    }

    }
