class Line {
    // ax + by = c
    double a;
    double b;
    double c;

    Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean contains(Point p) {
        return this.a * p.x + this.b * p.y == c;
    }

    public boolean isParellelWith(Line l) {
        // meredekségük megegyezik de c más
        return (this.a / this.b == l.a / l.b) && (this.c != l.c);
    }

    public boolean isOrthogonalTo(Line l) {
        // meredekségek egymás (-1) szeres reciprokai
        return (this.a / this.b == (-1) / (l.a / l.b));
    }
}
