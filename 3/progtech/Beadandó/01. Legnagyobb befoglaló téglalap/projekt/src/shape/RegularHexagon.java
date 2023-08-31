package shape;

/**
 * Class representing regular hexagons with a side perpendicular to the x-axis on a 2D plane with their side-length and center.
 */
public class RegularHexagon extends AbstractRegularShape {
    /**
     * @param x x coordinate of center point of the shape
     * @param y y coordinate of center point of the shape
     * @param sideLength sideLength of the shape, must be greater than 0
     * @throws IllegalArgumentException if sideLength is less than 0
     */
    public RegularHexagon(int x, int y, double sideLength) {
        super(x,y,sideLength);
    }

    /**
     * @return area of the bounding rectangle of the shape calculated with the formula sideLength^2 * 2 * Math.sqrt(3)
     */
    @Override
    public double boundingArea() {
        return 2 * this.sideLength * this.sideLength * Math.sqrt(3);
    }

    @Override
    public String toString() {
        return "RegularHexagon(" + super.toString() + ")";
    }
}
