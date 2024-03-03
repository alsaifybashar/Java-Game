package game;

import java.util.HashMap;

public interface RectHitbox extends Hitbox {
    /**
     * Should return+
     * [0] x
     * [1] y
     * [2] width
     * [3] height
     * @return
     */
    public HashMap<String, Double> getRectHitbox();
}
