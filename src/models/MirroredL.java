package models;

import java.awt.*;

// L shape piece
public class MirroredL extends Tetronimo{
    public static final int COLOR = 7;


    public MirroredL(){
        //set coordinates of the blocks
        super(5, 0, 5, 1, 5, 2, 4, 2, COLOR, "MIRROREDL");
    }


    @Override
    //how it looks in rotation

    public int[][] rotate(int curRotation) {
        switch (curRotation){  // coordinate adjustment for rotation all blocks of this shape
            case 0: return new int[][] {{1,1},{0,0},{-1,-1},{0,-2}};
            case 1: return new int[][] {{-1,1},{0,0},{1,-1},{2,0}};
            case 2: return new int[][] {{-1,-2},{0,-1},{1,0},{0,1}};
            case 3: return new int[][] {{1,0},{0,1},{-1,2},{-2,1}};
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }

}
