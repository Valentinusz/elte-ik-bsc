import java.util.Arrays;

import util.IntVector;

class IntVectorDemo {
    public static void main(String[] args) {
        int[] ns = {1,2,3};
        IntVector v = new IntVector(ns);
        IntVector v2 = new IntVector(ns);

        System.out.println(Arrays.toString(ns));
        System.out.println(v.toString());
        System.out.println(v2.toString());

        System.out.println("v.add(1);");
        v.add(1);
        System.out.println(v.toString());
        System.out.println(v2.toString());

        System.out.println("ns[0] = 10;");
        ns[0] = 10;             
        System.out.println(v.toString());
        System.out.println(v2.toString());
    }
}