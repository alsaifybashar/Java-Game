package game;

import java.util.HashMap;

public interface RoundHitbox extends Hitbox {
    /**
     * Should return
     * [0] x
     * [1] y
     * [2] radius
     * @return
     */
    public HashMap<String, Double> getRoundHitbox();
}
