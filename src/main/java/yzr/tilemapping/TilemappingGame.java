/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package yzr.tilemapping;
/**
 *
 * @author Joshua
 */


import javax.swing.JFrame;

public class TilemappingGame {
 

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("TileMapping Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}



    //public static void main(String[] args) {
    //    Tile[][] map = new Tile[50][50];
    //    

    //    for (int i = 0; i < 50; i++) {
    //        for (int j = 0; j < 50; j++) {
    //            map[i][j] = new Tile();
    //        }
    //    }
        
    //    map[1][1].fileTest();

