package shape;

public class Circle implements Shape {
    private double radius;

    @Override
    public double getPerimeter() {
        return 2*this.radius*Math.PI;
    }

    @Override
    public double getArea() {
        return this.radius*this.radius*Math.PI;
    }
}
