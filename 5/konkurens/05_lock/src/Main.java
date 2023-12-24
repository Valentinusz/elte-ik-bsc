import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        LockedList<Integer> list = new LockedList<>(IntStream.range(1, 5).boxed().toList());

        Runnable function = () -> {
            list.add(ThreadLocalRandom.current().nextInt(0, 100));

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(200, 300));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Stream.generate(() -> new Thread(function)).limit(3).forEach(Thread::start);

        list.forEach(System.out::println);
    }
}