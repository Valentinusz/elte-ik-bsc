import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

// A feladat megoldását a névtelen csomagban lévő Lottery.java fájlban, a Lottery osztályban kell megvalósítani.
public class Lottery {
    // Legyen egy konstans, osztályszintű adattag WINNING_NUMBERS néven, amely tartalmazza a nyerőszámokat:
    // 13, 42, 444, 999, 2023, 2024, 9999.
    // Ez olyan típusú adatszerkezet legyen, amire könnyű megállapítani, benne van-e egy érték vagy sem.
    public static final HashSet<Integer> WINNING_NUMBERS = new HashSet<>(List.of(13, 42, 444, 999, 2_023, 2_024, 9_999));

    // A THREAD_COUNT érték tetszőleges lehet, pl. 10.
    public static final int THREAD_COUNT = 10;

    // Készíts egy HITS nevű int tömb típusú, THREAD_COUNT elemszámú, statikus változót, amely a résztvevők (szálak)
    // találatainak számát tartja nyilván.
    public static final int[] HITS = new int[THREAD_COUNT];

    public static AtomicBoolean isOver = new AtomicBoolean(false);

    public static void main(String[] args) {
        // Indíts el THREAD_COUNT szálat. Ez az érték tetszőleges lehet, pl. 10.
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        ArrayList<Callable<Integer>> tasks = new ArrayList<>(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            // Minden szálnak legyen egy sorszáma 0 és 9 között, gondolom ezt THREAD_COUNT akart lenni, ha mégse, akkor
            // 10-el való maradékos osztással megoldható, hogy 0-9 legyen
            int threadIndex = i;
            tasks.add(() -> {
                Thread.currentThread().setName(String.valueOf(threadIndex));

                int correctGuessCount = 0;
                int sleepAmount;
                int guess;
                while(!isOver.get()) {
                    sleepAmount = 1;
                    guess = ThreadLocalRandom.current().nextInt(1, 10_000);

                    if (WINNING_NUMBERS.contains(guess)) {
                        System.out.println("[" + threadIndex + "] Got a hit: " + guess);

                        synchronized (HITS) {
                            HITS[threadIndex]++;
                            correctGuessCount++;
                        }

                        if (correctGuessCount == WINNING_NUMBERS.size()) {
                            isOver.set(true);
                            return threadIndex;
                        }

                        sleepAmount = ThreadLocalRandom.current().nextInt(100, 300);
                    }
                    try {
                        Thread.sleep(sleepAmount);
                    } catch (InterruptedException e) {
                        return null;
                    }
                }

                return null;
            });
        }

        List<Future<Integer>> results = null;
        try {
            results = executorService.invokeAll(tasks);
            executorService.shutdown();
        } catch (InterruptedException e) {
            System.exit(1);
        } finally {
            executorService.shutdownNow();
        }

        Optional<Integer> winner = results.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).filter(Objects::nonNull).findFirst();

        winner.ifPresent(integer -> System.out.println("We got a winner: " + integer));
    }
}
