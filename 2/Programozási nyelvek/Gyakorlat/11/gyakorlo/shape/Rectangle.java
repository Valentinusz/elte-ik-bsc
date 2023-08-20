package shape;

public class Rectangle implements Shape {
    private double a;
    private double b;

    @Override
    public double getPerimeter() {
        return 2*(a+b);
    }

    @Override
    public double getArea() {
        return a*b;
    }
}
