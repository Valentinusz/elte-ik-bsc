package point;

/**
 * Class representing 2D points
 */
public class Point {
    /**
     * x coordinate of the point
     */
    protected double x;

    /**
     * y coordinate of center point of the shape
     */
    protected double y;

    /**
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
