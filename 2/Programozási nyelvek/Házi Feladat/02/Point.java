class Point {
    // adattagok láthatósága korlátozható
    // public
    // private
    // protected
    public double x;
    public double y;

    // this - aktualis példányra referencia
    Point(double x, double y) {
        this.x = x;
        this.y = y;
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