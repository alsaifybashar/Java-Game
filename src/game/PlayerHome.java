package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

public class PlayerHome extends Entity implements RectHitbox {

    PlayerHome(double width, double height) {
        System.out.println("I AM CREATED");
        this.width = width;
        this.height = height;
        x = 0;
        y = 0;
    }

    /**
     *
     */
    public void draw() {
        System.out.println("I AM DRAWN");
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, width, height);
    }

    /**
     *
     * @param entity
     */
    @Override
    public void onCollision(Entity entity){
        if(entity instanceof Enemy){
            SoundEffects.play(SoundEffects.getGameover());
            entityManager.removeEntity(this);
        }
    }

}
