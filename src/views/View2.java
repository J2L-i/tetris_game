package views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
/**This is an extra componenent to show the next random tetronimo*/
public class View2 extends JComponent{
    private String nextPiece;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);

        if (nextPiece!=null){
            switch (nextPiece){
                case "SQUARE":
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(20, 10, 9, 9);
                    g.fillRect(10, 20, 9, 9);
                    g.fillRect(20, 20, 9, 9);
                    break;
                case "LSHAPE":
                    g.fillRect(10, 0,  9, 9);
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(10, 20, 9, 9);
                    g.fillRect(20, 20, 9, 9);
                    break;
                case "MIRROREDL":
                    g.fillRect(20, 0,  9, 9);
                    g.fillRect(20, 10, 9, 9);
                    g.fillRect(20, 20, 9, 9);
                    g.fillRect(10, 20, 9, 9);
                    break;
                case "Line":
                    g.fillRect(10, 0,  9, 9);
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(10, 20, 9, 9);
                    g.fillRect(10, 30, 9, 9);
                    break;
                case "TSHAPE":
                    g.fillRect(20,  0, 9, 9);
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(20, 10, 9, 9);
                    g.fillRect(20, 20, 9, 9);
                    break;
                case "SSHAPE":
                    g.fillRect(10, 0,  9, 9);
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(20, 10, 9, 9);
                    g.fillRect(20, 20, 9, 9);
                    break;
                case "ZSHAPE":
                    g.fillRect(20, 0,  9, 9);
                    g.fillRect(20, 10, 9, 9);
                    g.fillRect(10, 10, 9, 9);
                    g.fillRect(10, 20, 9, 9);
                    break;
            }}

    }

    public void repaintNextPiece(String nextPiece){
        this.nextPiece=nextPiece;
        super.repaint();
    }
}