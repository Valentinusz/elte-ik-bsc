import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

public class Task {
    public static void main(String[] args) {
        AtomicInteger even = new AtomicInteger();
        AtomicInteger odd = new AtomicInteger();

//        BlockingQueue<long[]> blockingQueue = new LinkedBlockingQueue<>();
//        BlockingQueue<long[]> blockingQueue = new ArrayBlockingQueue<>(100);
        // van még SynchronousQueue, ez nem tárol adatot hanem közvetlen cseréltet adatot a szálakkal
        BlockingQueue<long[]> blockingQueue = new SynchronousQueue<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while(true) {
                    long[] a = new long[1_000_000];

                    for (int j = 0; j < a.length; j++) {
                        a[j] = ThreadLocalRandom.current().nextLong();
                    }

                    try {
                        blockingQueue.put(a);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        new Thread(() -> {
            try {
                while (true) {
                    long[] a = blockingQueue.take();

                    if (LongStream.of(a).sum() % 2L == 0L) {
                        System.out.println("even: " + even.incrementAndGet());
                    } else {
                        System.out.println("count: " + even.incrementAndGet());
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
