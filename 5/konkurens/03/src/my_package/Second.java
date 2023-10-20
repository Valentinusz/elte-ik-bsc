package my_package;

import java.util.Random;
import java.util.stream.IntStream;

public class Second {
    public static void main(String[] args) {
        Random random = new Random();
        int[] a = IntStream.generate(() -> random.nextInt(0, 10)).limit(10).toArray();

        for (int i = 0; i < a.length; i++) {
            // az i változó, nem effectively final, ezért a lambda nem tudja lekötni
            int j = i;

            new Thread(() -> {
                if (a[j] == j) {
                    System.out.println("Heuréka " +  "a[" + j + "]" + "==" + j);
                } else {
                    System.out.println("Sajnos " + "a[" + j + "]" + "==" + a[j]);
                }
            }).start();
        }
    }
}
