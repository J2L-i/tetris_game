package controllers;
import models.Tetronimo;
import models.TShape;
import models.MirroredL;
import models.Square;
import models.SShape;
import models.StraightLine;
import models.LShape;
import models.ZShape;


import java.awt.Color;
import java.util.Random;

//Game Logic

public class TetrisC2 {

    public static final int NUM_ROWS=18;
    //number of columns in board
    public static final int NUM_COLS=10;
    // number of shapes
    private static final int NUM_SHAPES=7;
    //board
    private int [][] board;
    // call current piece
    private Tetronimo currentShape;
    //next piece
    private Tetronimo nextShape;
    private boolean isFinished = false;
    private int currentScore=0;
    private int speed=1000; // 1 sec.
    private	Thread t;
    private int savedBoard[][];


    public TetrisC2() {
        board = new int[NUM_COLS][NUM_ROWS];
        // initialization a new game with a clear board
        clearBoard();

        // get the first shape
        currentShape=generateRandomShape();
        // get the next shape
        nextShape=generateRandomShape();


        // create additional array with a board (allows the user to restart a level)
        savedBoard= new int[NUM_COLS][NUM_ROWS];


        startGame(); // start to move a shape down


    }


    /**
     * Starts a game
     */
    public void startGame(){
        // every 1 sec. moves current shape down
        t=new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if(!Thread.interrupted()){
                        try {
                            Thread.sleep(speed);
                            moveDown(); // moves current shape down
                        } catch ( InterruptedException e ) {}
                    }else return; // interrupt the running thread
                }
            }
        });
        t.start();
    }





    /**
     * Restores game
     */
    public void restartGame(){
        for (int y=0; y<NUM_ROWS;y++)
            for (int x=0; x<NUM_COLS; x++){
                board[x][y]=savedBoard[x][y];
            }
        isFinished=false;
        currentShape=generateRandomShape(); // get the first shape
        nextShape=generateRandomShape(); // get the next shape
        startGame(); // start to move a shape down
    }



    public int[][] getBoard(){
        return board;
    }

    public String getNextPieceName(){
        return nextShape.getPieceName();
    }



    public Tetronimo getCurrentShape(){
        return currentShape;
    }

    /**
     * Return true if the game is finished
     * @return boolean
     */
    public boolean getIsFinished(){
        return isFinished;
    }

    /**
     * Return number of lines cleared
     * @return
     */
    public int getScore(){
        return currentScore;
    }





    /**
     * Clear the board to start a new game.
     * populated with 0. 0 means no figure present.
     */
    public void clearBoard(){
        for (int y=0; y<NUM_ROWS;y++)
            for (int x=0; x<NUM_COLS; x++){
                board[x][y]=0;
            }
    }


   //Generates a random number to represent tetronimo

    public Tetronimo generateRandomShape(){
        Random randomNumber = new Random ();
        int iNumber=randomNumber.nextInt(NUM_SHAPES); //generates a random number from 0 to NUMBER_SHAPES-1

        switch (iNumber){
            case 0:
                return new TShape();
            case 1:
                return new ZShape();
            case 2:
                return new StraightLine();
            case 3:
                return new LShape();
            case 4:
                return new Square();
            case 5:
                return new SShape();
            case 6:
                return new MirroredL();
            default:
                return null;
        }
    }

   //check if space is free
    private boolean checkSpace(int plusX, int plusY, Tetronimo currentShape) {
        for (int i = 0; i < 4; i++) { // for all blocks in the current shape...
            // calculates perspective coordinates
            int x = currentShape.getBlockArray()[i].getXPos() + plusX;
            int y = currentShape.getBlockArray()[i].getYPos() + plusY;
            if (x < 0 | x >= NUM_COLS | y < 0 | y >= NUM_ROWS){
                return false;	 // false if we try to move outside the playing area
            }
            if (board[x][y]!=0){ // false if the other shape is already on this place
                return false;
            }
        }
        return true; // all positions are vacant
    }


   //check space for rotation
    private boolean checkSpaceRotation(int [][] newCoordinates, Tetronimo currentShape) {
        for (int i = 0; i < 4; i++) {	// for all blocks in the current shape...
            // calculates perspective coordinates
            int x = currentShape.getBlockArray()[i].getXPos() + newCoordinates[i][0];
            int y = currentShape.getBlockArray()[i].getYPos() + newCoordinates[i][1];

            if (x < 0 | x >= NUM_COLS | y < 0 | y >= NUM_ROWS){
                return false;	 // false if we try to move outside the playing area
            }
            if (board[x][y]!=0){ // false if the other shape is already on this place
                return false;
            }
        }
        return true; // can be rotated
    }



    /**
     * Move a shape one row down
     */
    public void moveDown(){

        if (checkSpace(0, +1, currentShape)==true){ // check if perspective coordinates for all blocks of the current shape are vacant

            for (int i = 0; i < 4; i++) {
                currentShape.getBlockArray()[i].setYPos(currentShape.getBlockArray()[i].getYPos()+1); // add to the Y-coordinates all blocks of the current shape +1
            }
        } else { // if we can't move down current shape
            for (int i = 0; i < 4; i++) {
                // the current shape becomes a part of the board
                board[currentShape.getBlockArray()[i].getXPos()][currentShape.getBlockArray()[i].getYPos()]=currentShape.getBlockArray()[i].getColor(); // save color all blocks of the current shape
                // check a status of the game. If a part of shape lying on the first row of the board then the game over
                if (currentShape.getBlockArray()[i].getYPos()<1) {

                    gameLost();
                    return; // exit from "for" loop
                }
            }
            if (isFinished==false) {
                // clear completed rows
                clearLines();
                // generate a new shape
                currentShape=nextShape;
                nextShape=generateRandomShape();
            }
        }


    }


    /**
     * Move piece one colon left of right
     */
    public void moveLeftOrRight(int newX){
        if (checkSpace(newX, 0, currentShape)==true){ // check if  coordinates for  blocks of  currentshape are clear
            for (int i = 0; i < 4; i++) {
                currentShape.getBlockArray()[i].setXPos(currentShape.getBlockArray()[i].getXPos()+newX);  // add to the X-coordinates all blocks of the current shape +1 if move right and -1 when move left
            }
        }
    }

    //End game method when a tetronimo piece reaches the top of the game board
    public void gameLost()
    {
        isFinished=true;
        t.interrupt(); // interrupt the running thread
    }



    /**
     * Rotate current shape clockwise or counter-clockwise
     */
    public void rotate (boolean isRotateClockwise){
        int curRotation=currentShape.getRotationIndex();

        if (isRotateClockwise==false){ // if rotate counter-clockwise than take (not current) the previous rotationIndex
            if (curRotation==0){
                curRotation=3;
            } else curRotation--;
        }

        int [][] newCoordinates=currentShape.rotate(curRotation);

        if (isRotateClockwise==false){ // if rotate counter-clockwise than convert rotate values to opposite
            for (int i = 0; i < 4; i++) {
                newCoordinates[i][0]*=-1;
                newCoordinates[i][1]*=-1;
            }
        }

        if (checkSpaceRotation(newCoordinates, currentShape)){ // check if perspective coordinates for all blocks of the current shape are vacant
            currentShape.setRotationIndex(isRotateClockwise); // save new value of rotationIndex of the current shape
            for (int i = 0; i < 4; i++) {	// change coordinates for all blocks of the current shape
                currentShape.getBlockArray()[i].setXPos (currentShape.getBlockArray()[i].getXPos() + newCoordinates[i][0]);
                currentShape.getBlockArray()[i].setYPos (currentShape.getBlockArray()[i].getYPos() + newCoordinates[i][1]);
            }
        }
    }



   // Clear when a line of Tetris blocks is formed

    public void clearLines() {
        boolean hasRowSpace;
        int numberLinesCleared = 0;
        for (int y=0; y<NUM_ROWS;y++) {
            hasRowSpace = false;
            for (int x=0; x<NUM_COLS; x++){
                if (board[x][y]==0) {
                    hasRowSpace=true;
                    break;
                }
            }
            if (hasRowSpace==false) {
                numberLinesCleared++;
                shiftBlocksDown(y); 	// clear completed row
                y--;
            }
        }

        // compute score
        switch (numberLinesCleared) {
            case 1: //1 row full
                currentScore += 100;
                break;
            case 2:
                currentScore += 100;
                break;
            case 3:
                currentScore += 100;;
                break;
            case 4:
                currentScore += 800;

                break;
        }


    }

  //move tetris down when full line formed
    public void shiftBlocksDown(int rowNumber)
    {
        for (int y = rowNumber; y > 0; y--) {
            for (int x = 0; x < NUM_COLS; x++) {
                board[x][y] = board[x][y-1];
            }
        }
    }




}

