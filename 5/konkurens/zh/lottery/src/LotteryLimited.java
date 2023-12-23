import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LotteryLimited {
    public static final int THREAD_COUNT = 10;
    public static final HashSet<Integer> WINNING_NUMBERS = new HashSet<>(List.of(13, 42, 444, 999, 2_023, 2_024, 9_999));
    public static final int[] HITS = new int[THREAD_COUNT];

    public static final ConcurrentMap<Integer, Integer> availableGuesses = new ConcurrentHashMap<>();

    public static final AtomicBoolean isOver = new AtomicBoolean(false);

    public static void main(String[] args) {
        for (var number: WINNING_NUMBERS) {
            availableGuesses.put(number, 1);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        ArrayList<Callable<Integer>> tasks = new ArrayList<>(THREAD_COUNT);

        // 3
        AtomicInteger totalGuesses = new AtomicInteger(0);
        Logger logger = new Logger();
        ExecutorService loggerExecutor = Executors.newSingleThreadExecutor();
        loggerExecutor.submit(logger);

        // 4
        ExecutorService terminationCheckerExecutor = Executors.newSingleThreadExecutor();

        terminationCheckerExecutor.submit(() -> {
            while (!isOver.get()) {
                try {
                    Thread.sleep(234);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (availableGuesses.values().stream().allMatch(value -> value == 0)) {
                    System.out.println("Simulation stuck. Exiting...");
                    System.exit(1);
                }
            }
        });

        terminationCheckerExecutor.shutdown();
        // \4

        for (int i = 0; i < THREAD_COUNT; i++) {
            int threadIndex = i;
            tasks.add(() -> {
                Thread.currentThread().setName(String.valueOf(threadIndex));

                int correctGuessCount = 0;
                int sleepAmount;
                int guess;

                while(!isOver.get() && totalGuesses.get() < 50_000) {
                    sleepAmount = 1;
                    guess = ThreadLocalRandom.current().nextInt(1, 10_000);

                    if (WINNING_NUMBERS.contains(guess)) {
                        if (availableGuesses.get(guess) > 0) {
                            logger.log("[" + threadIndex + "] Got a hit: " + guess);

                            synchronized (HITS) {
                                HITS[threadIndex]++;
                                correctGuessCount++;
                                availableGuesses.computeIfPresent(guess, (k, v) -> v-1);
                            }

                            if (correctGuessCount == WINNING_NUMBERS.size()) {
                                isOver.set(true);
                                return threadIndex;
                            }

                            sleepAmount = ThreadLocalRandom.current().nextInt(100, 300);
                        } else {
                            logger.log("[" + threadIndex + "] Number already taken: " + guess);
                        }
                    }

                    totalGuesses.incrementAndGet();

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

        try {
            loggerExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            loggerExecutor.shutdownNow();
        }

        Optional<Integer> winner = results.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).filter(Objects::nonNull).findFirst();

        if (winner.isPresent()) {
            System.out.println("We got a winner: " + winner + ". Total guesses: " + totalGuesses.get());
        } else {
            System.out.println("No winners. Total guesses: " + totalGuesses.get());
        }
    }
}
