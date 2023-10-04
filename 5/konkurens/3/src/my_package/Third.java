package my_package;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Third {
    static class Helper {
        // volatile változók nem kerülnek cache-elésre
        // ezért, ha az egyik szál megváltoztatja a másik szálban biztos érzékelhető lesz a változás
        // volatile nélkül Reader végtelen ciklusba kerülne
        public static volatile boolean B = false;
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            Instant start = Instant.now();

            while (!Helper.B) {

            }
            System.out.println("About to stop");
            System.out.println("Time taken: "+(Duration.between(start, Instant.now())));
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            Instant plannedStop = Instant.now().plus(Duration.of(5, ChronoUnit.SECONDS));

            // várakozás sleep nélkül
            while (!Helper.B && plannedStop.isAfter(Instant.now())) {}
            Helper.B = true;
            System.out.println("Changed");
        }
    }

    public static void main(String[] args) {
        new Thread(new Reader()).start();
        new Thread(new Writer()).start();
    }
}
