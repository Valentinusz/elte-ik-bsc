/**
 * Singleton class, which means there can only be one of this.
 * When the concerts finished, the bus will begin taking people home,
 * but only has $LIMIT spaces to fill, so people might need to wait
 * until it comes back for them
 *
 * Singleton osztaly, tehat csak egy lehet belole.
 * Amikor a koncertek lementek, a busz elkezdi hazavinni az embereket,
 * viszont csak $LIMIT ferohely van rajta, szoval lehet, hogy varnia kell
 * nehany fesztivalozonak, amig visszajon ertuk a busz
 */
public class Bus {

    // Maximum number of passangers
    // Maximum utasok szama
    private static final int LIMIT = 2;
    // Time it spends waiting for passengers to get on
    // Ennyi idot var, hogy felszaljanak az utasok
    private static final int WAITING_TIME_MSEC = 1000;
    // Time it takes for the bus to make a trip
    // Ennyi idobe telik megtennie egy utat
    private static final int TRIP_TIME_MSEC = 500;

    // Singleton instance
    // Singleton valtozo
    private static Bus instance;
    // Passengers of the bus (with capacity)
    // A busz utasai (fix ferohellyel)
    // TODO Part 3.: Create some collection for the above purpose
    // TODO 3. Resz: Valamilyen adatszerkezet a fent emlitett celra
    // Variable for whether its taking on new passengers or not
    // Valtozo arra, hogy fogad-e uj utasokat, vagy nem
    // TODO Part 3.: Create a variable for the above purpose
    // TODO 3. Resz: Valamilyen valtozo a fent emlitett celra

    private Bus(){}

    /**
     * Singleton getter, if we want to get the bus, use:
     * Bus.getInstance()
     *
     * Singleton getter, ha hozza szeretnenk ferni a buszhoz, igy hasznaljuk:
     * Bus.getInstance()
     * @return
     */
    public static Bus getInstance(){
        if(instance == null)
            instance = new Bus();
        return instance;
    }

    /**
     * Starts the bus, so it keeps going back and forth between "home" and
     * the festival. When it's at the festival, it can take new passengers,
     * but while it's on the road, new passengers cannot get on it. Finishes
     * after the first time no passengers want to get on the bus.
     *
     * Elinditja a buszt, oda-vissza megy "haza" es a fesztival kozott.
     * Amikor a fesztivalon van, akkor fel tud venni uj utasokat,
     * de amig uton van, nem szallhat fel uj utas. Leall az elso alkalom utan,
     * hogy nem szallt fel senki, amikor a fesztivalon varakozik az utasokra.
     */
    public void startBus(){
        while(true){
            // TODO Part 3.: Print out - Bus is now taking $LIMIT passengers
            // TODO Part 3.: Use the previously created variable to signal that
            // TODO Part 3.: the bus is currently taking passengers

            // TODO 3. Resz: Irjuk ki - Bus is now taking $LIMIT passengers
            // TODO 3. Resz: Az iment letrehozott valtozo segitsegevel jelezzuk,
            // TODO 3. Resz: hogy a busz fogad uj utasokat

            // TODO Part 3.: Wait $WAITING_TIME_MSEC
            // TODO 3. Resz: Varjunk $WAITING_TIME_MSEC-et

            // TODO Part 3.: Print out - Bus is going to drop passengers off
            // TODO Part 3.: Use the previously created variable to signal that
            // TODO Part 3.: the bus is currently *_NOT_* taking passengers

            // TODO 3. Resz: Irjuk ki - Bus is going to drop passengers off
            // TODO 3. Resz: Az iment letrehozott valtozo segitsegevel jelezzuk,
            // TODO 3. Resz: hogy a busz *_NEM_* fogad uj utasokat

            // TODO Part 3.: Wait $TRIP_TIME_MSEC
            // TODO 3. Resz: Varjunk $TRIP_TIME_MSEC-et

            // TODO Part 3.: If the bus had no new passengers, exit the cycle
            // TODO Part 3.: If the bus had passengers, remove them and
            // TODO Part 3.: print - $passengerName got off the bus, going home
            // TODO 3. Resz: Ha nem szallt fel utas, lepjunk ki a ciklusbol
            // TODO 3. Resz: Ha volt uj utas, szallitsuk le oket, es
            // TODO 3. Resz: irjuk ki - $passengerName got off the bus, going home

        }
    }

    /**
     * A passenger tries to get on the bus. If the bus is currently
     * not taking any passengers (hasn't started yet or is on the road) return false,
     * otherwise try to fit them in. If the bus has empty seats to fill, and the passenger
     * can get on, return true, if the bus is filled ($LIMIT), so the passenger has no seat,
     * return false.
     *
     * Egy utas megprobal felszallni a buszra. Ha a busz jelenleg nem fogad
     * uj utasokat (nem indult meg el, vagy eppen uton van), terjen vissza false-szal,
     * egyebkent probalja fogadni az utasokat. Ha a busznak van meg ures ulohelye, es fel tud
     * szallni egy uj utas, terjen vissza true-val, ha a busz teli van ($LIMIT), azaz nincs helye
     * az utasnak, terjen vissza false-szal.
     * @param attendee
     * @return
     */
    public boolean tryToGetOnBus(Attendee attendee){

        // TODO Part 3.: If the bus is currently not taking on new passengers
        // TODO Part 3.: return false
        // TODO Part 3.: And print - The bus is not taking passengers currently
        // TODO 3. Resz: Ha a busz jelenleg nem vesz fel uj utasokat,
        // TODO 3. Resz: terjunk vissza false-szal
        // TODO 3. Resz: Es irjuk ki - The bus is not taking passengers currently

        // TODO Part 3.: If the passenger (attendee) can get on the bus, return true
        // TODO Part 3.: and print - $attendeeName got on the bus
        // TODO Part 3.: If the passenger (attendee) could not get on the bus, return false
        // TODO Part 3.: and print - $attendeeName could not get on the bus
        // TODO 3. Resz: Ha az utas (attendee) fel tudott szallni a buszra, terjunk vissza true-val
        // TODO 3. Resz: es irjuk ki - $attendeeName got on the bus
        // TODO 3. Resz: Ha az utas (attendee) nem tudott felszallni a buszra, terjunk vissza false-szal
        // TODO 3. Resz: es irjuk ki - $attendeeName could not get on the bus
        return false;
    }

}
