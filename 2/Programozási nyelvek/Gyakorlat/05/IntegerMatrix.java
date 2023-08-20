/**
 * @author boda
 * @version 1.0
 * @since 2022
 * */

public class IntegerMatrix {
    private int rowNum;
    private int colNum;
    private int[] linearData;

    /**
     * @param rowNum count of rows in the matrix
     * @param colNum count of columns in the matrix
     * @param linearData linear representation of the matrix
     */


    public IntegerMatrix(int rowNum, int colNum, int[] linearData) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.linearData = linearData; // {1,2,3,4,5,6}
    }


    /**
     * @return returns a string concatened from the elements of the linearly represented matrix where elements are seperated by , and rows by;
     */


    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rowNum; ++i) {
            for (int j = 0; j < this.colNum; ++j) {
                sb.append(this.linearData[i*this.colNum+j]);
                if (j == this.colNum - 1) {
                    if (i != this.rowNum -1 ) {
                        sb.append(";");
                    } 
                } else {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }
}
