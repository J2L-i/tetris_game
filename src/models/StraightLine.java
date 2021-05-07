package models;
import java.awt.Color;


public class StraightLine extends Tetronimo {

    public static final int COLOR = 3; // TetrisBoard.java


    public StraightLine() {
        //set coordinates of the blocks
        super(5, 0, 5, 1, 5, 2, 5, 3, COLOR, "Line");
    }


    //rotation
    public int[][] rotate(int curRotation) {
        switch (curRotation) {  // coordinate adjustment for rotation all blocks of this shape
            case 0:
                return new int[][]{{1, 0}, {0, -1}, {-1, -2}, {-2, -3}};
            case 1:
                return new int[][]{{-1, 3}, {0, 2}, {1, 1}, {2, 0}};
            case 2:
                return new int[][]{{-2, -3}, {-1, -2}, {0, -1}, {1, 0}};
            case 3:
                return new int[][]{{2, 0}, {1, 1}, {0, 2}, {-1, 3}};
            default:
                return new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        }
    }
}
