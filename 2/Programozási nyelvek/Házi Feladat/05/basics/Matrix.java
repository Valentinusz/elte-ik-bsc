package basics;

public class Matrix {
    private int rowCount;
    private int colCount;
    private double[] elements;

    public Matrix() {};

    //3
    public Matrix(int rowCount, int colCount, double[][] matrix) {
        this.elements = new double[rowCount*colCount];
        this.rowCount = rowCount;
        this.colCount = colCount;

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                elements[i*colCount+j] = matrix[i][j];
            }
        }
    }


    //1
    public static Matrix createNullMatrix(int rowCount, int colCount) {
        if (rowCount > 0 && colCount >0) {
            Matrix matrix = new Matrix();
            matrix.rowCount = rowCount;
            matrix.colCount = colCount;
    
            matrix.elements = new double[rowCount*colCount];
            for (int i = 0; i < rowCount * colCount; ++i) {
                matrix.elements[i] = 0;
            }
            return matrix;
        } else {
            throw new IllegalArgumentException("The size of a matrix need to positive intigers.");
        }

    }


    //2
    public static Matrix createIdentityMatrix(int n) {
        Matrix matrix = new Matrix();
        matrix.rowCount = n;
        matrix.colCount = n;
    
        matrix.elements = new double[n*n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    matrix.elements[i*n+j] = 1;
                } else {
                    matrix.elements[i*n+j] = 0;
                }
            }
        }
        return matrix;
    }

    //4
    public void transposeMatrix() {
        double[] newElements = new double[this.rowCount * this.colCount];
        for (int i = 0; i < this.colCount; ++i) {
            for (int j = 0; j < this.rowCount; ++j) {
                newElements[i*this.rowCount+j] = elements[j*this.colCount+i];
            }
        }

        int r = this.rowCount;
        this.rowCount = this.colCount;
        this.colCount = r;
        this.elements = newElements;
    }



    //5
    public void addMatrices(Matrix matrix) {
        if (this.rowCount == matrix.rowCount && this.colCount == matrix.colCount) {
            for (int i = 0; i < this.elements.length; ++i) {
                this.elements[i] += matrix.elements[i];
            }
        } else {
            throw new IllegalArgumentException("The two matrices need to be mathcing in size.");
        }
    }

    public void substractMatrices(Matrix matrix) {
        if (this.rowCount == matrix.rowCount && this.colCount == matrix.colCount) {
            for (int i = 0; i < this.elements.length; ++i) {
                this.elements[i] -= matrix.elements[i];
            }
        } else {
            throw new IllegalArgumentException("The two matrices need to be mathcing in size.");
        }
    }

    //6
    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.rowCount; ++i) {
            sb.append("[");
            for (int j = 0; j < this.colCount; ++j) {
                sb.append(this.elements[i*this.colCount+j]);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("],");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }
}