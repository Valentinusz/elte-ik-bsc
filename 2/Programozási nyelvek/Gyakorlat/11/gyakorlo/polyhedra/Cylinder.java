package polyhedra;
public class Cylinder extends Prism {
    private double radius;

    @Override
    public double baseArea() {
        return this.radius * this.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cylinder : (h=");
        sb.append(this.height);
        sb.append(" , r=");
        sb.append(this.radius);
        sb.append(")");
        return sb.toString();
    }
}
