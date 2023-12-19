import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FestivalSimulator {

    // Koncertekre szant ido
    private static final int CONCERTS_DURATION_MSEC = 6000;

    // Bulizasra es hazamenetelre szant ido
    private static final int PARTY_DURATION_MSEC = 8000;

    // Ha kell...
    private static final int NUMBER_OF_ADDITIONAL_THREADS = 1;

    // Koncertek listaja
    private static final List<Concert> concerts = List.of(
            new Concert("Krubi", 2500, 1000),
            new Concert("Wellhello", 2000, 1000),
            new Concert("Halott Penz", 1500, 1000),
            new Concert("Tankcsapda", 2000, 1000), // V: ők sem hazudtak mindenben
            new Concert("Byealex", 1500, 1000),
            new Concert("Majka", 2500, 1000)
    );

    // Fesztivalozok listaja
    private static final List<Attendee> attendees = List.of(
            new Attendee("Olga"),
            new Attendee("Helga"),
            new Attendee("Eszter"),
            new Attendee("Feri"),
            new Attendee("Peti"),
            new Attendee("Laci")
    );

    /**
     * 1. Hivjuk meg a setup() metodust, hogy hozzarendeljuk a fesztivalozokat a koncertekhez
     * 2. Hivjuk meg a fesztivalozok waitForConcert() metodusat, hogy varakozzanak a koncertjukre (egy kulon szalon)
     * 3. Hivjuk meg a koncertek start() metodusat, hogy elkeszuljenek (egy kulon szalon)
     * 4. Varjunk $CONCERT_DURATION_MSEC-et
     * 5a. (CSAK A 2. Resz) $CONCERT_DURATION_MSEC utan adjuk a resztvevok tudtara, hogy vege a fesztivalnak
     * 5b. (CSAK A 3. Resz) Inditsuk el a buszt a startBus() metodussal (egy kulon szalon)
     * 6. Fejezzuk be ugy a kodot, hogy a program maximum $PARTY_DURATION_MSEC ido utan terminaljon, de ha lehetseges,
     * ne varjuk ki mindenkepp ezt az idot
     *
     * @param args
     */
    public static void main(String[] args) {
        setup();

        // V: ExecutorService a feladatok kezelésére
        ExecutorService executorService = Executors.newFixedThreadPool(
                attendees.size() + concerts.size() + NUMBER_OF_ADDITIONAL_THREADS // busznak
        );

        // 1. Resz: Hivjuk meg a waitForConcert() metodust minden resztvevonek egy kulon szalon
        // V: átadjuk a feladatokat az ExecutorService-nek
        attendees.forEach(attendee -> executorService.submit(attendee::waitForConcert));

        // 1. Resz: Hivjuk meg a start() metodust minden koncertnek egy kulon szalon
        // V: átadjuk a feladatokat az ExecutorService-nek
        concerts.forEach(concert -> executorService.submit(concert::start));

        // 1. Resz: Varjunk $CONCERTS_DURATION_MSEC-et
        try {
            Thread.sleep(CONCERTS_DURATION_MSEC);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 2. Resz CSAK: Tudassuk a fesztivalozokkal, hogy vege a fesztivalnak
        // V: CONCERTS_DURATION_MSEC után minden koncert véget ért, billentsük át a Attendee flagjét
        attendees.forEach(Attendee::endFestival);

        // 3. Resz CSAK: Inditsuk el a buszt (Bus::startBus) egy kulon szalon
        executorService.submit(() -> Bus.getInstance().startBus());


        // 1. Resz: Varjunk maximum $PARTY_DURATION_MSEC-et, de inkabb kevesebbet,
        try {
            executorService.shutdown();
            /* V:
            Addig blokkol amíg a korábban megadott feladatok be nem fejeződnek. Ha ez nem történik meg
            PARTY_DURATION_MSEC-on belül akkor timeout miatt abbahagyja a blokkolást, ha a taskok befejeződtek akkor
            szintén abbahagyja a blokkolást.
             */
            executorService.awaitTermination(PARTY_DURATION_MSEC, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 1. Resz: majd allitsuk le a szimulaciot
            // V: ha minden leállt, akkor ez teljesen biztonságos
            executorService.shutdownNow();
        }
    }

    /**
     * "Szofisztikalt" algoritmus, ami hozzarendeli a fesztivalozokat a koncertekhez. xd
     */
    private static void setup() {
        for (int i = 0; i < concerts.size(); ++i) {
            concerts.get(i).addAttendee(attendees.get(i));
        }
    }
}
