public class TestClass2 {
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    @BinaryProperty(BinaryPropertyType.COMMUTATIVE)
    static int correct(int a, int b) {
        return a + b;
    }

    /**
     * Van annotáció és elrontja.
     */
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    static int wrongAnnotation(int a, int b) {
        return a / b;
    }

    /**
     * Nincs annotáció ezért nem rontja el.
     */
    static int f4(int a, int b) {
        return a / b;
    }
}
