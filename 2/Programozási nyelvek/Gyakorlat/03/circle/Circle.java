package circle;

import circle.utils.Point;//circle.utils.Point VAGY circle.utils.* 

class Circle {
    private double x = 0;
    private double y = 0;
    private double radius;

    Circle(double x, double y, double radius) {
        if (radius > 0) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius;
    }

    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }
}