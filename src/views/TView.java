package views;

import models.TetrisBoard;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class TView extends JFrame{

    public static final int SHAPE_SIZE=30;
    private JLabel scoreLabel;

    TetrisBoard tetrisBoard;
    View2 tetrisNext;

    public TView(){

        // create a JFrame & add there the status bar
        super("Tetris Game");

        tetrisNext=new View2();
        tetrisNext.setPreferredSize(new Dimension(50,50));
        add(createStatusBar(), BorderLayout.NORTH);

        tetrisBoard=new TetrisBoard();
        add(tetrisBoard, BorderLayout.CENTER);


        // set size
        setSize((SHAPE_SIZE*10)+20, (SHAPE_SIZE*18)+90);

        // exit normally on closing the window
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // show frame
        setVisible( true );
    }


    public void updateStatusBar(int currentScore, String nextPiece){
        scoreLabel.setText(String.valueOf(currentScore));
        tetrisNext.repaintNextPiece(nextPiece);
    }

    public void updateBoard(int numCols, int numRows, int[][] boardFromModel, int [][] currentShape){
        int [][] board= new int[numCols][numRows];
        for (int y=0; y<numRows;y++)
            for (int x=0; x<numCols; x++){
                board[x][y]=boardFromModel[x][y];
            }

        // add to the cope of current board the current shape
        for (int i = 0; i < 4; ++i) {
            board[currentShape[i][0]][currentShape[i][1]]=currentShape[i][2];
        }

        tetrisBoard.setSTAT(numCols, numRows, SHAPE_SIZE, boardFromModel, currentShape);
        tetrisBoard.repaint();
    }

    private JPanel createStatusBar(){
        JPanel statusBar=new JPanel();

        // create the SCORE BOARD
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS  ));
        namePanel.add(new JLabel("Scored:"));

        // create the second colon
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
        scoreLabel=new JLabel("0");
        scorePanel.add(scoreLabel);

        statusBar.setLayout(new BorderLayout());
        statusBar.add(namePanel, BorderLayout.WEST);
        statusBar.add(scorePanel, BorderLayout.CENTER);
        statusBar.add(tetrisNext, BorderLayout.EAST);


        return statusBar;
    }


}