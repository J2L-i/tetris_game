package controllers;
import views.TView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;



public class TetrisController implements KeyListener  { // Use KeyListener

    private TetrisC2 tetrisPiece= new TetrisC2(); // create a game piece
    private TView tetrisView;		// create a View Model of our board

    public TetrisController() { //constructor
        tetrisView=new TView();
        tetrisView.addKeyListener(this); // add KeyListener in the JFrame of the ViewModel

        new Thread() {
            @Override public void run() {
                while (true) {
                    try {
                        if (tetrisPiece.getIsFinished()==true){
                            restartLevelDialog();
                        }
                        Thread.sleep(200); // repeat very 0.2 sec.
                        updateView(); // call View to update screen
                    } catch ( InterruptedException e ) {}
                }
            }
        }.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT : // move left
                // call a method for move left
                tetrisPiece.moveLeftOrRight(-1);
                break;
            case KeyEvent.VK_RIGHT: // move right
                // call a method for move right
                tetrisPiece.moveLeftOrRight(+1);
                break;
            case KeyEvent.VK_DOWN : // down
                // call a method for down
                tetrisPiece.moveDown();
                break;
            case KeyEvent.VK_Z: // rotate counter-clockwise
                // call a method for rotate counter-clockwise
                tetrisPiece.rotate(false);
                break;
            case KeyEvent.VK_X: // rotate clockwise
                // call a method for rotate clockwise
                tetrisPiece.rotate(true);
                break;
        }
        updateView(); // call Model to update information on the screen

    }

    /**
     * Calls TetrisC2 Model to update moving shapes down the board
     */
    private void updateView(){
        int [][] currentShape = new int [4][3];
        for (int i = 0; i < 4; ++i) {
            currentShape [i][0]=tetrisPiece.getCurrentShape().getBlockArray()[i].getXPos();
            currentShape [i][1]=tetrisPiece.getCurrentShape().getBlockArray()[i].getYPos();
            currentShape [i][2]=tetrisPiece.getCurrentShape().getBlockArray()[i].getColor();
        }

        tetrisView.updateBoard(tetrisPiece.NUM_COLS, tetrisPiece.NUM_ROWS, tetrisPiece.getBoard(), currentShape);
        tetrisView.updateStatusBar(tetrisPiece.getScore(), tetrisPiece.getNextPieceName());  // call TetrisC2 to show score awards, next shape
    }

    //Shows a dialog  restarts the game

    private void restartLevelDialog(){
        int reply = JOptionPane.showConfirmDialog(null, "Game Over! Restart the game?",
                "Restart the game",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            tetrisPiece.restartGame();
        } else System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
