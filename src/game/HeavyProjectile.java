package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

public class HeavyProjectile extends Projectile implements RectHitbox{


    /**
     * @param x  start position x
     * @param y  start position y
     * @param vx constant velocity x
     * @param vy constant velocity y
     * @param damage of projectile
     */
    public HeavyProjectile(double x, double y, double vx, double vy, double damage) {
        super(x, y, vx, vy, damage);
        size = 40;
        width = size;
        height = size;
    }

    /**
     * Draws the projectile
     */
    public void draw(){
        double stroke = getSize() * 0.07;
        gc.setFill(new Color(1, 0, 0, ((damage+3) / 9)));
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.fillRect(x, y, getSize(), getSize());
        gc.strokeRect(x + stroke / 2, y + stroke / 2, getSize() - stroke, getSize() - stroke);
    }

    @Override
    public void onCollision(Entity entity) {
        super.onCollision(entity);
        SoundEffects.play(SoundEffects.getEnemeydead());
    }
}
