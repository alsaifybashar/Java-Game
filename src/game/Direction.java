package game;

/**
 * For declaring directions in a 2D plane
 */
public enum Direction {
    LEFT(-1,0), RIGHT(1,0), UP(0,-1), DOWN(0,1);
    private int x;
    private int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
