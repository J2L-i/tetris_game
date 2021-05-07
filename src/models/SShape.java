package models;

import java.awt.*;

public class SShape extends Tetronimo {
    public static final int color = 6;//TetrisBoard.java
    public SShape(){ //constructor and coord for blocks
        super(5,0,5,1,6,1,6,2,color, "SSHAPE");
    }
    @Override

    public int[][] rotate(int curRotation) {
        switch (curRotation){  // coordinate adjustment for rotation all blocks of this shape
            case 0: return new int[][] {{1,0},{0,-1},{-1,0},{-2,-1}};
            case 1: return new int[][] {{0,2},{1,1},{0,0},{1,-1}};
            case 2: return new int[][] {{-2,-1},{-1,0},{0,-1},{1,0}};
            case 3: return new int[][] {{1,-1},{0,0},{1,1},{0,2}};
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }


}
