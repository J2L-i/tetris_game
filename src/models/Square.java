package models;

import java.awt.*;

//box piece
public class Square extends Tetronimo {
    public static final int COLOR = 5;//color on TetrisBoard.java
    public Square (){
        //set coordinates of the blocks
        super(5, 0, 6, 0, 5, 1, 6, 1, COLOR, "SQUARE");
    }



    public int[][] rotate(int curRotation) {
        switch (curRotation){  // coordinate adjustment for rotation all blocks of this shape
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }

}
