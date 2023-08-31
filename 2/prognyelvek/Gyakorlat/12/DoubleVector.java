import java.util.Arrays;

class DoubleVector
{
    double[] coords;

    public DoubleVector(double x1, double x2)
    {
        this.coords = new double[2];
        this.coords[0] = x1;
        this.coords[1] = x2;
    }

    public String toString()
    {
        return "(" + this.coords[0] + "," + this.coords[1] + ")";
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (that != null && getClass().equals(that.getClass())) {
            DoubleVector d = (DoubleVector)that;
            //return this.coords[0] == d.coords[0] && this.coords[1] == d.coords[1];
            return Arrays.equals(this.coords, d.coords);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }
}