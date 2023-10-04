package my_package;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Fourth {
    public static void printInfo() {
        System.out.println(Thread.currentThread().getName() + " " + Instant.now());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1200, 1500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " " + Instant.now());
    }

    public static void main(String[] args) {
        var threads = Stream.generate(() -> new Thread(Fourth::printInfo)).limit(3).toList();
        threads.forEach(Thread::run); // ha csak meghívjuk a thread run metódusát mindig az aktuális threadben fut le
        threads.forEach(Thread::start); // start indítja ténylegesen a szálat
    }
}
