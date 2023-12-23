import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

record PolarCoordinate(double distance, double angle) {
    public PolarCoordinate getMaxByAngle(PolarCoordinate other) {
        return other.angle > this.angle ? other : this;
    }
}

public class Main {
    public static void main(String[] args) {
        // Csomagoló osztály, ami atomi műveleteket biztosít
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while(atomicInteger.get() < 20_000) {
//                    int j = atomicInteger.get();
//                    ++j;
//                    atomicInteger.set(j);
                    // ehelyett incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.incrementAndGet());
                    // az inputon nem sorban tűnik, de a sout + string összefűzés rontja el
                    // get
                    // set
                    // incrementAndGet
                    // getAndIncrement
                    // addAndGet
                    // getAndAdd
                    // decrementAndGet
                    // accummulateAndGet
                    // itt a=atomicInteger értéke, b=3
                    atomicInteger.accumulateAndGet(3, (a, b) -> {
                        if (a % 2 == 0) {
                            return 2 * a + b;
                        } else {
                            return a-b;
                        }
                    });
                    // updateAndGet
                    atomicInteger.updateAndGet(a -> {
                        if (a % 2 == 0) {
                            return 2 * a + 3;
                        } else {
                            return a-3;
                        }
                    });
                }
            }).start();
        }

        AtomicLong atomicLong = new AtomicLong(0);
        atomicLong.compareAndSet(0L, 2L);

        // ha nem adunk semmit null a kezdőérték
        AtomicReference<PolarCoordinate> polarCoordinateAtomicReference = new AtomicReference<>(new PolarCoordinate(0 , 0));

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                Random r = ThreadLocalRandom.current();
                for (int j = 0; j < 1_000; j++) {
                    PolarCoordinate c = new PolarCoordinate(r.nextDouble(), r.nextDouble() * 360);
//                    PolarCoordinate now = null;
//                    PolarCoordinate save = null;
//
//                    // dowhile azért kell hogy ne akadjanak össze a szálak
//                    // ún. aktív várakozás
//                    do {
//                        now = polarCoordinateAtomicReference.get();
//                        save = c.getMaxByAngle(now);
//                    } while (polarCoordinateAtomicReference.compareAndSet(now, save));
                    // egyszerűbb megoldás: ugyan ezt az ismétlést megírja helyettünk
                    System.out.println(
                            polarCoordinateAtomicReference.accumulateAndGet(c, PolarCoordinate::getMaxByAngle)
                    );
                }
            }).start();
        }

        System.out.println("Hello world!");
    }
}