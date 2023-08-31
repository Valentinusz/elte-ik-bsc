public class MyMath {
    public static double power(int base, int power) {
        /*
        if (power < 0) {
            throw new IllegalArgumentException("unsupported operation.");
        */

        double res = 1;

        if (power >= 0) {

            for (int i=0; i<power; ++i) {
                res *= base;
            }

        } else {

            for (int i=power; i<0; ++i) {
                res /= base;
            }

        }

        return res;
    }
}