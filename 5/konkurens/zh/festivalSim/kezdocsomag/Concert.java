/**
 * Represents a concert. The concert has attendees, which is people who would
 * like to attend the concert. When the concert start, it notifies them,
 * when the concert ends, it notifies them again
 *
 * Koncertet reprezental. Vannak resztvevoi, akik a fesztivalozok, akik el
 * szeretnenek menni a koncertre. Amikor indul a koncert, ertesiti oket,
 * amikor vege a koncertnek, ujra ertesiti oket
 */
public class Concert {


    // Collection for people who would like to go to the concert
    // Adatszerkezet a fesztivalozoknak, akik reszt akarnak venni a koncerten
    // TODO Part 1.: Create the above described collection (type is Attendee)
    // TODO 1. Resz: Hozzuk letre a fent leirt adatszerkezetet (Attendee a tipus)
    // How much time till the concert starts
    // Ennyi ido mulva kezd a koncert
    private final int startTime;
    // Duration of the concert
    // A koncert idotartama
    private final int duration;
    private final String name;

    public Concert(final String name, final int startTime, final int duration){
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
    }

    /**
     * Wait until startTime elapses, then notify attendees that the concert is starting.
     * Wait until duration elapses, then notify attendees that the concert is over
     *
     * Var, amig a startTime letelik, majd ertesiti a resztvevoket, hogy kezdodik a koncert.
     * Utana var, amig a duration letelik, majd ertesiti a resztvevoket, hogy vege a koncernek
     */
    public void start(){

        // TODO Part 1.: Wait $startTime
        // TODO 1. Resz: Varjunk $startTime-ot

        // TODO Part 1.: Tell the attendees that the concert is about to start
        // TODO Part 1.: and print - $attendeeName is going to $name
        // TODO 1. Resz: Ertesitsuk a resztvevoket, hogy mindjart kezd a koncert
        // TODO 1. Resz: es irjuk ki - $attendeeName is going to $name


        // TODO Part 1.: Wait $duration
        // TODO 1. Resz: Varjunk $duration-t


        // TODO Part 1.: Tell the attendees that the concert is over
        // TODO Part 1.: and print - $name finished, $attendeeName is going to party
        // TODO 1. Resz: Ertesitsuk a resztvevoket, hogy vege a koncertnek
        // TODO 1. Resz: es irjuk ki - $name finished, $attendeeName is going to party
    }

    /**
     * Adds an attendee to the the attendees collection, meaning they would like to go to the concert
     *
     * Hozzaad egy fesztivalozot a resztvevokhoz, jelezve, hogy reszt szeretnenek venni a koncerten
     * @param attendee
     */
    public void addAttendee(final Attendee attendee){
        // TODO Part 1.: Add $attendee to the collection of Attendees
        // TODO 1. Resz: Adjuk hozza $attendee-t az Attendee-knek letrehozott adatszerkezethez
    }

}
