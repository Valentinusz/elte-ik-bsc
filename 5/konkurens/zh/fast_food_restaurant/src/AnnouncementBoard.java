import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AnnouncementBoard extends Thread {
    // Készíts egy map-szerű adatszerkezetet, amely a vendégek neveit és az általuk rendelt ételt tartalmazza!
    private final ConcurrentMap<String, String> ready = new ConcurrentHashMap<>();

    public void announceReady(String customer, String food) {
        // Tedd be az elkészült rendelés adatait az adatszerkezetbe!
        ready.put(customer, food);
    }

    @Override
    public void run() {
        // Írd ki az összes elkészült rendelést másodpercenként, amíg az étterem nyitva van, és nem készült még
        // el az összes rendelés!
        while(Restaurant.getInstance().isOpen() && ready.size() < Restaurant.CUSTOMER_COUNT) {
            System.out.println("Elkészült rendelések: ");
            ready.forEach((customer, food) -> System.out.println("    " + customer + ": " + food));

            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
