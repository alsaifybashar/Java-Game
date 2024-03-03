package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public enum Level {

    DUAL("dual"), INVASION("invasion");

    String mode;
    private static GraphicsContext gc;
    private double WIDTH, HEIGHT;


    /**
     * @param mode
     */
    Level(String mode) {
        this.mode= mode;
    }


    /**
     * Switching Background
     */
    public void draw(){
        switch (mode){
            case "dual":
                drawDual();
                break;
            case "invasion":
                drawInvasion();
        }
    }

    public void drawInvasion(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawDual() {
        //Main background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        //The black line to the left
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, WIDTH * 0.009, HEIGHT);
        //The black line to the right
        gc.fillRect(WIDTH * 0.991, 0, WIDTH * 0.009, HEIGHT);
        //The line in the middle
        gc.fillRect(WIDTH * 0.495, 0, WIDTH * 0.01, HEIGHT);
        //The line at the bottom
        gc.fillRect(0, HEIGHT * 0.991, WIDTH, HEIGHT * 0.9);
        gc.stroke();

    }

    public static void setGc(GraphicsContext gcTmp) {
        gc = gcTmp;
    }

    public void onResize(double width, double height) {
        this.HEIGHT = height;
        this.WIDTH = width;
    }
}