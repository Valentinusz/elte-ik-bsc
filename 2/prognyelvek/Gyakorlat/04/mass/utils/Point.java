package mass.utils;

public class Point {
    public int id;
    public double x;
    public double y;

    public static int id_count = 0;

    public Point(double x, double y) {
        this.id = ++id_count;
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

    public String toString() {
        String a = this.id + ": (" + this.x + "," + this.y + ")";
        return a;
    }

    public static Point getS(Point[] points) {
        double x = 0;
        double y = 0;

        for (int i=0; i < 3; ++i) {
            x += points[i].x;
            y += points[i].y;
        }

        Point s = new Point(x/3,y/3);

        return s;
    }
}