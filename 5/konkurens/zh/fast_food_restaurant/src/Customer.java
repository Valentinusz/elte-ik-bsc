import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class Customer extends Thread {
    private static final String[] GIVEN_NAMES = {
            "János", "József", "András", "Pál", "Péter", "Zoltán", "Béla", "László", "Géza", "Tamás", "Mihály",
            "Miklós", "Imre", "Dániel", "Mária", "Anna", "Judit", "Zsófia", "Zsuzsanna", "Barbara", "Ilona", "Anikó",
            "Enikő", "Anita", "Andrea", "Orsolya", "Katalin", "Júlia"
    };

    private static final String[] FAMILY_NAMES = {
            "Nagy", "Kiss", "Kovács", "Szabó", "Tóth", "Németh", "Pintér", "Fazekas", "Ács", "Végh", "Tímár"
    };

    private static final String[] FOOD_NAMES = {"hamburger", "hot-dog", "saláta", "sült hús", "szendvics"};

    private CountDownLatch waiting = new CountDownLatch(1);

    public Customer() {
        super(
        FAMILY_NAMES[ThreadLocalRandom.current().nextInt(FAMILY_NAMES.length)] + " " +
            GIVEN_NAMES[ThreadLocalRandom.current().nextInt(GIVEN_NAMES.length)]
        );
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Éhes vagyok, ennék valamit. Bemegyek ebbe az étterembe, beállos a sorba, remélem, hogy hamar sorra kerülök.");

        // Álljunk be a sorba!
        try {
            Restaurant.getInstance().standIntoQueue(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(getName() + ": Sorban állok, és várok, hogy felvegyék a rendelésem.");

        // Várjunk, hogy felvegyék a rendelésünket!
        try {
            waiting.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String requestedFood = FOOD_NAMES[ThreadLocalRandom.current().nextInt(FOOD_NAMES.length)];
        System.out.println(getName() + ": Ezt kérném szépen: egy " + requestedFood + "!");

        // Készítsünk egy jövőbeli karakterláncot, ami majd a elkészült étel nevét fogja tartalmazni!
        Future<String> food = Restaurant.getInstance().order(this.getName(), requestedFood);

        // Vedd ki a komment jelet a 3. feladathoz:
        washHands();

        try {
            System.out.println(getName() + ": Az a " + food.get() + " nagyon finom!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeOrder() {
        waiting.countDown();
    }

    private void washHands() {
        System.out.println(getName() + ": Kezet mosok étkezés előtt.");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            // We should not get into here
            e.printStackTrace();
        }
    }
}
