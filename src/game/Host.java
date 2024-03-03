package game;

import javafx.scene.canvas.GraphicsContext;
import server.Multiplayer;
import java.util.HashMap;

/**
 * Multiplayer gamemode where this gamemode is the host which is responsible for doing all the computation
 */
public class Host extends GameCore {
    private Player player1;
    private Player player2;
    private Multiplayer host;
    private HashMap<String, Boolean> currentlyActiveKeys2 = new HashMap<>();

    /**
     * @param gc
     * @param width
     * @param height
     * @param host Must already be connected
     */
    public Host(GraphicsContext gc, double width, double height, Multiplayer host) {
        super(gc, width, height);
        level = Level.DUAL;
        this.host = host;
        host.start();
        player2 = new Speedo(WIDTH*0.9, HEIGHT*0.1, Direction.LEFT, KeySchema.WASD);
        player1 = new Heavy(WIDTH*0.1, HEIGHT*0.1, Direction.RIGHT, KeySchema.WASD);
        onResize();
    }

    /**
     * Updates the game if host is connected
     * @param elapsedTime
     */
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        if(host.isConnected()){
            player1.move(currentlyActiveKeys);
            player2.move(currentlyActiveKeys2);
            host.getConnection().setUpdate(entityManager);
            host.getConnection().update();
            if(host.getConnection().read() != null){
                currentlyActiveKeys2 = (HashMap<String, Boolean>) host.getConnection().read();
            }
        }
    }

    /**
     * Resizes the game
     */
    public void onResize() {
        super.onResize();
        player1.setBorder(0, 0, WIDTH/2, HEIGHT);
        player2.setBorder(WIDTH/2, 0, WIDTH, HEIGHT);
    }
}
