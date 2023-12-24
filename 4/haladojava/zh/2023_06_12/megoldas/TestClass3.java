public class TestClass3 {
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    @BinaryProperty(BinaryPropertyType.COMMUTATIVE)
    static int correct(int a, int b) {
        return a + b;
    }

    /**
     * Rossz paraméterezés ezért nem vizsgáljuk.
     */
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    static String wrongParameterType(String a, int b) {
        return a + b;
    }

    /**
     * Rossz paraméterezés ezért nem vizsgáljuk.
     */
    @BinaryProperty(BinaryPropertyType.ASSOCIATIVE)
    static int wrongParameterCount(int a, int b, int c) {
        return a + b + c;
    }
}
