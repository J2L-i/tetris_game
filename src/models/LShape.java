package models;

import java.awt.*;

public class LShape extends Tetronimo {
    public static final int COLOR = 4;


    public LShape (){ //constructor
        //set coordinates of the blocks
        super(5, 0, 5, 1, 5, 2, 6, 2, COLOR, "LSHAPE");
    }

    //how it looks in rotation
    public int[][] rotate(int curRotation) {
        switch (curRotation){  // coordinate adjustment for rotation all blocks of this shape
            case 0: return new int[][] {{1,0},{0,-1},{-1,-2},{-2,-1}};
            case 1: return new int[][] {{-1,2},{0,1},{1,0},{0,-1}};
            case 2: return new int[][] {{-1,-1},{0,0},{1,1},{2,0}};
            case 3: return new int[][] {{1,-1},{0,0},{-1,1},{0,2}};
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }
}
