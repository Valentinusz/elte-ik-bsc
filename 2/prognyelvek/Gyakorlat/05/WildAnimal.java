// felsorolás

/**
 * @since 2022 
 * @author boda
 */

// dokumentáció létrehozása
// javadoc -d doc *.java

public enum WildAnimal {
    RACOON("trash", 40),
    ELEPHANT("nuts", 30000),
    MONKE("banana", 420),
    GIRAFFE("leaves",69);

    // final kulcsszó: konstans (csak egyszer adható érték)
    private final String food;
    private final int amount;

    WildAnimal(String food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    // decorator / annotation, jelzés a fordítónak
    // esetünkben hogy felülírnánk valamit  

    @Override
    public String toString() {
        switch(this) {
            case MONKE: return "monke";
            case GIRAFFE: return "girafee";
            case ELEPHANT: return "elephant";
            case RACOON: return "racoon";
            default: return "animal";
        }
    }

    /**
     * Prints out according to the specification.
     * @return String representation of the enum.
     */


    public static String listAllAnimals() {
        // StringBuilder hatékonyabb alternatíva a konkatenálásra 
        StringBuilder sb = new StringBuilder();

        // values metódus visszaadja a felsorolás elemeit
        for(WildAnimal animal : WildAnimal.values()) {
            sb.append(animal.ordinal());
            // ordinal metódus az enum sorszáma
            sb.append(": the ").append(animal.toString());
            sb.append(" likes to eat ").append(animal.food).append(" ");
            sb.append(animal.amount).append(" times a week.\n");
        }



        return sb.toString();
    }
}
