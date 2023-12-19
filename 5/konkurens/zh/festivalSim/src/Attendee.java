import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Egy fesztivalozot reprezental.
 * Eloszor megvarjak, amig elkezdodik a koncertjuk,
 * utan elmennek arra a koncertre, majd buliznak
 * 2. Resz: addig, ameddig vege a fesztivalnak
 * 3. Resz: addig, ameddig a busz fel tudja venni oket es haza tudnak menni
 */
public class Attendee {

    // Ennyi idot varnak, mielott egy sikertelen proba utan ujra megprobalnak a buszra felszallni
    private static final int WAIT_TIME_MSEC = 1000;

    private final String name;

    /**
     * V: Ez a flag jelzi, hogy véget ért a buli, kívülről átbillentjük ha simulator szerint letelt a buli ideje is.
     */
    private final AtomicBoolean isFestivalOver = new AtomicBoolean(false);

    public Attendee(final String name){
        this.name = name;
    }

    /**
     * Megvarjak, amig elkezdodik a koncert, majd meghivjak a goToConcert() metodust
     */
    public void waitForConcert(){
        // 1. Resz: Varakozzon, amig a koncert elindul
        // V: Jelen objektum zárát megszerezzük. Ez kell ahhoz hogy a wait() hívás működjön.
        synchronized (this) {
            try {
                // V: feladjuk az objektumát zárát és várunk, amíg értesítést nem kapunk
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 1. Resz: Miutan elkezdodott a koncert, hivjuk meg a goToConcert() metodust
        goToConcert();
    }

    /**
     * Megvarjak, amig vege a koncertnek, majd meghivjak a goToParty() metodust
     */
    public void goToConcert() {
        // 1. Resz: Varakozzon, amig a koncertnek vege
        // V: Jelen objektum zárát megszerezzük. Ez kell ahhoz hogy a wait() hívás működjön.
        synchronized (this) {
            try {
                // V: feladjuk az objektumát zárát és várunk, amíg értesítést nem kapunk
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // V: ezen a ponton már megérkezett az értesítés hogy vége a koncertnek

        // 2./3. Resz: Miutan a koncertnek vege, hivjuk meg a goToParty() metodust

        this.goToParty();
    }

    /**
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     */
    public void goToParty2(){
        // 2./3. Resz: Irjuk ki - "$name began partying"
        System.out.println(this.name + " began partying");

        // 2. Resz: Minden $WAIT_TIME_MSEC-enkent csekkoljuk, hogy vege van-e a fesztivalnak
        // 2. Resz: aztan hagyjuk abba a bulit, ha igen
        // 2. Resz: Tipp: Csinalhatsz uj valtozokat es metodusakat ennek segitsegere

        // V: jelzőflaget figyeljük
        while (!isFestivalOver.get()) {
            try {
                Thread.sleep(WAIT_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     * 3. Resz: Buliznak, amig fel tudnak szallni a buszra
     */
    public void goToParty(){
        // 2./3. Resz: Irjuk ki - "$name began partying"
        System.out.println(this.name + " began partying");

        // 3. Resz: Probaljon felszallni a buszra (Bus::tryToGetOnBus) $WAIT_TIME_MSEC idokozonkent

        // V: buszraszállási próba
        while (!Bus.getInstance().tryToGetOnBus(this)) {
            try {
                Thread.sleep(WAIT_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // V: ide csak akkor jut el ha már felszállt a buszra

        // 3. Resz: Ha fel tudott szallni a buszra, hagyja abba a bulit es lepjen le
    }

    public String getName(){
        return this.name;
    }

    /**
     * Ends the festival.
     */
    public void endFestival() {
        this.isFestivalOver.set(true);
    }
}
