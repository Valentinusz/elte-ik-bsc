import basics.Matrix;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create nullmatrix:");
        
        Matrix m1 = Matrix.createNullMatrix(5, 5);
        System.out.println(m1.toString());

        System.out.println("\nNullmatrix with invalid arguments:");
        try {
            Matrix mBadSize = Matrix.createNullMatrix(-2, 5);
        } catch (Exception IllegalArgumentException) {
            System.out.println(IllegalArgumentException.getMessage());
        }

        System.out.println("\nCreate identity matrix:");
        Matrix m2 = Matrix.createIdentityMatrix(3);
        System.out.println(m2.toString());

        System.out.println("\nTranspose identity matrix (shouldn't change):");
        m2.transposeMatrix();
        System.out.println(m2.toString());

        double[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

        System.out.println("\nCreate \"matrix\" from matrix:");
        Matrix m3 = new Matrix(3,3,matrix);
        System.out.println(m3.toString());

        System.out.println("\nTranspose m3:");
        m3.transposeMatrix();
        System.out.println(m3.toString());

        System.out.println("\nTranspose m3 back:");
        m3.transposeMatrix();
        System.out.println(m3.toString());

        System.out.println("\nAdding different size matrices:");
        try {
            m3.addMatrices(m1);
        } catch (Exception IllegalArgumentException) {
            System.out.println(IllegalArgumentException.getMessage());
        }

        Matrix m4 = new Matrix(3,3,matrix);
        m4.transposeMatrix();
        System.out.println("\nm4:");
        System.out.println(m4.toString());

        System.out.println("\nm3+m4:");
        m3.addMatrices(m4);
        System.out.println(m3.toString());

        System.out.println("\nm3-m2:");
        m3.substractMatrices(m2);
        System.out.println(m3.toString());

        System.out.println("\nCreate 2 by 3 matrix:");
        double[][] matrix5 = {{1,2,3},{4,5,6}};
        Matrix m5 = new Matrix(2,3,matrix5);

        System.out.println(m5.toString());

        System.out.println("\nTranspose 2 by 3 matrix (result should be 3 by 2):");
        m5.transposeMatrix();
        System.out.println(m5.toString());
    }
}
