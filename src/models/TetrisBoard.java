package models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

//BOARD OF GAME
public class TetrisBoard extends JComponent
{

    int [][] board;
    int [][] currentShape;
    int numCols;
    int numRows;
    int tetSize;


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // create a background
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, tetSize*numCols, tetSize*numRows);


        // show the board
        if (board!=null){
            for (int x = 0; x < numCols; x++) {
                for (int y = 0; y < numRows; y++) {
                    if (board[x][y]>0){
                        g.setColor(getColor(board[x][y]));
                        g.fillRect(tetSize*x, tetSize*y, tetSize-1, tetSize-1);
                    }
                }
            }
        }

        // show the current shape
        if (currentShape!=null){
            for (int i = 0; i < 4; ++i){
                g.setColor(getColor(currentShape[i][2]));
                g.fillRect(tetSize*currentShape[i][0], tetSize*currentShape[i][1], tetSize-1, tetSize-1);
            }
        }

    }

    public void setSTAT(int numCols, int numRows, int tetSize, int[][] boardFromModel, int [][] currentShape){
        this.numCols=numCols;
        this.numRows=numRows;
        this.tetSize=tetSize;
        this.board=boardFromModel;
        this.currentShape=currentShape;
    }
//color for tetris pieces
    //https://www.programcreek.com/java-api-examples/?class=java.awt.Color&method=MAGENTA
    private Color getColor(int colorCode){
        switch (colorCode){
            case 1: return Color.RED;
            case 2: return Color.YELLOW;
            case 3: return Color.GREEN;
            case 4: return Color.CYAN;
            case 5: return Color.BLUE;
            case 6: return Color.MAGENTA;
            case 7: return Color.ORANGE;
            default: return Color.BLACK;
        }
    }

}