package models;
import java.awt.Color;

public class ZShape extends Tetronimo{
    public static final int COLOR = 2;
    public ZShape (){
        //set coordinates of the blocks
        super(5, 0, 5, 1, 4, 1, 4, 2, COLOR, "ZSHAPE");
    }


    /**
     * Rotate shape
     */
    public int[][] rotate(int curRotation) {
        switch (curRotation){  // coordinate adjustment for rotation all blocks of this shape
            case 0: return new int[][] {{1,1},{0,0},{1,-1},{0,-2}};
            case 1: return new int[][] {{-2,1},{-1,0},{0,1},{1,0}};
            case 2: return new int[][] {{0,-2},{1,-1},{0,0},{1,1}};
            case 3: return new int[][] {{1,0},{0,1},{-1,0},{-2,1}};
            default: return new int[][] {{0,0},{0,0},{0,0},{0,0}};
        }
    }

}
