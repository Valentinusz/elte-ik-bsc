import java.util.concurrent.Semaphore;

// vasúti szemafor
// n engedély van, ez megadható elengedhető
public class Example {
    public static volatile int common = 0;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true); // fer ütemezés, sort épít a várakozó szálakból

        for (int i = 0; i < 2; i++) {
            int n = i;
            new Thread(() -> {
                while (true) {
                    semaphore.acquireUninterruptibly(2); // engedély kérése
                    // megadható a permitek száma
                    try {
                        System.out.println(n + " acquired the permit at: " + common);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        ++common;
                    } finally {
                        // ha a szál hibával áll le az engedély nem kerül visszaadásra
                        semaphore.release(2); // engedély visszaadása
                    }


                }
            }).start();
        }
    }
}