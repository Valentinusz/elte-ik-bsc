import java.util.concurrent.ThreadLocalRandom;

public class Restaurant {
    public static final int COUNTER_COUNT = 4;
    public static final int QUEUE_LENGTH = 10;
    public static final int CUSTOMER_COUNT = 50;

    private static Restaurant instance = new Restaurant();

    public static Restaurant getInstance() {
        return instance;
    }

    // TODO: Készíts egy open nevű adattagot, ami azt tárolja, hogy az étterem nyitva van-e!

    private Counter[] counters = new Counter[COUNTER_COUNT];

    // TODO: Készíts egy thread pool-t a szálak futtatására!

    // Vedd ki a komment jelet a 4. feladathoz:
    // private AnnouncementBoard board = new AnnouncementBoard();

    public void standIntoQueue(Customer customer) throws InterruptedException {
        counters[ThreadLocalRandom.current().nextInt(COUNTER_COUNT)].enqueue(customer);
    }

    public static void main(String[] args) {
        getInstance().setup();
        getInstance().execute();
        getInstance().shutdown();
    }

    // TODO3: Készíts egy order nevű metódust, ami elindít egy Order típusú szálat! (Paraméterek: az ügyfél neve és a rendelt étel.)

    // Vedd ki a komment jelet a 4. feladathoz:
    // public AnnouncementBoard getAnnouncementBoard() {
    //    return board;
    // }

    // Vedd ki a komment jelet az alapfeladathoz:
    // public boolean isOpen() {
        // TODO: Add vissza, hogy nyitva van-e az étterem!
    // }

    private void setup() {
        // TODO: Indítsd el az összes kiszolgáló ablakot!
        // TODO4: Indítsd el a kiíró táblát!
    }

    private void execute() {
        // TODO: Indítsd el a vendégeket! Várj véletlenszerűen 0,5 és 1 másodperc közötti időt minden új vendég intítása előtt!
    }

    private void shutdown() {
        // TODO: Várj egy másodpercet zárás előtt!
        // TODO: Tiltsd le új szálak elindítását a thread pool-ban!
        // TODO: Zárd be az étteremet (open változó)!
        // TODO: Várj legfeljebb 10 másodpercet, hogy minden szál befejeződjön!
        // TODO: Állítsd le azonnal a thread pool-t!
    }
}