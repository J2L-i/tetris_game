package models;

import java.awt.Color;

/**
 * Tetronimo.java:
 * An abstract class to model the base capaabilities of a tetronimo
 *
 * @author Jennifer Lewis
 * @version 1.0 December 14, 2020
 *
 */
public abstract class Tetronimo {
    /**
     * Constant to represent the size of the tetronimo
     */
    private Block[] blockArray = new Block[4];

    private String pieceName;

    int curRotation;

    // Generates the four rectangles for the tetronino and puts them on the screen,

        public Tetronimo (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int color, String pieceName){
        blockArray[0] = new Block(x1, y1, color);
        blockArray[1] = new Block(x2, y2, color);
        blockArray[2] = new Block(x3, y3, color);
        blockArray[3] = new Block(x4, y4, color);
        this.pieceName=pieceName;

        curRotation = 0;
    }
    public Block [] getBlockArray(){
        return blockArray;
    }

    /**
     * Increments the rotation of the tetronimo, other classes
     */
    public void setRotationIndex(boolean isRotateClockwise){
        if (isRotateClockwise==true){ // shift clockwise then assign next rotation
            if (curRotation<3){
                curRotation++;
            } else curRotation=0;
        } else {					 // shifts tetronimo counter-clockwise(left) t
            if (curRotation==0){
                curRotation=3;
            } else curRotation--;
        }

    }

   //Rotation
    public int getRotationIndex(){
        return curRotation;
    }

   //Class
    public String getPieceName(){
        return pieceName;
    }


    /**
     * Rotate shape
     */
    public abstract int[][] rotate(int curRotation);


}
