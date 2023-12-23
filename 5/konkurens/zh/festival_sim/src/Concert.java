import java.util.*;

/**
 * Koncertet reprezental. Vannak resztvevoi, akik a fesztivalozok, akik el szeretnenek menni a koncertre. Amikor indul
 * a koncert, ertesiti oket, amikor vege a koncertnek, ujra ertesiti oket.
 */
public class Concert {

    // Adatszerkezet a fesztivalozoknak, akik reszt akarnak venni a koncerten
    // 1. Resz: Hozzuk letre a fent leirt adatszerkezetet (Attendee a tipus)
    private final List<Attendee> attendees = Collections.synchronizedList(new LinkedList<>());

    // Ennyi ido mulva kezd a koncert
    private final int startTime;
    // A koncert idotartama
    private final int duration;
    private final String name;

    public Concert(final String name, final int startTime, final int duration){
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
    }

    /**
     * Var, amig a startTime letelik, majd ertesiti a resztvevoket, hogy kezdodik a koncert.
     * Utana var, amig a duration letelik, majd ertesiti a resztvevoket, hogy vege a koncernek
     */
    public void start(){
        // 1. Resz: Varjunk $startTime-ot
        try {
            Thread.sleep(startTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 1. Resz: Ertesitsuk a resztvevoket, hogy mindjart kezd a koncert
        // V: synchronizedList-re iterálásnál külön szinkronizálni kell
        synchronized (attendees) {
            for (var attendee : attendees) {
                // 1. Resz: es irjuk ki - $attendeeName is going to $name
                System.out.println(attendee.getName() + " is going to " + this.name);
                // V: meg kell szerezni a zárat hogy notify() hívás működjön
                synchronized (attendee) {
                    // V: notify() itt biztonságos mert az adott zárra biztos csak egy szál vár
                    attendee.notify();
                }
            }
        }

        // 1. Resz: Varjunk $duration-t
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 1. Resz: Ertesitsuk a resztvevoket, hogy vege a koncertnek
        synchronized (attendees) {
            for (Attendee attendee : attendees) {
                // 1. Resz: es irjuk ki - $name finished, $attendeeName is going to party
                System.out.println(this.name + " finished, " + attendee.getName() + " is going to party");
                synchronized (attendee) {
                    attendee.notify();
                }
            }
        }
    }

    /**
     * Hozzaad egy fesztivalozot a resztvevokhoz, jelezve, hogy reszt szeretnenek venni a koncerten.
     */
    public void addAttendee(final Attendee attendee){
        // 1. Resz: Adjuk hozza $attendee-t az Attendee-knek letrehozott adatszerkezethez
        this.attendees.add(attendee);
    }
}
