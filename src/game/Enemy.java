package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

/**
 * Enemy
 */
public class Enemy extends Entity implements RoundHitbox{
    double v = 50;

    /**
     * @param x spawn position
     * @param y spawn position
     * @param direction which direction it should travel
     */
    public Enemy(double x, double y, Direction direction) {
        this.x = x;
        this.y = y;
        this.vx = v*direction.getX();
        this.vy = v*direction.getY();
        this.ax = 0;
        this.ay = 0;
        this.size = 40;
    }


    /**
     * When collided, do this
     * @param entity which it collided with
     */
    @Override
    public void onCollision(Entity entity){
        SoundEffects.play(SoundEffects.getEnemeydead());
        entityManager.removeEntity(this);
    }

    /**
     * Draws the enemy
     */
    @Override
    void draw() {
        gc.setFill(Color.DARKGRAY);
        gc.fillOval(x,y,getSize(),getSize());
        double stroke = getSize()*0.07;
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.strokeOval(x+stroke/2,y+stroke/2,getSize()-stroke,getSize()-stroke);
    }
}
