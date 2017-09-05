package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Divya on 8/22/2017.
 */
public class Domino {

    public int top;
    public int bottom;

    public Domino(int top, int bottom){
        this.top = top;
        this.bottom = bottom;
    }

    public void printDomino() {
        System.out.println("["+top+", "+bottom+"]");
        System.out.println();
    }

    public void renderDomino(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 200, 200);
        //gc.drawImage(m.d00, 30, 30, 300, 300);
    }

}
