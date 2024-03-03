package game;

import javafx.scene.paint.Color;
import sounds.SoundEffects;

/**
 * Type of Player
 */
public class Heavy extends Player implements RectHitbox {
    private double chargeTimer = 0;
    private double chargeTime = 1;
    private double charge = 0;

    /**
     * @param x spawn location x
     * @param y spawn location y
     * @param direction shooting direction
     * @param keySchema controlls for player
     */
    Heavy(Double x, Double y, Direction direction, KeySchema keySchema) {
        super(x, y, direction, keySchema);
        projectileCapacity = 6;
        projectileReloadTimer = 1;
    }

    /**
     * Updates the player
     * velocity, location and timers.
     * @param deltaTime
     */
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateCharge(deltaTime);
    }

    /**
     * Method which takes care of using the special ability
     * @param deltaTime
     */
    public void updateCharge(double deltaTime) {
        if (hold) {
            //Slows speed while charging
            vMax = 50;
            //Makes sure the ammo doesnt reload while charging
            if (chargeTimer > chargeTime * 0.5) projectileReloadTimer = 1;
            chargeTimer += deltaTime;
            System.out.println(chargeTimer);
            if (chargeTimer >= chargeTime) {
                chargeTimer = 0;
                if (projectiles > 0) {
                    projectiles--;
                    charge++;
                }
            }
            System.out.println(charge);
        } else {
            charge = 0;
            chargeTimer = 0;
            vMax = 200;
        }
    }

    /**
     * Draws the player
     */
    @Override
    public void draw() {
        double stroke = getSize() * 0.07;
        gc.setFill(new Color(1, 0, 0, ((hp + 3) / 8)));
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(stroke);
        gc.fillRect(x, y, getSize(), getSize());
        gc.strokeRect(x + stroke / 2, y + stroke / 2, getSize() - stroke, getSize() - stroke);
        gc.setFill(Color.WHITE);
        double projectilesSize = getSize() / 4;
        for (int i = 0; i < projectiles; i++) {
            if (i < 3) {
                gc.fillRect(x + ((getSize() - projectilesSize) / 2) * i, getY() - getSize() * 0.75 - projectilesSize / 2, getSize() / 4, getSize() / 4);
            } else {
                gc.fillRect(x + ((getSize() - projectilesSize) / 2) * (i - 3), getY() + getSize() * 0.75 - projectilesSize / 2, getSize() / 4, getSize() / 4);
            }

        }
    }

    /**
     * Should be used when the player wants to shoot
     * Also takes care of the special ability charge
     * @param key true if key is pressed
     */
    @Override
    protected void prepareShoot(Boolean key) {
        if (key) {
            //Makes sure you can't hold in the button
            hold = true;
        } else if (hold) {
            if (charge > 0) {
                shootCharge();
            } else shoot();
            hold = false;
        }
    }

    /**
     * Shoots the charge
     */
    public void shootCharge() {
        SoundEffects.play(SoundEffects.getFire1());
        new HeavyProjectile(getX() + (getSize() * 1.5 * direction.getX()), getY() + (getSize() * 1.5 * direction.getY()), (projectileV * direction.getX()) + vx, vy + (projectileV * direction.getY()), charge);
        charge = 0;
        chargeTimer = 0;
        projectileReloadTimer = 1;
    }

}
