import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Counter extends Thread {
    private static int MAX_ID = 0;
    private int id = ++MAX_ID;

    // Készíts egy queue nevű sort a vendégek számára!
    private final BlockingQueue<Customer> queue = new LinkedBlockingQueue<>(Restaurant.QUEUE_LENGTH);

    @Override
    public void run() {
        // Amíg az étterem nyitva van, és az étterem nem üres, tedd a következőket:
        while (Restaurant.getInstance().isOpen()) {
            Customer customer;
            try {
                // Próbálj kivenni a sorból egy vendéget, de csak 1 másodpercig várj!
                customer = queue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Ha volt ügyfél a sorban, kérdezzük meg tőle, hogy mit szeretne rendelni:
            if (customer == null) {
                continue;
            }
            System.out.println(id + ". eladó: Jó napot! Mit tehetek Önért, kedves " + customer.getName() + "?");

            // Várj 0,1 másodpercet a korai ébresztés elkerülésére!
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Ébreszd fel a vendéget!
            customer.takeOrder();

            System.out.println(id + ". eladó: is closed.");
        }

    }

    public void enqueue(Customer customer) throws InterruptedException {
        this.queue.put(customer);
    }
}
