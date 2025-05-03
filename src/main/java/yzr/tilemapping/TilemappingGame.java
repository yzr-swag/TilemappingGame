/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package yzr.tilemapping;

/**
 *
 * @author Joshua
 */
public class TilemappingGame {

    public static void main(String[] args) {
        Tile[][] map = new Tile[50][50];

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = new Tile();
            }
        }
    }
}
