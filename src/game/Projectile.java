package game;


import javafx.scene.paint.Color;

import java.util.HashMap;

public class Projectile extends Entity implements RoundHitbox {
    protected double damage;

    /**
     * @param x  start position x
     * @param y  start position y
     * @param vx constant velocity x
     * @param vy constant velocity y
     * @param damage the damage the projectile will make
     */
    public Projectile(double x, double y, double vx, double vy, double damage) {
        //Lets the projectile know the outerbounds of the game
        entityManager.setBorderAsOuterBounds(this);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.damage = damage;

        //Projectils have constant velocity
        this.ax = 0;
        this.ay = 0;
        this.size = 20;

    }

    /**
     * Draws the projectile
     */
    public void draw() {
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, size*scale, size*scale);
    }

    /**
     * Bounces the projectile if it hits an y border
     * @param y
     */
    @Override
    public void setY(double y) {
        if (isInsideBorderY(y)) this.y = y;
        else this.vy = -this.vy;
    }

    /**
     * Despawns the entity when it reaches the end of the screen
     * @param x
     */
    @Override
    public void setX(double x) {
        if (isInsideBorderX(x)) this.x = x;
        else entityManager.removeEntity(this);
    }


    public void onCollision(Entity entity) {
        entityManager.removeEntity(this);
    }

    public double getDamage() {
        return damage;
    }
}
