package shape;

/**
 * Abstract class representing regular shapes that have a side perpendicular with the x-axis on a 2D plane with their side-length and center.
 */
public abstract class AbstractRegularShape extends Shape {
    /**
     * sideLength of the given shape
     */
    protected double sideLength;

    /**
     * @param x x coordinate of center point of the shape
     * @param y y coordinate of center point of the shape
     * @param sideLength sideLength of the shape, must be greater than 0
     * @throws IllegalArgumentException if sideLength is less than 0
     */
    public AbstractRegularShape(int x, int y, double sideLength) {
        super(x,y);
        if (sideLength > 0) {
            this.sideLength = sideLength;
        } else {
            throw new IllegalArgumentException("sideLength must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "sideLength: " + this.sideLength + ", " + super.toString();
    }
}
