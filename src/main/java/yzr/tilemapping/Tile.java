
package yzr.tilemapping;

//swaag

import java.io.File;



public class Tile {
    private static String tileType = "ground";
    private static String tileImg = "dirt1.png";

    public Tile(){
        //sumin here
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

    public void fileTest(){
        File tileFile = new File(tileImg);
    }

}