package game;
import javafx.scene.canvas.Canvas;

class ResizableCanvas extends Canvas {

    /**
     * Standard canvas is not resizable, this one is.
     * @return
     */
    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
