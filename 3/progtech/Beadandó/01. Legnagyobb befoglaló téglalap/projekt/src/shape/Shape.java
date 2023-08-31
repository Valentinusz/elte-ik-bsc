package shape;

import point.Point;

/**
 * Abstract class representing shapes with their center point.
 */
public abstract class Shape {
    /**
     * Center of the shape on a 2D plane.
     */
    protected Point center;

    /**
     * @param x x coordinate of center point of the shape
     * @param y y coordinate of center point of the shape
     */
    public Shape(int x, int y) {
        this.center = new Point(x,y);
    }

    /**
     * Calculates the area of the smallest bounding rectangle of the given shape.
     * @return area of the smallest bounding rectangle of shape
     */
    public abstract double boundingArea();

    @Override
    public String toString() {
        return "center: " + this.center.toString();
    }
}
