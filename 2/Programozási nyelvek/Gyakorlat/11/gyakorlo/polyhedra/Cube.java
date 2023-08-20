import polyhedra.Prism;

public class Cube extends Prism {
    public Cube(double height) {
        if (height >= 0) {
            this.height = height;
        } else {
            throw new  IllegalArgumentException();
        }
    }

    @Override
    public double baseArea() {
        return height*height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cube : (h=");
        sb.append(this.height);
        sb.append(")");
        return sb.toString();
    }
}
