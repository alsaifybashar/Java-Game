package game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Singleplayer gamemode where you shoot at enemies
 */
public class Invasion extends GameCore {
    private Player player;
    private double basewidth = WIDTH*0.05;
    private PlayerHome home = new PlayerHome(basewidth, HEIGHT);
    private double counter = 0;

    /**
     * @param gc
     * @param width of canvas
     * @param height of canvas
     */
    public Invasion(GraphicsContext gc, double width, double height) {
        super(gc, width, height);
        level = Level.INVASION;
    }

    /**
     * Updates game
     * @param elapsedTime
     */
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        if(player != null) player.move(currentlyActiveKeys);

        counter+= elapsedTime;
        if(counter>1) {
            counter = 0;
            new Enemy(WIDTH,Math.random()*HEIGHT,Direction.LEFT);
        }
    }

    /**
     * When resized, do this
     */
    public void onResize() {
        super.onResize();
        player.setBorder(basewidth, 0, WIDTH, HEIGHT);
    }

    /**
     * Choose player
     * @param player
     */
    public void setPlayer(Player player){
        this.player = player;
    }
}

