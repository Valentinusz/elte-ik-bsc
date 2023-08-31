public class Point {
    protected double x;
    protected double y;

    protected double previousX;
    protected double previousY;

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.previousX = this.x;
        this.x = x;
    }

    public void setY(double y) {
        this.previousY = this.y;
        this.x = y;
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.previousX = this.x;
        this.previousY = this.y;
    }

    public void move (double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void mirror(double cx, double cy) {
        this.move(2 * (cx - this.x), 2 * (cy - this.y));
    }

    public void mirror2(Point p) {
        this.move(2 * (p.x - this.x), 2 * (p.y - this.y));
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }
}
