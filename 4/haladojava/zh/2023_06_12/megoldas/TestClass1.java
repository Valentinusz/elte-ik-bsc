public class TestClass1 {
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    @BinaryProperty(BinaryPropertyType.COMMUTATIVE)
    static int correct(int a, int b) {
        return a + b;
    }

    static int noAnnotation(int a, int b) {
        return a - b;
    }

    @BinaryProperty(BinaryPropertyType.NONASSOCIATIVE)
    static int notCheckedAnnotation(int a, int b) {
        return a - b;
    }

    static int f2(int a, int b) {
        return a - b;
    }
}
