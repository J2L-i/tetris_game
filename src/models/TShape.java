package models;

import java.awt.*;

public class TShape extends Tetronimo {
    public static final int COLOR = 1;
    /**
     * Set the coordinates of the blocks to form TShape
     */
    public TShape (){
        //set coordinates of the blocks
        super(5, 0, 5, 1, 4, 1, 5, 2, COLOR, "TSHAPE");

    }
    @Override
    public int[][] rotate(int rotationIndex) { // coordinate adjustment for rotation all blocks of this shape
        switch (rotationIndex){
            case 0: return new int[][] {{1,1},{0,0},{1,-1},{-1,-1}};
            case 1: return new int[][] {{-1,1},{0,0},{1,1},{1,-1}};
            case 2: return new int[][] {{-1,-1},{0,0},{-1,1},{1,1}};
            case 3: return new int[][] {{1,-1},{0,0},{-1,-1},{-1,1}};
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }


}
