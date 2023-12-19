import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Restaurant {
    public static final int COUNTER_COUNT = 4;
    public static final int QUEUE_LENGTH = 10;
    public static final int CUSTOMER_COUNT = 50;

    private static Restaurant instance = new Restaurant();

    public static Restaurant getInstance() {
        return instance;
    }

    // Készíts egy open nevű adattagot, ami azt tárolja, hogy az étterem nyitva van-e!
    private final AtomicBoolean open = new AtomicBoolean(true);

    private final Counter[] counters = new Counter[COUNTER_COUNT];

    // Készíts egy thread pool-t a szálak futtatására!
    private final ExecutorService executorService = Executors.newFixedThreadPool(
            CUSTOMER_COUNT + COUNTER_COUNT + 1
    );

    // Vedd ki a komment jelet a 4. feladathoz:
     private AnnouncementBoard board = new AnnouncementBoard();

    public void standIntoQueue(Customer customer) throws InterruptedException {
        counters[ThreadLocalRandom.current().nextInt(COUNTER_COUNT)].enqueue(customer);
    }

    public static void main(String[] args) {
        getInstance().setup();
        getInstance().execute();
        getInstance().shutdown();
    }

    // Készíts egy order nevű metódust, ami elindít egy Order típusú szálat! (Paraméterek: az ügyfél neve és a rendelt étel.)
    public Future<String> order(String customer, String food) {
        return executorService.submit(new Order(customer, food));
    }

    // Vedd ki a komment jelet a 4. feladathoz:
     public AnnouncementBoard getAnnouncementBoard() {
        return board;
     }

     public boolean isOpen() {
        // Add vissza, hogy nyitva van-e az étterem!
         return open.get();
     }

    private void setup() {
        // Indítsd el az összes kiszolgáló ablakot!
        for (int i = 0; i < COUNTER_COUNT; i++) {
            counters[i] = new Counter();
            executorService.submit(counters[i]);
        }

        // Indítsd el a kiíró táblát!
        executorService.submit(board);
    }

    private void execute() {
        // Indítsd el a vendégeket!
        for (int i = 0; i < CUSTOMER_COUNT; i++) {
            executorService.submit(new Customer());

            // Várj véletlenszerűen 0,5 és 1 másodperc közötti időt minden új vendég intítása előtt!
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void shutdown() {
        // Várj egy másodpercet zárás előtt!
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Tiltsd le új szálak elindítását a thread pool-ban!
        executorService.shutdown();

        // Zárd be az étteremet (open változó)!
        open.set(false);

        // Várj legfeljebb 10 másodpercet, hogy minden szál befejeződjön!
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Állítsd le azonnal a thread pool-t!
        executorService.shutdownNow();
    }
}
