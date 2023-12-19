import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

// Az osztály implementálja a megfelelő interfészt!
// V: Kell visszatérési érték ezért Callable
public class Order implements Callable<String> {
    String customer;
    String food;

    public Order(String customer, String food) {
        this.customer = customer;
        this.food = food;
    }

    @Override
    public String call() {
        // Várjon véletlenszerűen 1 és 2 másodperc között!
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1_000, 2_000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Hirdesse ki az elkészült rendelést a hirdetőtáblán!
        Restaurant.getInstance().getAnnouncementBoard().announceReady(this.customer, this.food);

        // Adja vissza a food adattag értékét!
        return this.food;
    }
}
