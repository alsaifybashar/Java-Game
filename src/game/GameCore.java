package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

/**
 * The games core
 */
abstract public class GameCore {
    protected GraphicsContext gc;
    protected double WIDTH, HEIGHT;
    protected Level level;

    protected HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    protected EntityManager entityManager = new EntityManager();

    /**
     *
     * @param gc canvas graphicscontext
     * @param width of canvas
     * @param height of canvas
     */
    public GameCore(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.WIDTH = width;
        this.HEIGHT = height;
        Entity.setGraphicsContext(gc);
        Entity.setEntityManager(entityManager);
        Level.setGc(gc);
    }

    /**
     * Lets the game know what keys are pressed
     * @param keys
     */
    public void setCurrentlyActiveKeys(HashMap<String, Boolean> keys) {
        this.currentlyActiveKeys = keys;
    }

    /**
     * Basic functionallity to make the game playable
     * @param elapsedTime
     */
    public void update(double elapsedTime) {
        level.draw();
        entityManager.updateAll(elapsedTime);
        entityManager.drawAll();
    }

    /**
     * Sets the width and height of the game and adjust the content on the canvas.
     * @param width
     * @param height
     */
    public void setDimensions(double width, double height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        onResize();
    }

    /**
     * When the canvas is resized, this method should be called to update the game to the new dimensions.
     */
    void onResize(){
        level.onResize(WIDTH,HEIGHT);
        entityManager.onResize(WIDTH, HEIGHT);
    }

}
