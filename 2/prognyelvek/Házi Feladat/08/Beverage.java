public class Beverage {
    private String name;
    private int legalAge;

    public Beverage(String name, int legalAge) {
        if (name == null || legalAge < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.legalAge = legalAge;
    }

    public String getName() {
        return this.name;
    }

    public int getLegalAge() {
        return this.legalAge;
    }
}