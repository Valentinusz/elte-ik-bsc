package my_package;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Zeroth {
    public static void main(String[] args) throws InterruptedException {
        // Dátumkezelés Java-ban

        // System
        long start = System.currentTimeMillis();
        System.out.println("Started at: "+start);
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed: "+(end-start));

        // Date
        Date startDate = new Date();
        System.out.println("Start date: "+startDate);
        Thread.sleep(200);
        Date endDate = new Date();
        // getTime() megadja longként
        System.out.println("diff date: "+(endDate.getTime()-startDate.getTime()));

        // Instant
        Instant startInstant = Instant.now();
        System.out.println("Start instant: " + startInstant);
        Thread.sleep(200);
        Instant endInstant = Instant.now();
        // Duration osztály biztosít műveleteket az idővel való számoláshoz
        System.out.println("Diff duration: " + Duration.between(startInstant, endInstant));
    }
}
