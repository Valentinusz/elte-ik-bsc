package shape;

public class Square implements Shape {
    private double a;

    @Override
    public double getPerimeter() {
        return 4*a;
    }

    @Override
    public double getArea() {
        return a*a;
    }
}
