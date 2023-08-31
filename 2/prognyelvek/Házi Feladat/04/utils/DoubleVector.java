package utils;

public class DoubleVector {
    private double[] vector;

    public DoubleVector(double[] array) {
        vector = new double[array.length];
        for (int i = 0; i < array.length; ++i) {
            vector[i] = array[i];
        }
    }

    public static double dotProduct(DoubleVector v1, DoubleVector v2) {
        if (v1.vector.length == v2.vector.length) {
            double sum = 0;
            for (int i=0; i < v1.vector.length; ++i) {
                sum += v1.vector[i] * v2.vector[i];
            }
            return sum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static DoubleVector add(DoubleVector v1, DoubleVector v2) {
        if (v1.vector.length == v2.vector.length) {
            double[] newVector = new double[v1.vector.length];
            for (int i=0; i < v1.vector.length; ++i) {
                newVector[i] = v1.vector[i] + v2.vector[i];
            }
            return new DoubleVector(newVector);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static DoubleVector substract(DoubleVector v1, DoubleVector v2) {
        if (v1.vector.length == v2.vector.length) {
            double[] newVector = new double[v1.vector.length];
            for (int i=0; i < v1.vector.length; ++i) {
                newVector[i] = v1.vector[i] - v2.vector[i];
            }
            return new DoubleVector(newVector);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static DoubleVector multiply(double scalar, DoubleVector v) {
        double[] newVector = new double[v.vector.length];
        for (int i=0; i < v.vector.length; ++i) {
            newVector[i] = scalar * v.vector[i];
        }
        return new DoubleVector(newVector);
    }

    public String toString() {
        String result = "";
        for (int i=0; i < this.vector.length-1; ++i) {
            result += this.vector[i] + ", ";
        }
        result += this.vector[this.vector.length-1];
        return "(" + result + ")";
    }
}
