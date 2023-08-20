class ReversablePoint implements Reversable {
    private double x;
    private double y;

    private double previousX;
    private double previousY;

    @Override
    public void reverse() {
        this.x = this.previousX;
        this.y = this.previousY;
    }

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

    ReversablePoint(double x, double y) {
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

    public void mirror2(ReversablePoint p) {
        this.move(2 * (p.x - this.x), 2 * (p.y - this.y));
    }

    public double distance(ReversablePoint p) {
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }
}