package polyhedra;

public abstract class Prism {
    protected double height;

    public abstract double baseArea();

    public  double volume() {
        return baseArea() * height;
    }

}