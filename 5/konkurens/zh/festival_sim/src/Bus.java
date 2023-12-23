import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Singleton osztaly, tehat csak egy lehet belole. Amikor a koncertek lementek, a busz elkezdi hazavinni az embereket,
 * viszont csak $LIMIT ferohely van rajta, szoval lehet, hogy varnia kell nehany fesztivalozonak, amig visszajon ertuk a
 * busz.
 */
public class Bus {

    // Maximum utasok szama
    private static final int LIMIT = 2;

    // Ennyi idot var, hogy felszaljanak az utasok
    private static final int WAITING_TIME_MSEC = 1000;

    // Ennyi idobe telik megtennie egy utat
    private static final int TRIP_TIME_MSEC = 500;

    // A busz utasai (fix ferohellyel)
    // 3. Resz: Valamilyen adatszerkezet a fent emlitett celra
    // V: sok törlés, indexelés nem kell -> láncolt, indexelés nem kell, de konkurencia igen -> LinkedBlockingQueue
    // V: mondjuk itt ArrayBlockingQueue is jó igazából
    private static final BlockingQueue<Attendee> passengers = new LinkedBlockingQueue<>(LIMIT);

    // Valtozo arra, hogy fogad-e uj utasokat, vagy nem
    // 3. Resz: Valamilyen valtozo a fent emlitett celra
    // V: flag, hogy fogad-e utasokat, fix időközönként átbillentjük
    // V: nem is biztos hogy Atomicra kell idk
    private static final AtomicBoolean isTakingPassengers = new AtomicBoolean(false);

    // Singleton valtozo
    private static Bus instance;

    private Bus() {
    }

    /**
     * Singleton getter, ha hozza szeretnenk ferni a buszhoz, igy hasznaljuk: Bus.getInstance()
     */
    public static Bus getInstance() {
        if (instance == null) {
            instance = new Bus();
        }
        return instance;
    }

    /**
     * Elinditja a buszt, oda-vissza megy "haza" es a fesztival kozott. Amikor a fesztivalon van, akkor fel tud venni uj
     * utasokat, de amig uton van, nem szallhat fel uj utas. Leall az elso alkalom utan, hogy nem szallt fel senki,
     * amikor a fesztivalon varakozik az utasokra.
     */
    public void startBus() {
        while (true) {
            // 3. Resz: Irjuk ki - Bus is now taking $LIMIT passengers
            System.out.println("Bus is now taking " + LIMIT + " passangers.");

            // 3. Resz: Az iment letrehozott valtozo segitsegevel jelezzuk, hogy a busz fogad uj utasokat
            isTakingPassengers.set(true);

            // 3. Resz: Varjunk $WAITING_TIME_MSEC-et
            try {
                Thread.sleep(WAITING_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 3. Resz: Irjuk ki - Bus is going to drop passengers off
            System.out.println("Bus is going to drop passangers off");

            // 3. Resz: Az iment letrehozott valtozo segitsegevel jelezzuk, hogy a busz *_NEM_* fogad uj utasokat
            isTakingPassengers.set(false);

            // 3. Resz: Varjunk $TRIP_TIME_MSEC-et
            try {
                Thread.sleep(TRIP_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 3. Resz: Ha nem szallt fel utas, lepjunk ki a ciklusbol
            if (passengers.isEmpty()) {
                break;
            } else {
                // 3. Resz: Ha volt uj utas, szallitsuk le oket, es
                // irjuk ki - $passengerName got off the bus, going home

                // V: ebbe elvileg más szál nem is tud belerondítani, de így teljesen korrekt
                while (!passengers.isEmpty()) {
                    System.out.println(passengers.remove().getName() + " got off the bus, going home");
                }
            }
        }
    }

    /**
     * Egy utas megprobal felszallni a buszra. Ha a busz jelenleg nem fogad uj utasokat (nem indult meg el, vagy eppen
     * uton van), terjen vissza false-szal, egyebkent probalja fogadni az utasokat. Ha a busznak van meg ures ulohelye,
     * es fel tud szallni egy uj utas, terjen vissza true-val, ha a busz teli van ($LIMIT), azaz nincs helye az utasnak,
     * terjen vissza false-szal.
     */
    public boolean tryToGetOnBus(Attendee attendee) {
        // 3. Resz: Ha a busz jelenleg nem vesz fel uj utasokat,
        if (!isTakingPassengers.get()) {
            // 3. Resz: terjunk vissza false-szal
            // 3. Resz: Es irjuk ki - The bus is not taking passengers currently
            System.out.println("The bus is not taking passengers currently.");
            return false;
        }

        // 3. Resz: Ha az utas (attendee) fel tudott szallni a buszra, terjunk vissza true-val
        // 3. Resz: es irjuk ki - $attendeeName got on the bus

        // V: offer false, ha nem volt hely, ekkor az utas folytatja a várást
        if (passengers.offer(attendee)) {
            System.out.println(attendee.getName() + " got on the bus");
            return true;
        }

        // 3. Resz: Ha az utas (attendee) nem tudott felszallni a buszra, terjunk vissza false-szal
        // 3. Resz: es irjuk ki - $attendeeName could not get on the bus
        System.out.println(attendee.getName() + " could not get on the bus");
        return false;
    }
}
