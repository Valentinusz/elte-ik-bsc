import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class DataMover2 {
    public static AtomicInteger arrivalCount = new AtomicInteger(0);
    public static AtomicInteger totalSent = new AtomicInteger(0);
    public static AtomicInteger totalArrived = new AtomicInteger(0);
    public static ExecutorService pool;
    public static List<BlockingQueue<Integer>> queues = new ArrayList<>();
    public static List<Future<DataMover2Result>> moverResults = new ArrayList<>();
    public static List<Integer> discards = new LinkedList<>();
    public static int arrivalLimit;

    public static class DataMover2Task implements Callable<DataMover2Result> {
        private final int id;
        private DataMover2Result result = new DataMover2Result();
        private final int waitingTime;

        public DataMover2Task(int id, int waitingTime) {
            this.id = id;
            this.waitingTime = waitingTime;
        }

        private String makeStatusMessagePart(int arrivalCount) {
            return "total " +  arrivalCount + "/" + arrivalLimit + " | #" + this.id;
        }

        @Override
        public DataMover2Result call() throws Exception {
            while (arrivalCount.get() < arrivalLimit) {
                sendValue();

                long waitFor = ThreadLocalRandom.current().nextLong(300L, 1_000L);

                try {
                    Integer x = queues.get(this.id).poll(waitFor, TimeUnit.MILLISECONDS);

                    if (x == null) {
                        System.out.println(makeStatusMessagePart(arrivalCount.get()) + " got nothing...");
                    } else {
                        if (x % queues.size() == this.id) {
                            acceptValue(x);
                        } else {
                            forwardValue(x);
                        }
                    }

                    Thread.sleep(waitingTime);
                } catch (InterruptedException exception) {
                    System.out.println("Task #" + this.id + " got interrupted!");
                    Thread.currentThread().interrupt();
                }
            }
            return this.result;
        }

        public void sendValue() throws InterruptedException {
            int newValue = ThreadLocalRandom.current().nextInt(0, 10_000);

            queues.get(this.id).put(newValue);

            totalSent.addAndGet(newValue);

            System.out.println(makeStatusMessagePart(arrivalCount.get()) + " sends " + newValue);
        }

        public void acceptValue(int x) {
            System.out.println(makeStatusMessagePart(arrivalCount.incrementAndGet()) + " got " + x);
            this.result = this.result.incrementCount().addToData(x);
        }

        public void forwardValue(int x) throws InterruptedException {
            int forwardToIndex = (this.id + 1) % queues.size();

            queues.get(forwardToIndex).put(x - 1);

            this.result = this.result.incrementForwarded();

            System.out.println(
                    makeStatusMessagePart(arrivalCount.get()) + " forwards " + x + " [" + forwardToIndex + "]"
            );
        }
    }

    public static int[] parseArgs(String[] args) {
        if (args.length == 0) {
            args = new String[]{"123", "111", "256", "404"};
        }

        int[] waitingTimes = new int[args.length];

        try {
            for (int i = 0; i < args.length; i++) {
                waitingTimes[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Hibás bemenet: " + e.getMessage());
            System.exit(1);
        }

        return waitingTimes;
    }

    public static void main(String[] args) {
        int[] waitingTimes = parseArgs(args);

        int threadCount = waitingTimes.length;

        arrivalLimit = 5 * threadCount;

        for (int i = 0; i < threadCount; i++) {
            queues.add(new LinkedBlockingDeque<>());
        }

        pool = Executors.newFixedThreadPool(100);

        try {
            // n task létrehozása
            moverResults = pool.invokeAll(
                    IntStream.range(0, threadCount).mapToObj(id -> new DataMover2Task(id, waitingTimes[id])).toList()
            );

            // új taskok letiltása
            pool.shutdown();

            // 30 mp után timeout
            if (pool.awaitTermination(30, TimeUnit.SECONDS)) {
                // at this point all Futures inside the results list can safely be unpacked

                // sum remaining values inside the BlockingQueues
                discards = queues.stream()
                                 .map(Collection::stream)
                                 .map(queue -> queue.reduce(0, Integer::sum))
                                 .toList();

                // get total sum of discarded
                int discarded = discards.stream()
                                        .mapToInt(Integer::intValue)
                                        .sum();

                totalArrived.set(
                        moverResults.stream()
                                    .map(Future::resultNow)
                                    .mapToInt(result -> result.data() + result.forwarded())
                                    .sum()
                );

                System.out.println("discarded " + discards +  " = " + discarded);

                int sent = totalSent.get();
                int arrived = totalArrived.get();
                int got = arrived + discarded;

                if (sent == arrived + discarded) {
                    System.out.println(
                            "sent " + sent + " ===  got " + got + " = " + arrived + " discarded " + discarded
                    );
                } else {
                    System.out.println(
                            "WRONG sent " + sent + " !==  got " + got + " = " + arrived + " discarded " + discarded
                    );
                }
            }
        } catch (InterruptedException exception) {
            System.out.println("Task timed out!");
            Thread.currentThread().interrupt();
        }
    }
}
