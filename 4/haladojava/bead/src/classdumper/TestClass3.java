package classdumper;

class TestClass3 {
    private static final int CONST = 1;
    volatile transient boolean flag;
    protected Object obj;
    public int n = 2;

    public static void printConst() {
        System.out.println(CONST);
    }
}
