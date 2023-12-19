import java.util.concurrent.ThreadLocalRandom;

public class Customer extends Thread {
    private static final String[] GIVEN_NAMES = {"János", "József", "András", "Pál", "Péter", "Zoltán", "Béla", "László", "Géza", "Tamás", "Mihály", "Miklós", "Imre", "Dániel",
                                                 "Mária", "Anna", "Judit", "Zsófia", "Zsuzsanna", "Barbara", "Ilona", "Anikó", "Enikő", "Anita", "Andrea", "Orsolya", "Katalin", "Júlia"};
    private static final String[] FAMILY_NAMES = {"Nagy", "Kiss", "Kovács", "Szabó", "Tóth", "Németh", "Pintér", "Fazekas", "Ács", "Végh", "Tímár"};
    // Uncomment for task 2:
    // private static final String[] FOOD_NAMES = {"hamburger", "hot-dog", "saláta", "sült hús", "szendvics"};

    public Customer() {
        super(FAMILY_NAMES[ThreadLocalRandom.current().nextInt(FAMILY_NAMES.length)] + " " +
              GIVEN_NAMES[ThreadLocalRandom.current().nextInt(GIVEN_NAMES.length)]);
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Éhes vagyok, ennék valamit. Bemegyek ebbe az étterembe, beállos a sorba, remélem, hogy hamar sorra kerülök.");

        // TODO1: Álljunk be a sorba!

        // Vedd ki a komment jelet az 1. feladathoz:
        // System.out.println(getName() + ": Sorban állok, és várok, hogy felvegyék a rendelésem.");

        // TODO2: Várjunk, hogy felvegyék a rendelésünket!

        // Vedd ki a komment jelet a 2. feladathoz:
        // String requestedFood = FOOD_NAMES[ThreadLocalRandom.current().nextInt(FOOD_NAMES.length)];
        // System.out.println(getName() + ": Ezt kérném szépen: egy " + requestedFood + "!");

        // TODO3: Készítsünk egy jövőbeli karakterláncot, ami majd a elkészült étel nevét fogja tartalmazni!
        // Vedd ki a komment jelet a 3. feladathoz:
        // washHands();
    
        // TODO3: Kiírás:
        // TODO3:     System.out.println(getName() + ": Az a " + <elkészült étel neve> + " nagyon finom!");
    }   
    
    // Vedd ki a komment jelet a 3. feladathoz:
    // private void washHands() {
    //     System.out.println(getName() + ": Kezet mosok étkezés előtt.");
    //     try {
    //         Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    //     } catch (InterruptedException e) {
    //         // We should not get into here
    //         e.printStackTrace();
    //         return;
    //     }
    // }
}
