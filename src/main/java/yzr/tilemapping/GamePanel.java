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
    final int screenWidth = width;
    final int screenHeight = height;

    int FPS= 60;

    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    Timer dashCooldown = new Timer(width - 100, 100, (int) (tileSize * 0.75), player.dashCooldownLength, "src/main/resources/Dash icon.png");

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

        g2.dispose();

    }

    }
