package shape;

/**
 * Class representing circles on a 2D plane, with their radius and center.
 */
public class Circle extends Shape {
    /**
     * radius of the circle
     */
    protected double radius;

    /**
     * @param x x coordinate of center point of the shape
     * @param y y coordinate of center point of the shape
     * @param radius sideLength of the shape, must be greater than 0
     * @throws IllegalArgumentException if sideLength is less than 0
     */
    public Circle(int x, int y, double radius) {
        super(x,y);
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("Radius must be greater than 0!");
        }
    }

    /**
     * @return area of the bounding rectangle of the shape calculated with the formula 4*radius^2
     */
    @Override
    public double boundingArea() {
        return 4*this.radius*this.radius;
    }

    @Override
    public String toString() {
        return "Circle(radius: " + this.radius + ", " + super.toString() + ")";
    }
}
