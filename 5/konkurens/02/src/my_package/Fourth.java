package my_package;

import java.util.Random;

public class Fourth {
    public static void main(String[] args) {
        // miért kell becsomagolni tömbbe?
        // lambdába nem tudunk lokális változókat felüldefiniálni
        // röviden: lambda le akarja kötni a lokális változót, ha az nem (effectively) final nem tudja
        // ezzel a trükkel effectively final (nem final, de nem írjuk felül az értékét) az érték
        Object[] array = {null};

        Thread a = new Thread(() -> {
            while (array[0] == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Nincs itt semmi");
            }
            System.out.println("Az érték: " + array[0]);
        });

        Thread b = new Thread(() -> {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2, 6) * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            array[0] = 42;
        });

        a.start();
        b.start();
    }
}
