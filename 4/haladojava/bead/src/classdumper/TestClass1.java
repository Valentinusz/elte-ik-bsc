package classdumper;

import java.io.Serializable;

public class TestClass1 extends Thread implements Runnable, Serializable {
    private static final int CONST = 1;
    volatile transient boolean flag;
    protected Object obj;
    public int n = 2;

    public static void printConst() {
        System.out.println(CONST);
    }
}
