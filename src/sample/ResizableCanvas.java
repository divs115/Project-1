package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Part of this class is from an answer on
 * https://stackoverflow.com/questions/27808210/java-fx-splitpane
 */

class ResizableCanvas extends Pane {

    private final String id;
    private Canvas canvas = new Canvas();
    private Main m = new Main();

    public ResizableCanvas(String id) {
        this.id=id;
        getChildren().add(this.canvas);
    }

    @Override
    public void layoutChildren() {
        final int TOP = (int)snappedTopInset();
        final int right = (int)snappedRightInset();
        final int bottom = (int)snappedBottomInset();
        final int left = (int)snappedLeftInset();
        final int w = (int)getWidth() - left - right;
        final int h = (int)getHeight() - TOP - bottom;
        double width = super.getWidth();
        double height = super.getHeight();

        canvas.setLayoutX(left);
        canvas.setLayoutY(TOP);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, w, h);

            if (id.equalsIgnoreCase("Board")) {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(0, 0, width, height);
                gc.setStroke(Color.BLACK);
                double increment = (height-20)/m.TOTAL;

                for (double x = width/6; x < width; x += width/6) {
                    for (double y = 10; y < height; y += increment) {
                        gc.strokeLine(x, y, (2*width)/5, y);
                    }
                    System.out.println();
//                    if() {
                        gc.strokeLine(x, 10, x, height - 10);
//                    }
                }
            }
            else if (id.equalsIgnoreCase("PlayerInfo")) {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(0, 0, width, height);

                gc.setFont(new Font("Verdana", 20));
                gc.strokeText("Computer", 30, 30);
                gc.strokeText("Player", 30, (height/2)+30);

                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(0, height/2, width, 5);
                gc.setFill(Color.BLACK);
            }
        }

    }
}
