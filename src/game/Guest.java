package game;

import javafx.scene.canvas.GraphicsContext;
import server.Multiplayer;

import java.util.HashMap;

/**
 * Gamemode where the this computer acts as client.
 * This gamemode only does rendering of EntityManager which is sent from the host
 */
public class Guest extends GameCore {
    private Multiplayer guest;

    /**
     *
     * @param gc canvas graphicscontext
     * @param width of canvas
     * @param height height of canvas
     * @param guest Must already be connected
     */
    public Guest(GraphicsContext gc, double width, double height, Multiplayer guest) {
        super(gc, width, height);
        level = Level.DUAL;
        guest.start();
        this.guest = guest;
        onResize();
    }

    /**
     * Updates the dimensions for the game
     */
    @Override
    void onResize() {
        if(entityManager != null){
            super.onResize();
        }
    }

    /**
     * Updates the game if guest is connected
     * @param elapsedTime
     */
    @Override
    public void update(double elapsedTime) {
        level.draw();

        if(guest.isConnected()){
            guest.getConnection().setUpdate(currentlyActiveKeys);
            guest.getConnection().update();
            entityManager = (EntityManager) guest.getConnection().read();
            if(entityManager != null){
                entityManager.drawAll();
            }
        }




    }
}