
package yzr.tilemapping;

//swaag

import java.util.Scanner;



public class Tile {
    private static String tileType = "ground";
    private static String tileImg = "dirt1.png";

    public Tile(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Tile type");
        tileType = input.nextLine();
    }

    public String getTileType(){
        return tileType;
    }

    public String getTileImg(){
        return tileImg;
    }

    public void changeTileStyle(){
        tileType = "ocean";
        tileImg = "ocean1";

    }
}