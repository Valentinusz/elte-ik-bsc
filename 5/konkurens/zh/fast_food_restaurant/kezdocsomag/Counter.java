public class Counter extends Thread {
    // Vedd ki a komment jelet az 1. feladathoz:
    // private static int MAX_ID = 0;
    // private int id = ++ MAX_ID;

    // TODO1: Készíts egy queue nevű sort a vendégek számára!

    @Override
    public void run() {
        // TODO1: Amíg az étterem nyitva van, és az étterem nem üres, tedd a következőket:
        // TODO1: Próbálj kivenni a sorból egy vendéget, de csak 1 másodpercig várj!
        // TODO2: Várj 0,1 másodpercet a korai ébresztés elkerülésére!
        // TODO1: Ha volt ügyfél a sorban, kérdezzük meg tőle, hogy mit szeretne rendelni:
        // TODO1: System.out.println(id + ". eladó: Jó napot! Mit tehetek Önért, kedves " + customer.getName() + "?");
        // TODO2: Ébreszd fel a vendéget!
        // TODO1: A végén írd ki a következőt:
        // TODO1: System.out.println(id + ". eladó: is closed.");
    }

    public void enqueue(Customer customer) throws InterruptedException {
        // TODO1: Tedd be a vendéget a sorba!
    }
}
