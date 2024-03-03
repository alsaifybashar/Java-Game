package game;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.HashMap;


abstract public class Entity implements Serializable {
    protected double x, y, vx, vy, ax, ay, vMax, size, width, height;
    protected static double scale = 1;
    protected Double[] border = new Double[4];
    protected static GraphicsContext gc;
    protected static EntityManager entityManager;

    /**
     * Adds itself to the entityManager, thus Entity can not exist without an EntityManager
     */
    public Entity() {
        entityManager.addEntity(this);
    }

    /**
     * All entities must implement draw where GraphicsContext is used.
     * This method should ONLY draw
     */
    abstract void draw();

    /**
     * Sets the EntityManager of all entities.
     * Assumptions that there will only be one EntityManager for all entities
     *
     * @param em
     */
    public static void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    /**
     * Sets GraphicsContext from canvas
     *
     * @param graphicsContext
     */
    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        gc = graphicsContext;
    }

    /**
     * Updates velocity and location of Entity.
     *
     * @param deltaTime
     */
    public void update(double deltaTime) {
        double vx = this.vx + ax * deltaTime;
        double vy = this.vy + ay * deltaTime;

        //Limit velocity
        if (Math.abs(vx) < vMax) {
            this.vx = vx;
        }
        if (Math.abs(vy) < vMax) {
            this.vy = vy;
        }

        double x = this.x + vx * deltaTime;
        double y = this.y + vy * deltaTime;

        setX(x);
        setY(y);
    }

    /**
     * Checks if entity is inside border in x
     *
     * @param x
     * @return true if inside
     */
    public boolean isInsideBorderX(double x) {
        return (x <= border[2] - getSize() && x >= border[0]);
    }

    /**
     * Checks if entity is inside border in y
     *
     * @param y
     * @return true if inside
     */
    protected boolean isInsideBorderY(double y) {
        return (y < border[3] - getSize() && y > border[1]);
    }

    /**
     * Sets border size
     *
     * @param xTop
     * @param yTop
     * @param xBottom
     * @param yBottom
     */
    public void setBorder(double xTop, double yTop, double xBottom, double yBottom) {
        border[0] = xTop;
        border[1] = yTop;
        border[2] = xBottom;
        border[3] = yBottom;
    }



    /**
     * @param x
     */
    protected void setX(double x) {
        this.x = x;
    }

    /**
     * @param y
     */
    protected void setY(double y) {
        this.y = y;
    }

    /**
     * @return size of Entity
     */
    public double getSize() {
        return size * scale;
    }

    /**
     * @return center x of Entity
     */
    public double getX() {
        return x + (size * scale) / 2;
    }

    /**
     * @return center x of Entity
     */
    public double getY() {
        return y + getSize() / 2;
    }



    public static void setScale(double scaleTmp) {
        scale = scaleTmp;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Double> getRectHitbox() {
        HashMap<String, Double> hitbox = new HashMap<>();
        hitbox.put("x",x);
        hitbox.put("y",y);
        hitbox.put("width",width*scale);
        hitbox.put("height",height*scale);
        return hitbox;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Double> getRoundHitbox() {
        HashMap<String, Double> hitbox = new HashMap<>();
        hitbox.put("x",x);
        hitbox.put("y",y);
        hitbox.put("radius",getSize()/2);
        return hitbox;
    }

    /**
     * This is my super cool method
     *
     * @param hitbox
     * @return true if intersected with another hitbox
     */
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof RectHitbox && this instanceof RectHitbox) {
            var rect1 = ((RectHitbox) hitbox).getRectHitbox();
            var rect2 = getRectHitbox();
            if (rect1.get("x") < rect2.get("x") + rect2.get("width") &&
                    rect1.get("x") + rect1.get("width") > rect2.get("x") &&
                    rect1.get("y") < rect2.get("y") + rect2.get("height") &&
                    rect1.get("y") + rect1.get("height") > rect2.get("y")) {
                return true;
            }
        } else if (hitbox instanceof RoundHitbox && this instanceof RectHitbox) {
            var circle = ((RoundHitbox) hitbox).getRoundHitbox();
            var rect = this.getRectHitbox();

            double circleDistanceX = Math.abs(circle.get("x") - rect.get("x"));
            double circleDistanceY = Math.abs(circle.get("y") - rect.get("y"));

            if (circleDistanceX > (rect.get("width") / 2 + circle.get("radius"))) {
                return false;
            }
            if (circleDistanceY > (rect.get("height") / 2 + circle.get("radius"))) {
                return false;
            }

            if (circleDistanceX <= (rect.get("width") / 2)) {
                return true;
            }
            if (circleDistanceY <= (rect.get("height") / 2)) {
                return true;
            }

            double cornerDistance_sq = Math.pow((circleDistanceX - rect.get("width") / 2), 2) +
                    Math.pow((circleDistanceY - rect.get("height") / 2), 2);

            return (cornerDistance_sq <= (Math.pow(circle.get("radius"), 2)));
        } else if (hitbox instanceof RoundHitbox && this instanceof RoundHitbox) {
            var circle1 = ((RoundHitbox) hitbox).getRoundHitbox();
            var circle2 = this.getRoundHitbox();

            double dx = circle1.get("x") - circle2.get("x");
            double dy = circle1.get("y") - circle2.get("y");
            var distance = Math.sqrt(dx * dx + dy * dy);

            return (distance < circle1.get("radius") + circle2.get("radius"));
        }
        return false;
    }

}
