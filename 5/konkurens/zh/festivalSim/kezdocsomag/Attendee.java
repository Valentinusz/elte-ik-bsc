/**
 * Represents a person going to the festival.
 * They wait for the concert they would like to attend
 * then attend the concert, after that go to party
 * Part 2.: until the festival is over
 * Part 3.: until the bus can pick them up and go home.
 *
 * Egy fesztivalozot reprezental.
 * Eloszor megvarjak, amig elkezdodik a koncertjuk,
 * utan elmennek arra a koncertre, majd buliznak
 * 2. Resz: addig, ameddig vege a fesztivalnak
 * 3. Resz: addig, ameddig a busz fel tudja venni oket es haza tudnak menni
 */
public class Attendee {

    // Time they wait until trying to get on the bus after an unsuccessful attempt
    // Ennyi idot varnak, mielott egy sikertelen proba utan ujra megprobalnak a buszra felszallni
    private static final int WAIT_TIME_MSEC = 1000;

    private final String name;

    public Attendee(final String name){
        this.name = name;
    }

    /**
     * Wait for the concert to begin, then invoke goToConcert()
     *
     * Megvarjak, amig elkezdodik a koncert, majd meghivjak a goToConcert() metodust
     */
    public void waitForConcert(){
        // TODO Part 1.: Wait until the concert starts
        // TODO 1. Resz: Varakozzon, amig a koncert elindul

        // TODO Part 1.: After the concert started, invoke goToConcert()
        // TODO 1. Resz: Miutan elkezdodott a koncert, hivjuk meg a goToConcert() metodust
    }

    /**
     * Wait until the concert is over then invoke goToParty()
     *
     * Megvarjak, amig vege a koncertnek, majd meghivjak a goToParty() metodust
     */
    public void goToConcert(){
        // TODO Part 1.: Wait until the concert finishes
        // TODO 1. Resz: Varakozzon, amig a koncertnek vege

        // TODO Part 2./3.: After the concert finished, invoke goToParty()
        // TODO 2./3. Resz: Miutan a koncertnek vege, hivjuk meg a goToParty() metodust
    }

    /**
     * Part 2: Partying until the festival ends
     * Part 3: Partying until they can get on the bus
     *
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     * 3. Resz: Buliznak, amig fel tudnak szallni a buszra
     */
    public void goToParty(){

        // TODO Part 2./3.: Print out - "$name began partying"
        // TODO 2./3. Resz: Irjuk ki - "$name began partying"

        // ONLY PART 2 / CSAK A 2. RESZ
        // TODO Part 2.: Check every $WAIT_TIME_MSEC if the festival is over, then finish partying
        // TODO Part 2.: Advice: You can create new variables and methods to keep track of it
        // TODO 2. Resz: Minden $WAIT_TIME_MSEC-enkent csekkoljuk, hogy vege van-e a fesztivalnak
        // TODO 2. Resz: aztan hagyjuk abba a bulit, ha igen
        // TODO 2. Resz: Tipp: Csinalhatsz uj valtozokat es metodusakat ennek segitsegere
        // ONLY PART 2 / CSAK A 2. RESZ

        // ONLY PART 3 / CSAK A 3. RESZ
        // TODO Part 3.: Try to get on the bus (Bus::tryToGetOnBus) every $WAIT_TIME_MSEC
        // TODO Part 3.: If the attendee could get on the bus, finish partying and leave
        // TODO 3. Resz: Probaljon felszallni a buszra (Bus::tryToGetOnBus) $WAIT_TIME_MSEC idokozonkent
        // TODO 3. Resz: Ha fel tudott szallni a buszra, hagyja abba a bulit es lepjen le
        // ONLY PART 3 / CSAK A 3. RESZ

    }

    public String getName(){
        return this.name;
    }


}
