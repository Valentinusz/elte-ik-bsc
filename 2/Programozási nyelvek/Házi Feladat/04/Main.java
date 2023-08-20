import utils.DoubleVector;

public class Main {
    public static void main(String[] args) {
        double[] a1 = {0,2,2,0};
        double[] a2 = {1,0,0,1};
        double[] a3 = {0,1,1,0};
        double[] a4 = {1,2,3};


        DoubleVector v1 = new DoubleVector(a1);
        DoubleVector v2 = new DoubleVector(a2);
        DoubleVector v3 = new DoubleVector(a3);
        DoubleVector v4 = new DoubleVector(a4);



        System.out.println("Vectors:");
        System.out.println("v1: " + v1.toString());
        System.out.println("v2: " + v2.toString());
        System.out.println("v3: " + v3.toString());
        System.out.println("v4: " + v4.toString());


        System.out.println("Addition:");
        DoubleVector v5 = DoubleVector.add(v1, v2);
        System.out.println("v1 + v2 = " + v5.toString() + " = v5");

        DoubleVector v6 = DoubleVector.add(v5, v3);
        System.out.println("v5 + v3 = " + v6.toString() + " = v6");


        DoubleVector v7;
        try {
            v7 = DoubleVector.add(v4, v6);
            System.out.println("v4 + v6 = " + v7.toString() + " = v7");
        } catch (Exception IllegalArgumentException) {
            System.out.println("Vectors need to be matching in size.");
        }


        System.out.println("Substraction:");
        v7 = DoubleVector.substract(v1, v3);
        System.out.println("v1 - v3 = " + v7.toString() + " = v7");

        DoubleVector v8 = DoubleVector.substract(v5, v7);
        System.out.println("v5 - v7 = " + v8.toString() + " = v8");

        System.out.println("Dot product:");
        System.out.println("v2 . v3 = " + DoubleVector.dotProduct(v2, v3));
        System.out.println("v5 . v6 = " + DoubleVector.dotProduct(v5, v6));


        System.out.println("Scalar multiplication:");
        DoubleVector v9 = DoubleVector.multiply(5, v4);
        System.out.println("v4 * 5 = " + v9.toString() + " = v9");

        DoubleVector v10 = DoubleVector.multiply(-2, v8);
        System.out.println("v8 * -2 = " + v10.toString() + " = v10");
    }
}