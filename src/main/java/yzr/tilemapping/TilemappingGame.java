/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package yzr.tilemapping;

import javax.swing.JFrame;
import java.awt.*;

public class TilemappingGame {

    public static void main(String[] args) {
        //creating window and gamePanel
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();


        //setting window defaults
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("TileMapping Game");
        window.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Rock 1.png"));

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}
