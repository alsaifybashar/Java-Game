package game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Gamemode where 2 players dual eachother locally on the same computer.
 */
public class Dual extends GameCore {
    Player player1 = new Heavy(WIDTH * 0.1, HEIGHT * 0.1, Direction.RIGHT, KeySchema.WASD);
    Player player2 = new Speedo(WIDTH * 0.9, HEIGHT * 0.1, Direction.LEFT, KeySchema.ARROWS);

    /**
     * @param gc
     * @param width
     * @param height
     */
    public Dual(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        level = Level.DUAL;
    }

    /**
     * Updates the game
     * @param elapsedTime
     */
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        player1.move(currentlyActiveKeys);
        player2.move(currentlyActiveKeys);


    }

    /**
     * Updates the game for new dimensions
     */
    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH / 2, HEIGHT);
        player2.setBorder(WIDTH / 2, 0, WIDTH, HEIGHT);
    }


}
