class Circle {
    double x;
    double y;
    double radius;

    Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void enlarge(double f) {
        this.radius *= f;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

}